import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2150 {
	// scc의 개수 K 출력, SCC출력(각 SCC의 최소 번호 노드의 오름차순으로 호출, SCC내부에서도 오름차순)
	// sccId 추출
	// scc들을 각각 추출 후 scc 정렬 (최소번호 순)
	static int[] dfn;
	static int[] lows;
	static int[] sccId;
	static int dfnCounter;
	static int sccCounter;
	static boolean[] onStack;
	static Stack<Integer> stack;
	static List<Integer>[] graph;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		dfn = new int[V+1];
		lows = new int[V+1];
		sccId = new int[V+1];
		dfnCounter = 1;
		sccCounter = 0;
		onStack = new boolean[V+1];
		stack = new Stack<>();
		graph = new ArrayList[V+1];
		for (int i=0;i<V+1;i++) {
			graph[i] = new ArrayList<>();
		}
		for (int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
		}

		// tarjan
		for (int i=1;i<V+1;i++) {
			if (dfn[i]==0) tarjan(i);
		}
		SCC[] sccs = new SCC[sccCounter];
		for (int i=0;i<sccCounter;i++) {
			sccs[i] = new SCC(i);
		}
		for (int i=1;i<V+1;i++) {
			sccs[sccId[i]].list.add(i);
		}
		Arrays.sort(sccs);
		StringBuilder sb = new StringBuilder();
		sb.append(sccCounter).append("\n");
		for (SCC scc:sccs) {
			while (!scc.list.isEmpty()) {
				sb.append(scc.list.poll()).append(" ");
			}
			sb.append(-1).append("\n");
		}
		//System.out.println(Arrays.toString(sccId));
		System.out.println(sb);
	}

	public static void tarjan(int num) {
		dfn[num] = lows[num] = dfnCounter++;
		stack.push(num);
		onStack[num] = true;
		for (int next:graph[num]) {
			if (dfn[next]==0) {
				tarjan(next);
				lows[num] = Math.min(lows[num], lows[next]);
			} else if (onStack[next]) {
				lows[num] = Math.min(lows[num], dfn[next]);
			}
		}
		//System.out.println(Arrays.toString(lows));
		if (dfn[num]==lows[num]) {
			while (true) {
				int popped = stack.pop();
				onStack[popped] = false;
				sccId[popped] = sccCounter;
				if (popped==num) break;
			}
			sccCounter++;
		}
	}

	public static class SCC implements Comparable<SCC> {
		int sccId;
		PriorityQueue<Integer> list;
		SCC(int sccId) {
			this.sccId = sccId;
			list = new PriorityQueue<>();
		}
		@Override
		public int compareTo(SCC o) {
			return Integer.compare(this.list.peek(), o.list.peek());
		}
	}
}

