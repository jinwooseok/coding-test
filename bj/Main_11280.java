import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_11280 {
	static Stack<Integer> stack;
	static int[] dfn;
	static int[] low;
	static int[] sccIds;
	static int sccCounter;
	static int dfnCounter;
	static boolean[] onStack;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		// 노드를 표현할 배열 준비 (T:1~N, F:N+1~2N)

		dfn = new int[2*N+1];
		low = new int[2*N+1];
		sccIds = new int[2*N+1];
		sccCounter = 1;
		dfnCounter = 1;
		onStack = new boolean[2*N+1];

		stack = new Stack<>();

		List<Integer>[] nodes = new ArrayList[2*N+1];
		for (int i=1;i<2*N+1;i++) {
			nodes[i] = new ArrayList<>();
		}

		// 그래프 생성
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int aNeg, bNeg;
			int b = Integer.parseInt(st.nextToken());
			if (a<0) {
				a = Math.abs(a)+N;
				aNeg = a-N;
			}  else aNeg = a+N;
			if (b<0) {
				b = Math.abs(b)+N;
				bNeg = b-N;
			} else bNeg = b+N;
			// a가 아니라면 b여야 한다.
			nodes[aNeg].add(b);
			// b가 아니라면 a여야 한다.
			nodes[bNeg].add(a);
		}

		// 코사라주 or 타잔
		for (int i=1;i<2*N+1;i++) {
			if (dfn[i] != 0) continue;
			tarjan(nodes, i);
		}
		for (int i=1;i<=N;i++) {
			if (sccIds[i] == sccIds[i+N]) {
				System.out.println(0);
				return;
			}
		}
		System.out.println(1);
	}

	static void tarjan(List<Integer>[] graph, int v) {
		// 1. 방문 표시
		dfn[v] = low[v] = dfnCounter++;
		stack.push(v);
		onStack[v] = true;

		// 2. 인접 정점 탐색
		for (int next : graph[v]) {
			if (dfn[next] == 0) {
				// 2-1. 아직 방문 안 한 정점 (Tree Edge)
				tarjan(graph, next);
				low[v] = Math.min(low[v], low[next]);
			} else if (onStack[next]) {
				// 2-2. 스택에 있는 정점 (Back Edge)
				low[v] = Math.min(low[v], dfn[next]);
			}
			// else: 이미 SCC로 확정된 정점 (Cross Edge) - 무시
		}

		// 3. SCC 루트 판정
		if (dfn[v] == low[v]) {
			// v가 SCC의 루트!
			while (true) {
				int w = stack.pop();
				onStack[w] = false;
				sccIds[w] = sccCounter;
				if (w == v)
					break;
			}
			sccCounter++;
		}
	}
}
