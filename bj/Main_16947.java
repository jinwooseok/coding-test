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
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_16947 {
	/*
	순환선
	지선 - 순환선에 속하는 한역에서 시작하는 트리형태의 노선
	거리는 간선의 개수, 역a와 순환선 사이의 거리는 a와 순환선에 속하는 역 사이의 거리중 최소값 (즉, scc의 시작점과 연결됨.)

	각역과 순환선 사이의 거리 라고 한다면 가장 가까운 순환선. 이거는 scc를 사용해야 하는 상황에 봉착한 듯하다.
	아님 union find로 할 수 있을까?
	dfs로 갔을 때 이미 방문했던 노드가 나온다면?
	그렇다면 scc간의 거리는 어떻게 측정할까
	그럼 dfs 방문 순서를 표시하되, 한 scc내에서 방문 순서를 동일하게 부여하면 되긴할듯함.
	 */
	static int[] dfs, sccIds;
	static boolean[] inStack;
	static int sccId, dfsIdx;
	static Set<Integer> stack;
	static Node[] nodes;
	static int c, l;
	static boolean isValid = true;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		nodes = new Node[N+1];
		dfs = new int[N+1];
		sccIds = new int[N+1];
		inStack = new boolean[N+1];
		sccId = 1;
		dfsIdx = 1;
		stack = new HashSet<>();
		c = 0;
		for (int i=1;i<=N;i++) {
			nodes[i] = new Node();
		}
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes[a].nextNodes.add(b);
			nodes[b].nextNodes.add(a);
		}

		// tarjan(1);
		search(1, 0);
		// System.out.println(stack);

		Queue<Integer> q = new ArrayDeque<>();
		int[] dist = new int[N+1];
		Arrays.fill(dist, -1);
		dist[c] = 0;
		q.add(c);
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int next:nodes[cur].nextNodes) {
				if (dist[next] != -1) continue;
				if (stack.contains(next)) dist[next] = 0;
				else dist[next] = dist[cur]+1;
				q.add(next);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i=1;i<=N;i++) {
			sb.append(dist[i]).append(" ");
		}
		System.out.println(sb);
	}

	public static void search(int cur, int prev) {
		if (dfs[cur] != 0) {
			c = cur;
			return;
		}
		dfs[cur] = dfsIdx++;
		for (int next:nodes[cur].nextNodes) {
			if (next == prev) continue;
			if (c != 0) continue;
			search(next, cur);
		}
		if (isValid && c != 0) {
			stack.add(cur);
			if (cur == c) {
				isValid = false;
			}
		}
	}

	// public static void search(int cur) {
	// 	if (dfs[cur] != 0) return;
	// 	dfs[cur] = dfsIdx++;
	// 	stack.add(cur);
	// 	for (int next:nodes[cur].nextNodes) {
	// 		search(next);
	// 		if (dfs[next] < dfs[cur]) {
	// 			int last = stack.pop();
	// 			sccIds[last] = sccId;
	// 			sccId++;
	// 		} else {
	// 			int last = stack.pop();
	// 			sccIds[last] = sccIds[next];
	// 		}
	// 	}
	// }

	// public static void tarjan(int cur) {
	// 	dfs[cur] = ++dfsIdx; // dfs 순서에 현재 노드 저장
	// 	inStack[cur] = true;
	// 	stack.push(cur);
	// 	System.out.println(stack);
	// 	for (int next:nodes[cur].nextNodes) {
	// 		// 다음 방문할 노드가 스택에 있고, 직전에 방문했다면.
	// 		if (dfs[next] == 0) {
	// 			tarjan(next);
	// 		}
	// 		// 아무튼 방문안한점들을 방문한 후, 돌아올때면 모든 노드에는 마킹이 되어있음.
	// 		// 가장 깊은 점에서 내려오는데, 방문순서가 현재 순서보다 작다면, 이것은 순환임.
	// 		// 깊은 점에서 내려오는데, 방문순서가 현재보다 크다면? 그것은 순환이 아니다. 플레3, 브2 (마라톤), 브4 (건축왕)
	// 		if (dfs[next]<dfs[cur]) {
	// 			while (!stack.isEmpty()) {
	// 				// 스택에서 빼는 작업을 수행해야 함. next를 방문하지 않고. 해당 노드가 나올때까지.
	// 				int last = stack.pop();
	// 				System.out.println(last);
	// 				inStack[last] = false;
	// 				if (sccIds[next] != 0) {
	// 					sccIds[last] = sccIds[next];
	// 				} else {
	// 					sccIds[last] = sccId;
	// 				}
	//
	// 				if (last == next) {
	// 					break;
	// 				}
	// 			}
	// 			sccId++;
	// 		}
	// 	}
	// 	// 순환이 아닌경우는 각각이 scc라고 할 수 있음.
	// 	// 해당 노드 (마지막 next에 해당하는 노드를 한개의 scc로 처리한 후 끝낸다) 이러면 그 다음 하위 순회에서도 마찬가지가 됨.
	// 	if (!stack.isEmpty()) {
	// 		int last = stack.pop();
	// 		inStack[last] = false;
	// 		sccIds[last] = sccId;
	// 		sccId++;
	// 	}
	// }

	static class Node {
		List<Integer> nextNodes;

		Node() {
			nextNodes = new ArrayList<>();
		}
	}
}
