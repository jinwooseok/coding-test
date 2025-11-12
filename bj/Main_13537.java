import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_13537 {
	static long[] arr;
	static List<Long>[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new long[N+1];
		tree = new ArrayList[4*(N+1)];
		for (int i = 0; i < tree.length; i++) {
			tree[i] = new ArrayList<>();
		}
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			arr[i+1] = Long.parseLong(st.nextToken());
		}
		create(1, 1, N);
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		// for (int i=0;i<tree.length;i++) {
		// 	System.out.println(Arrays.toString(tree[i]));
		// }
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			long k = Long.parseLong(st.nextToken());
			sb.append(search(1, 1, N, start, end, k)).append("\n");
		}
		System.out.println(sb);
	}

	public static void create(int node, int start, int end) {
		if (start == end) {
			tree[node].add(arr[start]);
			return;
		}
		int mid = (start+end)/2;
		create(node*2, start, mid);
		create(node*2+1, mid+1, end);
		tree[node].addAll(tree[node * 2]);
		tree[node].addAll(tree[node * 2 + 1]);
		Collections.sort(tree[node]);
	}

	public static long search(int node, int start, int end, int left, int right, long k) {
		long sum = 0;
		// System.out.println(start+" "+end);
		// System.out.println(tree[node][0]);
		// System.out.println(tree[node][1]);
		if (end<left || start>right) return 0;
		if (left<=start && end<=right) {
			return tree[node].size()-binarySearch(tree[node], k);
		}
		int mid = (start+end)/2;
		sum += search(node*2, start, mid, left, right, k);
		sum += search(node*2+1, mid+1, end, left, right, k);
		return sum;
	}

	public static long binarySearch(List<Long> list, long k) {
		int left = 0, right = list.size();
		while (left < right) {
			int mid = (left + right) / 2;
			if (list.get(mid) <= k) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}

		return left;
	}
}
