import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_11281 {
	static int[] dfn;
	static int[] lows;
	static int[] sccId;
	static Stack<Integer> stack;
	static boolean[] onStack;
	static int sccCounter;
	static int dfnCounter;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		dfn = new int[2*N+1];
		lows = new int[2*N+1];
		sccId = new int[2*N+1];
		onStack = new boolean[2*N+1];
		sccCounter = 1;
		dfnCounter = 1;
		stack = new Stack<>();

		List<Integer>[] graph = new ArrayList[2*N+1];
		for (int i=1;i<2*N+1;i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int aNeg;
			int bNeg;
			if (a<0) {
				a = Math.abs(a)+N;
				aNeg = a-N;
			} else {
				aNeg = a+N;
			}

			if (b<0) {
				b = Math.abs(b)+N;
				bNeg = b-N;
			} else {
				bNeg = b+N;
			}
			graph[aNeg].add(b);
			graph[bNeg].add(a);
		}
		// System.out.println("=== GRAPH ===");
		// for (int i=1;i<=2*N;i++) {
		// 	if (!graph[i].isEmpty()) {
		// 		System.out.println(i + " -> " + graph[i]);
		// 	}
		// }
		// 타잔 진행
		for (int i=1;i<2*N+1;i++) {
			if (dfn[i] == 0) tarjan(graph, i);
		}

		// true false 판결 및 진리값 확정
		int possible = 1;
		StringBuilder sb = new StringBuilder();

		// System.out.println("=== DEBUG ===");
		// for (int i=1;i<=N;i++) {
		// 	System.out.println("x" + i + ": sccId = " + sccId[i] + ", ¬x" + i + ": sccId = " + sccId[N+i]);
		// }
		// System.out.println("=============");

		for (int i=1;i<=N;i++) {
			if (sccId[i] == sccId[N+i]) {
				possible = 0;
				break;
			}
			sb.append(sccId[i] < sccId[N+i] ? 1 : 0).append(" ");
		}

		if (possible==0)
			System.out.println(0);
		else {
			System.out.println(1);
			System.out.println(sb);
		}
	}

	static void tarjan(List<Integer>[] graph, int node) {
		dfn[node] = lows[node] = dfnCounter++;
		stack.push(node);
		onStack[node] = true;
		for (int next: graph[node]) {
			if (dfn[next] == 0) {
				tarjan(graph, next);
				lows[node] = Math.min(lows[node], lows[next]);
			} else if (onStack[next]) {
				lows[node] = Math.min(lows[node], dfn[next]);
			}
		}
		if (lows[node] == dfn[node]) {
			while (true) {
				int popped = stack.pop();
				onStack[popped] = false;
				sccId[popped] = sccCounter;
				if (popped == node) break;
			}
			sccCounter++;
		}
	}
}
