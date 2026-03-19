import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_16118 {
	/*
	여우와 늑대아 있음
	1~N 번호의 N개의 나무 그루터기
	M개의 오솔길
	초기:1번
	여우는 일정한 속도, 늑대는 더 빠르지만 체력이 부족함.(2배속도, 절반속도를 반복함)
	달빛여우: 각자가 빨리 다다를 수 있는 경로로 이동
	여우가 먼저 도착할 수 있는 그루터기의 개수 찾기
	경로는 최대 100000개. 경로 위주가 유리함
	다익스트라를 쓰면? 이전 늑대의 속도를 기준으로 다음거의 거리를 책정해야할듯
	다익스트라는 (N*N)도 안됨. 쌉가능
	거리 리스트와 pq 사용하기.
	두마리를 동시에 이동 vs 각각 이동 후 체크
	동시 이동 시 메모리를 고려했을 때 각각 이동으로 우선 해보자
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Node[] nodes = new Node[N+1];
		for (int i=1;i<=N;i++) {
			nodes[i] = new Node();
		}

		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			nodes[a].idjPoints.add(new int[]{b, d});
			nodes[b].idjPoints.add(new int[]{a, d});
		}
		PriorityQueue<Line> pq = new PriorityQueue<>();

		boolean[] visited = new boolean[N+1];
		pq.add(new Line(1,0, 0));
		double[][] wolf = new double[N + 1][2];
		double[] fox = new double[N + 1];
		for (int i=1;i<N+1;i++) {
			wolf[i][0] = Integer.MAX_VALUE;
			wolf[i][1] = Integer.MAX_VALUE;
			fox[i] = Integer.MAX_VALUE;
		}
		while (!pq.isEmpty()) {
			Line line = pq.poll();
			if (visited[line.nextId]) continue;
			visited[line.nextId] = true;
			fox[line.nextId] = line.weight;
			for (int[] next:nodes[line.nextId].idjPoints) {
				if (visited[next[0]]) continue;
				pq.add(new Line(next[0], line.weight+next[1], 1));
			}
		}
		pq = new PriorityQueue<>();

		boolean[][] visited2 = new boolean[N+1][2];
		pq.add(new Line(1,0,2f));
		// visited2[1][0] = true;
		while (!pq.isEmpty()) {
			Line line = pq.poll();
			if (line.speed == 0.5f) {
				if (visited2[line.nextId][0]) continue;
				visited2[line.nextId][0] = true;
				wolf[line.nextId][0] = line.weight;
			} else {
				if (visited2[line.nextId][1]) continue;
				visited2[line.nextId][1] = true;
				wolf[line.nextId][1] = line.weight;
			}
			for (int[] next:nodes[line.nextId].idjPoints) {
				if (line.speed == 0.5f) {
					if (visited2[next[0]][1]) continue;
					pq.add(new Line(next[0], line.weight+next[1]*2, 2));
				} else {
					if (visited2[next[0]][0]) continue;
					pq.add(new Line(next[0], (float)(line.weight+next[1]*0.5), 0.5f));
				}
			}
		}
		int count = 0;
		// for (int i=0;i<N+1;i++) {
		// 	System.out.println(wolf[i][0]);
		// 	System.out.println(wolf[i][1]);
		// }
		// System.out.println(Arrays.toString(fox));
		for (int i=2;i<=N;i++) {
			if (wolf[i][0]>fox[i] && wolf[i][1]>fox[i]) {
				count++;
			}
		}
		System.out.println(count);
	}

	static class Node {
		List<int[]> idjPoints;
		Node() {
			idjPoints = new ArrayList<>();
		}
	}

	static class Line implements Comparable<Line> {
		int nextId;
		double weight;
		float speed;
		Line(int nextId, double weight, float speed) {
			this.nextId = nextId;
			this.weight = weight;
			this.speed = speed;
		}

		@Override
		public int compareTo(Line o) {
			return Double.compare(this.weight, o.weight);
		}
	}
}
