import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10090 {
	/*
	큰 수가 작은 수 앞에 있을 때 INVERSION 형태
	그 형태의 개수를 구해라
	 */
	static int[] tree;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		tree = new int[N*4];
		arr = new int[N];
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		long sum = 0;
		for (int i=N-1;i>=0;i--) {
			if (arr[i] > 1) {
				sum += search(1, 1, N, 1, arr[i] - 1);
			}
			update(1, 1, N, arr[i]);
		}
		System.out.println(sum);
	}

	// public static void create(int node, int start, int end) {
	// 	if (start == end) {
	// 		tree[node] = arr[start];
	// 		return;
	// 	}
	// 	int mid = (start+end)/2;
	// 	create(node*2, start, mid);
	// 	create(node*2+1, mid+1, end);
	// 	tree[node] = Math.max(tree[node*2], tree[node*2+1]);
	// }

	public static void update(int node, int start, int end, int idx) {
		if (idx < start || idx > end) return;

		tree[node]++;

		if (start == end) return;

		int mid = (start+end)/2;
		update(node*2, start, mid, idx);
		update(node*2+1, mid+1, end, idx);
	}

	public static int search(int node, int start, int end, int left, int right) {
		if (start>right || end<left) return 0;
		if (left<=start && end<=right) {
			return tree[node];
		}
		int mid = (start+end)/2;
		int leftChild = search(node*2, start, mid, left, right);
		int rightChild = search(node*2+1, mid+1, end, left, right);
		return leftChild+rightChild;
	}
}
