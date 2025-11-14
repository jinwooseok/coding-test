import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1014 {
	static int source, sink;
	static List<Edge>[] graph;
	static int[] level;
	static int[] work;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t=0;t<T;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			source = 0;
			sink = N*M+1;
			graph = new ArrayList[sink+1];
			level = new int[sink+1];
			work = new int[sink+1];
			boolean[][] arr = new boolean[N][M];
			for (int i = 0; i < sink+1; i++) {
				graph[i] = new ArrayList<>();
			}

			for (int i=0;i<N;i++) {
				String line = br.readLine();
				for (int j=0;j<M;j++) {
					arr[i][j] = line.charAt(j) == '.';
				}
			}

			int totalSeats = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (!arr[i][j]) continue;
					totalSeats++;
					int nodeNum = i*M + j + 1;
					if (j % 2 == 0) {
						// Source -> 흑색 노드
						addEdge(source, nodeNum, 1);

						// 흑색 노드 -> 인접한 백색 노드들
						int[][] dirs = {{0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

						for (int[] dir : dirs) {
							int ni = i + dir[0];
							int nj = j + dir[1];

							// 범위 체크 && 앉을 수 있는 자리인지 체크
							if (ni >= 0 && ni < N && nj >= 0 && nj < M && arr[ni][nj]) {
								int adjacentNode = ni * M + nj + 1;
								addEdge(nodeNum, adjacentNode, 1);
							}
						}
					} else {
						// 백색 노드 -> Sink
						addEdge(nodeNum, sink, 1);
					}
				}
			}
			int maxMatching = dinic();
			sb.append(totalSeats - maxMatching).append('\n');
		}
		System.out.println(sb);
	}

	public static class Edge {
		int to;
		int capacity;
		int flow;
		Edge reverse;

		// 생성자
		Edge(int to, int capacity) {
			this.to = to;
			this.capacity = capacity;
			this.flow = 0;
		}

		// 잔여 유량
		int residual() {
			return capacity - flow;
		}

		// 유량 업데이트 - 반대 방향은 그만큼 빼주고 정방향은 그만큼 더해줌
		void addFlow(int f) {
			flow += f;
			reverse.flow -= f;
		}
	}

	// 엣지 설정
	public static void addEdge(int from, int to, int capacity) {
		Edge forward = new Edge(to, capacity);
		Edge backward = new Edge(from, 0); // 역방향은 처음엔 0임

		forward.reverse = backward;
		backward.reverse = forward;

		// graph와 edge의 reverse는 서로 참조 관계. 이후에 업데이트 할때 동시에 업데이트되는 효과가 있음
		graph[from].add(forward);
		graph[to].add(backward);
	}

	public static boolean bfs() {
		Arrays.fill(level, -1);
		level[source] = 0;

		Queue<Integer> q = new LinkedList<>();
		q.offer(source);

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (Edge edge: graph[cur]) {
				if (level[edge.to] == -1 && edge.residual() > 0) {
					level[edge.to] = level[cur] + 1;
					q.offer(edge.to);
				}
			}
		}

		return level[sink] != -1;
	}

	public static int dfs(int cur, int flow) {
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

	public static int dinic() {
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
}
