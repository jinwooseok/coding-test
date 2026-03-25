import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_6549 {
	static long[] tree;
	static long[] arr;
	static long maxWidth;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if (N == 0) break;
			tree = new long[N * 4];
			arr = new long[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Long.parseLong(st.nextToken());
			}
			build(1, 0, N - 1);
			maxWidth = 0;
			solve(0, N - 1);
			sb.append(maxWidth).append("\n");
		}
		System.out.println(sb);
	}

	// 최솟값 인덱스 저장
	static void build(int node, int left, int right) {
		if (left == right) {
			tree[node] = left;
			return;
		}
		int mid = (left + right) / 2;
		build(node * 2, left, mid);
		build(node * 2 + 1, mid + 1, right);
		tree[node] = arr[(int)tree[node*2]] <= arr[(int)tree[node*2+1]]
			? tree[node * 2] : tree[node * 2 + 1];
	}

	// 구간 최솟값 인덱스 쿼리
	static int query(int node, int left, int right, int l, int r) {
		if (r < left || right < l) return -1;
		if (l <= left && right <= r) return (int) tree[node];
		int mid = (left + right) / 2;
		int leftIdx = query(node * 2, left, mid, l, r);
		int rightIdx = query(node * 2 + 1, mid + 1, right, l, r);
		if (leftIdx == -1) return rightIdx;
		if (rightIdx == -1) return leftIdx;
		return arr[leftIdx] <= arr[rightIdx] ? leftIdx : rightIdx;
	}

	static void solve(int l, int r) {
		if (l > r) return;
		int m = query(1, 0, N - 1, l, r);
		maxWidth = Math.max(maxWidth, arr[m] * (r - l + 1));
		solve(l, m - 1);
		solve(m + 1, r);
	}
}