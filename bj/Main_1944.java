import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

class Main_1944 {
	/*
	열쇠가 있는 곳, 출발 위치에 복제 장치가 있음
	4방향 이동, 모든 열쇠 찾을 때까지 로봇 이동 횟수 합을 최소
	하나의 칸에 여러 로봇이 위치할 수 있음.
	한번 지나간 자리도 다시 지나갈 수 있음
	로봇 이동 횟수 합은 모든 로봇이 움직인 회수의 총합

	1:벽, 0:이동 가능, S: 출발 위치, K: 열쇠 위치
	DFS와 로봇 배열 활용(로봇이 움직이지 않고 멈추는 경우도 고려해야 할듯)
	이동 가능 경우의 수 : 5
	배열 크기 : 2500
	최대 복제된 로봇 수: 250

	복제 위치 도달 시 복제 가능함.

	한번 시행 체크 시 복제된 로봇이 모두 움직이는 경우의 수는 4**250 이므로 불가능함.
	무조건 복제함을 가정? 복제를 안하면 효율을 높일 순 있음.
	전체를 최단거리로 만든다.

	모든 노드를 방문해야 함

	1 - 2 - 3
	   / \
	  4   5
	 1. 모든 노드를 방문하는 최단 거리를 찾기
	 2. 최단거리 알고리즘을 사용해 모든 노드를 하나로 이음
	 3. 어차피 본체가 가든 복제가 가든 횟수는 똑같음. 그러므로 이 최단거리가 즉 움직인 횟수의 총합임.
	 4. 근데 이제 예외가 있는가?
	 	갈래가 여러개인 경우 현재 로봇 수로 한번에 가기 어려운 경우가 있음.
	 	그러면 갔다가 돌아와야 함.
	 	그 비용까지 고려하면 최단거리가 아닐수도 있음
	 	그리고 시작 위치에 따라서도 달라짐
	 	노드 수는 250개. 나쁘지 않음.
	 	경로 수는 최대 30000개 정도 대략.
	 	로봇 복제는 원하는 만큼 할 수 있어서 예외 없음
	 즉, 최단거리 알고리즘 (크루스컬 알고리즘 진행)
	1. 엣지 길이 측정
	2. 엣지들을 PQ에 삽입 (짧은거부터)
	3. 짧은거부터 꺼내면서 순환이 안되는거대로 진행
		순환 체크 -> UNION-FIND
		양쪽 노드가 이미 한 집합이면 순환 발생
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		PriorityQueue<Edge> pq = new PriorityQueue<>();

		int[][] arr = new int[N][N];
		int[] parents = new int[M+3];
		for (int i=0;i<M+3;i++) {
			parents[i] = i;
		}
		int[][] nodes = new int[M+3][2];
		// 2500 * 250 으로 각 노드에서 다른 노드로의 거리 측정.
		int nodeNum = 2;
		for (int i=0;i<N;i++) {
			String line = br.readLine();
			for (int j=0;j<N;j++) {
				arr[i][j] = line.charAt(j)-'0';
				if (arr[i][j] == 'K'-'0' || arr[i][j] == 'S'-'0') {
					arr[i][j] = nodeNum;
					nodes[nodeNum][0] = i;
					nodes[nodeNum][1] = j;
					nodeNum++;
				}
			}
		}
		// for (int i=0;i<N;i++) {
		// 	System.out.println(Arrays.toString(arr[i]));
		// }
		Queue<int[]> q = new ArrayDeque<>();
		int[][] MOVES = {{0,1},{1,0},{0,-1},{-1,0}};
		boolean[][] visited;
		for (int i=2;i<nodes.length;i++) {
			visited = new boolean[N][N];
			q.add(new int[]{nodes[i][0],nodes[i][1], 0});
			visited[nodes[i][0]][nodes[i][1]] = true;
			while (!q.isEmpty()) {
				int[] node = q.poll();
				if (arr[node[0]][node[1]] > 1) {
					pq.add(new Edge(i, arr[node[0]][node[1]], node[2]));
				}
				for (int[] move:MOVES) {
					int nr=node[0]+move[0];
					int nc=node[1]+move[1];
					if (nr<0 || nc<0 || nr>=N || nc>=N || visited[nr][nc] || arr[nr][nc]==1) continue;
					visited[nr][nc] = true;
					q.add(new int[]{nr, nc, node[2]+1});
				}
			}
		}

		// 크루스컬 알고리즘
		int totalWeight = 0;
		while (!pq.isEmpty()) {
			Edge edge = pq.poll();
			// 같은 그룹이면 생성 pass 아니면 가중치 추가 후 union
			if (!isSameGroup(parents, edge.node1, edge.node2)) {
				totalWeight += edge.weight;
				union(parents, edge.node1, edge.node2);
			}
		}
		Set<Integer> groups = new HashSet<>();
		for (int i=2;i<M+3;i++) {
			groups.add(parents[i]);
		}
		// System.out.println(Arrays.toString(parents));
		if (groups.size()>1) {
			System.out.println(-1);
		} else {
			System.out.println(totalWeight);
		}
 	}

	public static int find(int[] parents, int x) {
		if (parents[x] == x) return x;
		return parents[x] = find(parents, parents[x]);
	}

	public static void union(int[] parents, int a, int b) {
		int ap = find(parents, a);
		int bp = find(parents, b);
		if (ap < bp) {
			parents[bp] = ap;
		} else {
			parents[ap] = bp;
		}
	}

	public static boolean isSameGroup(int[] parents, int a, int b) {
		return find(parents, a) == find(parents, b);
	}
}
class Edge implements Comparable<Edge> {
	int node1;
	int node2;
	int weight;

	Edge(int a, int b, int weight) {
		node1 = a;
		node2 = b;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.weight, o.weight);
	}
}
