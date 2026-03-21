import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_2213 {
	/*
	집합 S에 속한 정점쌍이 인접하지 않으면 독립 집합
	독립집합 크기는 가중치의 합
	트리의 최대 독립 집합 구하기

	준비 자료구조 : 그래프

	1-2-3
	  | |
	  6 4
	  | |
	  7 5
	12676234543
	10 30 30 100 140
	0  10 30 30 100
	1
	2
	36

	최소 공통 조상을 찾음
	리프노드들부터 시작.
	각 공통조상에 도착할 때까지 dp 진행
	공통조상에 이전값의 결과 저장 다시 공통조상까지 dp 진행
	이후 루트노드(1)에서 역추적

	리프노드에서 자식노드가 2개이상인것에 도달할 때까지 진행.
	공통조상에서는 자식노드들 중 자신을 포함안할때 최대인경우(자식노드들의 최대 다 포함)
	자신이 포함됐을 때 최대인 경우(직계자식을 포함안하는 경우)의 최댓값 포함

	1. 1번노드에서 시작해 리프노드 찾기
	2. 리프노드부터 dp계속 진행(children이 2 이상인게 나올때까지)
	3. 반복해서 root에 도달한 경우 멈추기
	4. 역추적
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] weights = new int[N+1];
		Node[] nodes = new Node[N+1];
		st = new StringTokenizer(br.readLine());
		int[][] dp = new int[N+1][2];
		for (int i=1;i<=N;i++) {
			weights[i] = Integer.parseInt(st.nextToken());
			nodes[i] = new Node();
		}

		for (int i=0;i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes[a].children.add(b);
			nodes[b].children.add(a);
		}
		Queue<Integer> q = new ArrayDeque<>();
		q.add(1);
		int cur;
		nodes[1].parent = 0;
		while (!q.isEmpty()) {
			cur = q.poll();
			List<Integer> children = new ArrayList<>();
			for (int next:nodes[cur].children) {
				if (next == nodes[cur].parent) {
					continue;
				}
				nodes[next].parent = cur;
				children.add(next);
				q.add(next);
			}
			nodes[cur].children = children;
		}
		int[] visited = new int[N+1];
		for (int i=1;i<=N;i++) {
			// System.out.println(nodes[i].parent);
			// System.out.println(nodes[i].children);
			if (nodes[i].children.isEmpty()) {
				q.add(i);
				dp[i][0] = 0;
				dp[i][1] = weights[i];
			}
			else visited[i] = nodes[i].children.size();
		}
		// System.out.println(Arrays.toString(visited));
		while (!q.isEmpty()) {
			cur = q.poll();
			int max = 0;
			if (cur == 0) break;
			for (int child:nodes[cur].children) {
				dp[cur][0] += Math.max(dp[child][0], dp[child][1]);
				max += dp[child][0];
			}
			dp[cur][1] = max+weights[cur];
			visited[nodes[cur].parent]--;
			if (visited[nodes[cur].parent]==0) q.add(nodes[cur].parent);
		}

		int result = Math.max(dp[1][0], dp[1][1]);
		StringBuilder sb = new StringBuilder();
		sb.append(result).append("\n");

		q.add(1);
		boolean[] resultSet = new boolean[N+1];
		while (!q.isEmpty()) {
			cur = q.poll();
			if (!resultSet[nodes[cur].parent] && dp[cur][0]<dp[cur][1]) {
				resultSet[cur] = true;
			}
			q.addAll(nodes[cur].children);
		}
		for (int i=1;i<=N;i++) {
			if (resultSet[i]) sb.append(i).append(" ");
		}
		System.out.println(sb);
	}

	static class Node {
		int parent = 0;
		List<Integer> children;
		Node() {
			children = new ArrayList<>();
		}
	}
}
