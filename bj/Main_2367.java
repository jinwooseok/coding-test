import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2367 {
	static int N, K, D;
	static int source, sink;
	static int[] level;
	static int[] work;
	static List<Edge>[] graph;

	static class Edge {
		int to;
		int capacity;
		int flow;
		Edge reverse;

		Edge(int to, int capacity) {
			this.to = to;
			this.capacity = capacity;
			this.flow = 0;
		}

		int residual() {
			return capacity - flow;
		}

		void addFlow(int f) {
			flow += f;
			reverse.flow -= f;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		source = 0;
		sink = N + D + 1;
		int totalNodes = N + D + 2;

		graph = new ArrayList[totalNodes];
		level = new int[totalNodes];
		work = new int[totalNodes];

		for (int i = 0; i < totalNodes; i++) {
			graph[i] = new ArrayList<>();
		}

		int[] foodLimit = new int[D+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= D; i++) {
			foodLimit[i] = Integer.parseInt(st.nextToken());
		}

		for (int person = 1; person <= N; person++) {
			addEdge(source, person, K);
		}

		for (int food = 1; food <= D; food++) {
			addEdge(N + food, sink, foodLimit[food]);
		}

		for (int person = 1; person <= N; person++) {
			st = new StringTokenizer(br.readLine());
			int Z = Integer.parseInt(st.nextToken());

			for (int i = 0; i < Z; i++) {
				int food = Integer.parseInt(st.nextToken());
				addEdge(person, N + food, 1);
			}
		}

		// Dinic 알고리즘 실행
		System.out.println(dinic());
	}

	static void addEdge(int from, int to, int capacity) {
		Edge forward = new Edge(to, capacity);
		Edge backward = new Edge(from, 0);

		forward.reverse = backward;
		backward.reverse = forward;

		graph[from].add(forward);
		graph[to].add(backward);
	}

	static int dinic() {
		int maxFlow = 0;

		while (bfs()) {
			Arrays.fill(work, 0);

			while (true) {
				int flow = dfs(source, Integer.MAX_VALUE);
				if (flow == 0) break;
				maxFlow += flow;
			}
		}

		return maxFlow;
	}

	static boolean bfs() {
		Arrays.fill(level, -1);
		level[source] = 0;

		Queue<Integer> queue = new LinkedList<>();
		queue.offer(source);

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			for (Edge edge : graph[cur]) {
				if (level[edge.to] == -1 && edge.residual() > 0) {
					level[edge.to] = level[cur] + 1;
					queue.offer(edge.to);
				}
			}
		}

		return level[sink] != -1;
	}

	static int dfs(int cur, int flow) {
		if (cur == sink) return flow;

		for (int i = work[cur]; i < graph[cur].size(); i++) {
			work[cur] = i;
			Edge edge = graph[cur].get(i);

			if (level[edge.to] != level[cur] + 1) continue;
			if (edge.residual() <= 0) continue;

			int pushed = dfs(edge.to, Math.min(flow, edge.residual()));

			if (pushed > 0) {
				edge.addFlow(pushed);
				return pushed;
			}
		}

		return 0;
	}
}