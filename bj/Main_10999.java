import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10999 {
	/*
	N개의 수가 주어짐. 특정 부분합을 구하는 것이 목표
	구간 변경, 구간 쿼리

	특정 구간을 변경하게 되면 그 부분만 +- 가함.
	올라오면서 변경할 수가 없음. 나중에 조회 시 변경이 적용이 안됨

	레이지 세그
	M: 변경 횟수, K: 쿼리 횟수

	 */
	static long[] arr, tree, lazy;
	static int N, M, K;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new long[N+1];
		tree = new long[(N+1)*4];
		lazy = new long[(N+1)*4];

		for (int i=1;i<=N;i++) {
			arr[i] = Long.parseLong(br.readLine());
		}
		build(1, 1, N);
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<M+K;i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			if (command == 1) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				long sum = Long.parseLong(st.nextToken());
				update(1, 1, N, a, b, sum);
			} else {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				sb.append(query(1, 1, N, a, b)).append("\n");
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
		tree[node] = tree[node*2]+tree[node*2+1];
	}

	static void update(int node, int left, int right, int start, int end, long sum) {
		if (left>end || right<start) return;

		if (start<=left && right<=end) {
			tree[node] += (right-left+1)*sum; // 그 구간의 합
			lazy[node] += sum; // 아직 내리지 못함
			return;
		}

		int mid = (left+right)/2;

		if (lazy[node]!=0) {
			tree[node*2] += lazy[node]*(mid-left+1);
			tree[node*2+1] += lazy[node]*(right-mid);
			lazy[node*2] += lazy[node];
			lazy[node*2+1] += lazy[node];
			lazy[node] = 0;
		}

		update(node*2, left, mid, start, end, sum);
		update(node*2+1, mid+1, right, start, end, sum);

		tree[node] = tree[node*2] + tree[node*2+1];
	}

	static long query(int node, int left, int right, int start, int end) {
		if (right < start || end < left) return 0;

		if (start <= left && right <= end) return tree[node];

		int mid = (left+right)/2;

		if (lazy[node]!=0) {
			tree[node*2] += lazy[node]*(mid-left+1);
			tree[node*2+1] += lazy[node]*(right-mid);
			lazy[node*2] += lazy[node];
			lazy[node*2+1] += lazy[node];
			lazy[node] = 0;
		}

		return query(node*2, left, mid, start, end) + query(node*2+1, mid+1, right, start, end);
	}
}
