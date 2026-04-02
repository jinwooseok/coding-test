import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_13925 {
	static long[] arr, tree;
	static long[] plusLazy, mulLazy;
	static int N;
	static long MOD = 1_000_000_007;
	/*
	점검할 것
	1. 쿼리할 때마다 모듈러 연산
	2. prop 맞게 되고 있는지 확인하기
	3. update가 요구사항을 충족하는지 확인
	4. 범위 확인해보기
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		arr = new long[N+1];
		for (int i=1;i<=N;i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}

		// 트리, lazy 초기화
		tree = new long[(N+1)*4];
		plusLazy = new long[(N+1)*4];
		mulLazy = new long[(N+1)*4];
		Arrays.fill(mulLazy, 1);
		// 트리 빌드
		build(1, 1, N);
		StringBuilder sb = new StringBuilder();
		// 쿼리 실행
		int M = Integer.parseInt(br.readLine());
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			// System.out.println(Arrays.toString(tree));
			if (command == 4) {
				// 더하기 연산 실행
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				sb.append(query(1, 1, N, x, y)).append("\n");
			} else {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				long v = Long.parseLong(st.nextToken());
				update(1, 1, N, x, y, v, command);
			}
		}
		System.out.println(sb);
	}

	static void build(int node, int left, int right) {
		if (left == right) {
			tree[node] = arr[left];
			return;
		}
		int mid = (left+right)/2;
		build(node*2, left, mid);
		build(node*2+1, mid+1, right);
		tree[node] = (tree[node*2]+tree[node*2+1]) % MOD;
	}

	static void update(int node, int left, int right, int start, int end, long value, int command) {
		if (right<start || left>end) return;
		if (start<=left && right<=end) {
			if (command == 1) {
				// 기존 값에서 더하기를 함. 각각 밸류씩 더하므로 밸류 길이만큼 더하기. 단 모듈러.
				// System.out.println(tree[node]);
				tree[node] += (value*(right-left+1))%MOD;
				plusLazy[node] += value;

				tree[node] = tree[node] % MOD;
				plusLazy[node] = plusLazy[node] % MOD;
				// System.out.println(tree[node]);
			} else if (command == 2) {
				tree[node] *= value;
				mulLazy[node] *= value;
				plusLazy[node] *= value;

				tree[node] %= MOD;
				mulLazy[node] %= MOD;
				plusLazy[node] %= MOD;
			} else {
				tree[node] = (value*(right-left+1))%MOD;

				mulLazy[node] = 0;
				plusLazy[node] = value;
			}
			return;
		}
		int mid = (left+right)/2;
		prop(node, left, mid, right);
		update(node*2, left, mid, start, end, value, command);
		update(node*2+1, mid+1, right, start, end, value, command);
		tree[node] = (tree[node*2]+tree[node*2+1])%MOD;

	}

	static long query(int node, int left, int right, int start, int end) {
		if (right<start || left>end) return 0;
		int mid = (left+right)/2;
		if (start<=left && right<=end) {
			return tree[node];
		}
		prop(node, left, mid, right);
		return (query(node*2, left, mid, start, end) + query(node*2+1, mid+1, right, start, end)) % MOD;
	}

	static void prop(int node, int left, int mid, int right) {
		tree[node*2] = mulLazy[node]*tree[node*2]%MOD+plusLazy[node]*(mid-left+1)%MOD;
		tree[node*2+1] = mulLazy[node]*tree[node*2+1]%MOD+plusLazy[node]*(right-mid)%MOD;

		mulLazy[node*2] = (mulLazy[node*2] * mulLazy[node]) % MOD;
		mulLazy[node*2+1] = (mulLazy[node*2+1] * mulLazy[node]) % MOD;
		plusLazy[node*2] = (plusLazy[node*2] * mulLazy[node] + plusLazy[node]) % MOD;
		plusLazy[node*2+1] = (plusLazy[node*2+1] * mulLazy[node] + plusLazy[node]) % MOD;
		mulLazy[node] = 1;
		plusLazy[node] = 0;

		tree[node*2] %= MOD;
		tree[node*2+1] %= MOD;
	}
}
