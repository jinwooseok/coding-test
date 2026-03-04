import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_16404 {
	static int order = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// parent 배열
		int[] parents = new int[N+1];

		// 오일러 경로 순으로 정렬 및 in-out 정보 저장
		int[][] nodes = new int[N+1][2];
		int[] tree = new int[N*4];
		int root = -1;
		st = new StringTokenizer(br.readLine());
		for (int i=1;i<N+1;i++) {
			parents[i] = Integer.parseInt(st.nextToken());
			// System.out.println(parents[i]);
			if (parents[i]==-1) root = i;
		}

		Map<Integer, Set<Integer>> map = new HashMap<>();
		for (int i=1;i<N+1;i++) {
			if (!map.containsKey(parents[i])) {
				map.put(parents[i], new HashSet<>());
				map.get(parents[i]).add(i);
			} else
				map.get(parents[i]).add(i);
		}

		dfs(map, root, nodes);
		// for (int i=0;i<N;i++) {
		// 	System.out.println(Arrays.toString(nodes[i]));
		// }
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			if (command == 1) {
				int sum = Integer.parseInt(st.nextToken());
				update(tree,
					nodes[num][0],
					nodes[num][1],
					1, N,
					1,
					sum);
			} else {
				sb.append(
					search(tree, 1, N, 1, nodes[num][0])
				).append("\n");
			}
		}
		System.out.println(sb);
	}

	public static void update(int[] tree, int start, int end, int left, int right, int node, int sum) {
		if (left>end || right<start) {
			return;
		}
		if (start <= left && right <= end) {
			tree[node] += sum;
			return;
		}
		int mid = (left+right)/2;
		update(tree, start, end, left, mid, node*2, sum);
		update(tree, start, end, mid+1, right,node*2+1, sum);
		return;
	}

	public static long search(int[] tree, int left, int right, int node, int idx) {
		if (left>idx || right<idx) {
			return 0;
		}
		long sum = tree[node];
		if (left == right) {
			return tree[node];
		}
		int mid = (left+right)/2;
		if (idx <= mid)
			return sum + search(tree, left, mid, node * 2, idx);
		else
			return sum + search(tree, mid + 1, right, node * 2 + 1, idx);
	}

	public static void dfs(Map<Integer, Set<Integer>> map,
		int node,
		int[][] nodes) {

		order++;
		nodes[node][0] = order;   // in

		if (map.containsKey(node)) {
			for (int child : map.get(node)) {
				dfs(map, child, nodes);
			}
		}

		nodes[node][1] = order;   // out
	}
}
/*
0
2 3 4
  1

0 2 3 1 4
 */