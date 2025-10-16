import java.io.*;
import java.util.*;
import java.util.function.IntBinaryOperator;

public class Main_17182 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<Node> nodes = new PriorityQueue<>();
		int n = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][n];
		for (int i=0;i<n;i++){
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<n;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		nodes.add(new Node(start, 0, 1<<start));
		// System.out.println(Integer.toBinaryString(4));
		// System.out.println(Integer.toBinaryString(1));
		int[][] dist = new int[n][1<<n];
		for (int i=0;i<n;i++) {
			Arrays.fill(dist[i], Integer.MAX_VALUE);
		}
		dist[start][1<<start] = 0;
		while (!nodes.isEmpty()) {
			Node cur = nodes.poll();

			// 이미 더 짧은 경로를 찾았으면 스킵
			//System.out.println(cur.end);
			if (dist[cur.end][cur.visited] < cur.dist) continue;

			// 목표 도달
			//System.out.println(cur.visited);
			if (cur.visited == (1<<n)-1) {
				//System.out.println(cur.dist);
				break;
			}

			// 다음 상태 탐색
			for (int i=0;i<n;i++) {
				if (i == cur.end) continue;
				int nextVisited = cur.visited | (1<<i);
				int nextDist = cur.dist + arr[cur.end][i];

				if (nextDist < dist[i][nextVisited]) {
					dist[i][nextVisited] = nextDist;
					nodes.add(new Node(i, nextDist, nextVisited));
				}
			}
		}
		// System.out.println(Arrays.toString(dist[start]));
		int minValue = Integer.MAX_VALUE;
		for (int i=0;i<n;i++) {
			minValue = Math.min(minValue, dist[i][(1<<n)-1]);
		}
		System.out.println(minValue);
	}
	static class Node implements Comparable<Node> {
		int end;
		int dist;
		int visited;
		Node(int end, int dist, int visited) {
			this.end = end;
			this.dist = dist;
			this.visited = visited;
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.dist, o.dist);
		}
	}
}
