import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_3648 {
	static ArrayList<Integer>[] graph;
	static ArrayList<Integer>[] reverseGraph;
	static boolean[] visited;
	static Stack<Integer> finishOrder;
	static int[] sccId;
	static int sccCount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		String line;

		while ((line = br.readLine()) != null && !line.isEmpty()) {
			st = new StringTokenizer(line);
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			// 그래프 초기화
			graph = new ArrayList[2*N + 1];
			reverseGraph = new ArrayList[2*N + 1];

			for (int i = 0; i <= 2*N; i++) {
				graph[i] = new ArrayList<>();
				reverseGraph[i] = new ArrayList<>();
			}

			// 투표 정보 처리
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());

				// 변수 인덱스 계산
				int p, pNeg, q, qNeg;

				if (a > 0) {
					p = a;
					pNeg = N + a;
				} else {
					p = N + Math.abs(a);
					pNeg = Math.abs(a);
				}

				if (b > 0) {
					q = b;
					qNeg = N + b;
				} else {
					q = N + Math.abs(b);
					qNeg = Math.abs(b);
				}

				graph[pNeg].add(q);
				reverseGraph[q].add(pNeg);

				graph[qNeg].add(p);
				reverseGraph[p].add(qNeg);
			}

			// 상근이(1번) 진출 강제: ¬x_1 → x_1
			graph[N + 1].add(1);
			reverseGraph[1].add(N + 1);

			// SCC 찾기
			findSCC(2*N);

			// 해 존재 여부 확인
			boolean possible = true;
			for (int i = 1; i <= N; i++) {
				int xi = i;
				int notXi = N + i;

				if (sccId[xi] == sccId[notXi]) {
					possible = false;
					break;
				}
			}

			if (possible) {
				sb.append("yes\n");
			} else {
				sb.append("no\n");
			}
		}

		System.out.print(sb);
	}

	// Kosaraju 알고리즘으로 SCC 찾기
	static void findSCC(int n) {
		// 1단계: 원래 그래프에서 DFS
		visited = new boolean[n + 1];
		finishOrder = new Stack<>();

		for (int i = 1; i <= n; i++) {
			if (!visited[i]) {
				dfs1(i);
			}
		}

		// 2단계: 역방향 그래프에서 역순으로 DFS
		visited = new boolean[n + 1];
		sccId = new int[n + 1];
		sccCount = 0;

		while (!finishOrder.isEmpty()) {
			int v = finishOrder.pop();
			if (!visited[v]) {
				dfs2(v, sccCount);
				sccCount++;
			}
		}
	}

	// 1단계 DFS: 끝나는 순서 기록
	static void dfs1(int v) {
		visited[v] = true;

		for (int next : graph[v]) {
			if (!visited[next]) {
				dfs1(next);
			}
		}

		finishOrder.push(v);
	}

	// 2단계 DFS: SCC 번호 할당
	static void dfs2(int v, int sccNum) {
		visited[v] = true;
		sccId[v] = sccNum;

		for (int next : reverseGraph[v]) {
			if (!visited[next]) {
				dfs2(next, sccNum);
			}
		}
	}
}