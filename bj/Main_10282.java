import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_10282 {
	/*
	의존하면 감염됨.
	총 몇대의 컴퓨터가 감염되는지 확인 (union-find)

	1. 그래프 구성 (의존성 순)
	-
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		Computer[] computers;
		boolean[] visited;
		StringBuilder sb = new StringBuilder();
		for (int t=0;t<T;t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			computers = new Computer[n+1];
			visited = new boolean[n+1]; // 이미 감염됐는지 여부
			visited[c] = true;
			for (int i=1;i<n+1;i++) {
				computers[i] = new Computer();
			}
			for (int i=0;i<d;i++) {
				st = new StringTokenizer(br.readLine());
				// b부터 시작 하도록. b의 자식은 a
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				computers[b].children.add(new int[] {a,s}); // a는 b가 감염된지 s초 후 감염
			}

			PriorityQueue<Dependency> pq = new PriorityQueue<>();
			for (int[] child:computers[c].children) {
				pq.add(new Dependency(child[0], child[1], child[1]));
			}
			int totalTime = 0;

			while (!pq.isEmpty()) {
				Dependency dp = pq.poll();
				if (visited[dp.next]) continue;
				visited[dp.next] = true;
				totalTime = Math.max(dp.total, totalTime);
				for (int[] next:computers[dp.next].children) {
					pq.add(new Dependency(next[0], next[1], dp.total+next[1]));
				}
			}

			int totalComputer = 0;
			for (int i=1;i<n+1;i++) {
				if (visited[i]) totalComputer++;
			}
			sb.append(totalComputer)
				.append(" ")
				.append(totalTime)
				.append("\n");
		}
		System.out.println(sb);
	}
	static class Computer {
		List<int[]> children;
		Computer() {
			children = new ArrayList<>();
		}
	}

	static class Dependency implements Comparable<Dependency> {
		int next;
		int time;
		int total;
		Dependency(int next, int time, int total) {
			this.next = next;
			this.time = time;
			this.total = total;
		}
		@Override
		public int compareTo(Dependency o) {
			return Integer.compare(this.total, o.total);
		}
	}
}
