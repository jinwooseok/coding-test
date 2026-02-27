import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_21939 {
	/*
	추천 문제 리스트 중, 가장 높은 난이도를 가진 문제 (문제 번호가 큰 것 출력)
	추천 문제 리스트 중, 가장 낮은 난이도를 가진 문제 (문제 번호가 작은것 출력)
	추천 문제 리스트에 문제 번호가 다른 난이도로 들어오면? 그것도 그대로 적용됨.
	문제 번호로 제거할땐 한꺼번에 제거가 되어야 함.

	문제 번호 boolean[] : 해당 문제의 상태. solved 가 true라면 우선순위 큐를 한번 더 돌리도록
	추천 우선순위 큐: 난이도 우선, 문제번호 순

	 */
	static PriorityQueue<Problem> highs;
	static PriorityQueue<Problem> lows;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		highs = new PriorityQueue<>(new Comparator<Problem>() {
			@Override
			public int compare(Problem o1, Problem o2) {
				if (o1.level == o2.level) {
					return Integer.compare(o2.problemId, o1.problemId);
				}
				return Integer.compare(o2.level, o1.level);
			}
		});

		lows = new PriorityQueue<>((o1, o2) -> {
			if (o1.level == o2.level) {
				return Integer.compare(o1.problemId, o2.problemId);
			}
			return Integer.compare(o1.level, o2.level);
		});
		int N = Integer.parseInt(br.readLine());
		int[] solved = new int[100001];
		int time = 0;
		Arrays.fill(solved, -1);
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int problemId = Integer.parseInt(st.nextToken());
			int level = Integer.parseInt(st.nextToken());
			Problem problem = new Problem(problemId, level, time);
			highs.add(problem);
			lows.add(problem);
		}

		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			int p, l, x;
			time++;
			switch (command) {
				case "add":
					p = Integer.parseInt(st.nextToken());
					l = Integer.parseInt(st.nextToken());
					add(solved, p, l, time);
					break;
				case "recommend":
					x = Integer.parseInt(st.nextToken());
					sb.append(recommend(solved, x)).append("\n");
					break;
				case "solved":
					p = Integer.parseInt(st.nextToken());
					solved(solved, p, time);
					break;
			}
		}
		System.out.println(sb);
	}
	public static int recommend(int[] solved, int x) {
		if (x == 1) {
			while (!highs.isEmpty()) {
				Problem high = highs.poll();
				if (solved[high.problemId]>=high.time) continue; // 지연된 제거
				highs.add(high);
				return high.problemId;
			}
			return -1;
		} else {
			while (!lows.isEmpty()) {
				Problem low = lows.poll();
				if (solved[low.problemId]>=low.time) continue; // 지연된 제거
				lows.add(low);
				return low.problemId;
			}
			return -1;
		}
	}

	public static void add(int[] solved, int p, int l, int time) {
		highs.add(new Problem(p, l, time));
		lows.add(new Problem(p, l, time));
	}

	public static void solved(int[] solved, int p, int time) {
		solved[p] = time;
	}

	static class Problem {
		int problemId;
		int level;
		int time;

		Problem(int problemId, int level, int time) {
			this.problemId = problemId;
			this.level = level;
			this.time = time;
		}
	}
}
