import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1108 {
	static Map<String, Integer> domains;
	static List<List<Integer>> graph;
	static int[] dfn;
	static int[] lows;
	static int[] sccId;
	static boolean[] onStack;
	static Stack<Integer> stack;
	static int sccCounter;
	static int dfnCounter;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		domains = new HashMap<>();
		stack = new Stack<>();
		sccCounter = 0;
		dfnCounter = 1;
		int idx = 0;
		graph = new ArrayList<>();
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			String target = st.nextToken();
			if (!domains.containsKey(target)) {
				graph.add(new ArrayList<>());
				domains.put(target, idx++);
			}
			int M = Integer.parseInt(st.nextToken());
			for (int j=0;j<M;j++) {
				String start = st.nextToken();
				if (!domains.containsKey(start)) {
					graph.add(new ArrayList<>());
					domains.put(start, idx++);
				}
				//System.out.println(domains);
				graph.get(domains.get(start)).add(domains.get(target));
			}
		}
		dfn = new int[idx];
		lows = new int[idx];
		sccId = new int[idx];
		onStack = new boolean[idx];

		for (int i=0;i<idx;i++) {
			if (dfn[i] == 0) tarjan(i);
		}

		// scc간 위상정렬
		int[] indegrees = new int[idx];
		long[] scores = new long[idx];
		for (int i=0;i<idx;i++) {
			scores[i] = 1;
		}
		// System.out.println(sccCounter);
		// System.out.println(Arrays.toString(sccId));
		for (int i = 0; i < idx; i++) {
			for (int next : graph.get(i)) {
				if (sccId[i] != sccId[next]) {
					indegrees[next]++;
				}
			}
		}
		Queue<Integer> q = new LinkedList<>();
		for (int i=0;i<idx;i++) {
			if (indegrees[i]==0) {
				q.add(i);
			}
		}
		// indegree가 없는 scc부터 bfs 하면서 scc 별 점수 책정
		//System.out.println(Arrays.toString(sccGraph));
		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int next : graph.get(cur)) {
				if (sccId[cur] != sccId[next]) {
					scores[next] += scores[cur];
					indegrees[next]--;
					if (indegrees[next] == 0) {
						q.add(next);
					}
				}
			}
		}
		//System.out.println(Arrays.toString(scores));
		// 속한 scc 찾기
		String targetSite = br.readLine();
		int targetNode = domains.get(targetSite);
		System.out.println(scores[targetNode]);
	}

	static void tarjan(int nodeNum) {
		dfn[nodeNum] = lows[nodeNum] = dfnCounter++;
		stack.add(nodeNum);
		onStack[nodeNum] = true;
		for (int next:graph.get(nodeNum)) {
			if (dfn[next] == 0) {
				tarjan(next);
				lows[nodeNum] = Math.min(lows[next], lows[nodeNum]);
			} else if (onStack[next]) {
				lows[nodeNum] = Math.min(dfn[next], lows[nodeNum]);
			}
		}

		if (dfn[nodeNum] == lows[nodeNum]) {
			while (true) {
				int popped = stack.pop();
				onStack[popped] = false;
				sccId[popped] = sccCounter;
				if (popped == nodeNum) break;
			}
			sccCounter++;
		}
	}
}
