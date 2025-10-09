import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main_3977 {
	static int[] dfn;
	static int[] lows;
	static int[] sccId;
	static boolean[] onStack;
	static Stack<Integer> stack;
	static List<Integer>[] graph;
	static int dfnCounter;
	static int sccCounter;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(st.nextToken());
		for (int t=0;t<T;t++) {
			// 그래프 구성
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			graph = new ArrayList[N];
			dfn = new int[N];
			lows = new int[N];
			sccId = new int[N];
			onStack = new boolean[N];
			stack = new Stack<>();

			dfnCounter = 1;
			sccCounter = 1;

			for (int i=0;i<N;i++) {
				graph[i] = new ArrayList<>();
			}

			for (int i=0;i<M;i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph[a].add(b);
			}

			// 타잔
			for (int i=0;i<N;i++) {
				if (dfn[i] == 0) tarjan(i);
			}
			// 첫번째 scc의 요소들을 추출
			//System.out.println(Arrays.toString(sccId));
			boolean[] indegree = new boolean[sccCounter];

			for (int i=0;i<N;i++) {
				for (int next:graph[i]) {
					if (sccId[i] != sccId[next]) {
						indegree[sccId[next]] = true;
					}
				}
			}
			int sum = 0;
			int rootSccId = 0;
			for (int i=1;i<indegree.length;i++) {
				if (!indegree[i]) {
					sum++;
					rootSccId = i;
				}
			}

			if (sum == 1) {
				for (int i=0;i<N;i++) {
					if (sccId[i] == rootSccId) sb.append(i).append("\n");
				}
			} else {
				sb.append("Confused").append("\n");
			}

			sb.append("\n");
			br.readLine();
		}
		System.out.println(sb);
	}

	static void tarjan(int n) {
		dfn[n] = lows[n] = dfnCounter++;
		stack.push(n);
		onStack[n] = true;
		for (int next:graph[n]) {
			if (dfn[next]==0) {
				tarjan(next);
				lows[n] = Math.min(lows[next], lows[n]);
			} else if (onStack[next]) {
				lows[n] = Math.min(dfn[next], lows[n]);
			}
		}
		if (lows[n] == dfn[n]) {
			// System.out.println(stack);
			// System.out.println(Arrays.toString(lows));
			while (true) {
				int node = stack.pop();
				onStack[node] = false;
				sccId[node] = sccCounter;
				if (n == node) break;
			}
			sccCounter++;
		}
	}
}
