import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_14268 {
	static int[] arr;
	static long[] seg;
	static List<Integer>[] tree;
	static int[] in, out;
	static int TIME;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 초기화
		arr = new int[N+1];
		seg = new long[(N+1)*4];
		tree = new ArrayList[N+1];
		TIME = 0;
		in = new int[N+1];
		out = new int[N+1];

		st = new StringTokenizer(br.readLine());
		for (int i=1;i<=N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i=1;i<=N;i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i=2;i<=N;i++) {
			tree[arr[i]].add(i);
		}

		// ett ->
		dfs(1);

		// 세그 트리 쿼리
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			if (command == 1) {
				// 칭찬 범위 업데이트
				int w = Integer.parseInt(st.nextToken());
				update(1, 1, N, in[a], out[a], w);
			} else {
				sb.append(query(1, 1, N, in[a])).append("\n");
			}
		}
		System.out.println(sb);
	}

	static void dfs(int i) {
		// ETT DFS
		in[i] = ++TIME;
		for (int next: tree[i]) {
			dfs(next);
		}
		out[i] = TIME;
	}

	static void update(int node, int left, int right, int start, int end, long weight) {
		if (left>end || start>right) return;
		if (start<=left && right<=end) {
			seg[node] += weight;
			return;
		}
		int mid = (left+right)/2;
		update(node*2, left, mid, start, end, weight);
		update(node*2+1, mid+1, right, start, end, weight);
	}

	static long query(int node, int left, int right, int idx) {
		long sum = seg[node];
		if (left == right) {
			return sum;
		}
		int mid = (left+right)/2;
		if (left <= idx && idx <= mid) {
			sum+=query(node*2, left, mid, idx);
		} else {
			sum+=query(node*2+1, mid+1, right, idx);
		}
		return sum;
	}
}
