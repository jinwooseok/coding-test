import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1766 {
	/**
	 * 1~N (1~N 난이도순)
	 * 먼저 풀었을 때 쉽게 풀리는 문제가 있음
	 * N개 모두 풀어야함(완탐)
	 * 순서가 있음(반드시 지켜야함)
	 * 가능하면 쉬운 문제부터 풀어야 함
	 * 선수문제가 있음, 숫자 낮은거부터 먼저하는게 좋음 (선수문제 안풀면 못품)
	 * 그래프 준비()
	 * 선수문제를 다 풀었는가?(리스트와 선수문제의 개수 리스트로 표현) -> 선수문제를 탐색하고 수량 마이너스 하는데 100000회
	 * 1. 문제 -> 우선순위 큐 (우선순위는 선수문제 개수, 난이도 순)
	 * 2. 난이도 낮은거부터 차례로 꺼내기
	 * 3. 만약 선수문제개수 리스트가 0이라면 문제 해결
	 * 4. 선수문제개수가 0이 아니라면 다시 우선순위 큐 삽입 (선수문제개수도 우선순위로 본다면 선수문제가 0인 경우가 무조건 존재해야함. 그러므로 선수문제 개수가 0인 것 중 최소난이도 추출하여 진행)
	 *
	 * 문제는 선수문제개수를 어떻게 우선순위 큐에 추가하는가?
	 * 1. 초기에 선수문제개수가 0인것만 우선순위 큐에 추가
	 * 2. 꺼내서 선수문제개수 배열에 -진행, 진행하다가 0이된것들만 우선순위큐에 삽입,
	 * 3. 우선순위큐에 삽입된것을 다 빼서 다시 선수문제개수배열에 -진행. 하면서 0이 된것들만 우선순위큐에 삽입.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 선수문제 배열
		ArrayList[] prevProblems = new ArrayList[N+1];

		// 남은 선수문제 개수 배열
		int[] remainCounts = new int[N+1];

		// 선수문제 개수가 0인것들을 난이도순으로 정렬하는 우선순위 큐
		PriorityQueue<Problem> possibleProblems = new PriorityQueue<>();

		// 초기화
		for (int i=0;i<N+1;i++) {
			prevProblems[i] = new ArrayList<Integer>();
		}

		// input 삽입
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int prev = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			prevProblems[prev].add(next);
			remainCounts[next]++; // 다음문제 푸려면 남은 숫자 추가
		}

		// 큐 초기 삽입
		for (int i=1;i<N+1;i++) {
			if (remainCounts[i] == 0) possibleProblems.add(new Problem(i));
		}

		// 작업 진행
		System.out.println(execute(possibleProblems, prevProblems, remainCounts));
	}

	static String execute(PriorityQueue<Problem> problems,ArrayList<Integer>[] prevProblems, int[] remainCounts) {
		StringBuilder sb = new StringBuilder();

		while (!problems.isEmpty()) {
			Problem problem = problems.poll();
			sb.append(problem.grade).append(" ");
			for (int i=0;i<prevProblems[problem.grade].size();i++) {
				remainCounts[prevProblems[problem.grade].get(i)]-=1;
				if (remainCounts[prevProblems[problem.grade].get(i)] == 0) {
					problems.add(new Problem(prevProblems[problem.grade].get(i)));
				}
			}
		}

		return sb.toString();
	}

	static class Problem implements Comparable<Problem> {
		int grade;
		Problem(int grade) {
			this.grade = grade;
		}

		@Override
		public int compareTo(Problem o) {
			return Integer.compare(this.grade, o.grade);
		}
	}
}
