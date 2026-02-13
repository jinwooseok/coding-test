import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_12764 {
	/*
	- 들어온 순서대로 번호가 작은거부터 앉는게 규칙
	- 최소 자리 수, 자리 별 사용한 사람 수 구하기

	N : 총 사람 수 (100000)
	P : 사람 별 이용 시작 시각 P (1000000)
	Q : 사람 별 이용 종료 시각 Q (1000000)

	시작 시간, 종료 시간은 각각 다른 사람과 겹치지 않음

	회의실 배정 - 필요한 회의실의 최소 개수. (회의실 1개를 가장 많이 쓰는 경우는 종료 시간 순으로 나열했을 때 시간이 빠른 순서이다. - 절대적으로 남은 시간적 공간이 많기 때문)

	1. 종료 시간 기준 우선순위 큐
	2. 먼저 온 사람을 1번 컴퓨터에 앉힘.
	3. 다음 사람의 시작 시간이 1번 사람의 종료시간보다 작으면 2번 컴퓨터에 앉힘
	4. 그 다음 사람이 왔을 때 1번 사람이 종료했는지 파악 (종료 시간 순이므로 1번 사람이 종료안했으면 당연히 2번사람도 종료안했음)
	5. 종료했다면 1번, 아니면 3번
	6. 그렇다면 종료한 자리, 종료안했을 때 배치자리는 어떻게 고르는가?
	- 가장 늦게 들어온 사람의 자리와 가장 먼저 들어온 사람의 자리를 파악해두기
	- 가장 먼저 들어온 사람의 자리와 종료 비교. 종료 했으면 그자리
	- 그게 종료 안됐으면 그 뒤사람들도 종료 안된거임. 가장 늦게 들어온 사람의 다음 자리에 앉힘.
	- 문제점. 1번 2번 비었고, 3번이 남아있다면?
	- 3번이 현재 가장먼저 온사람으로 4번에 앉게됨.
	- 1번 3번에 앉아있고 2번이 비었다면? deque로는 파악이 안됨.
	- 1,2,3 상황 -> 1번 나감 -> 다음사람 1번 들어옴 -> 2번 나감 : 이 케이스를 해결해야 함.
	deque를 사용하면 좋을 듯함. (쌍방 비교, q처럼 빼고 놓는 장점)
	각 자리별 stack 준비. 다음 사람 들어올 때마다 모든 자리 확인 -> 시간초과날듯
	- q로 들어오는 사람 넣기 -> 가장 옛날 것들 확인해서 종료시간 지나면 뺌 o(n)
	- 우선순위 큐에 빈자리를 넣고 빼기.
	- 처음에는 100000개를 넣어둠 (최대 사람 수)
	- 기록은 count 배열
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());

		// 컴퓨터 자리와 최종 결과 배열 (0인 컴퓨터가 마지막). 컴퓨터 자리 수는 최대 N개
		int[] counts = new int[N];
		Person[] people = new Person[N];
		PriorityQueue<Person> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.endTime, b.endTime));
		// 맨앞자리부터 컴퓨터 앉아야 한다는 규칙 (빈 자리를 넣어둘거임)
		PriorityQueue<Integer> emptyComputers = new PriorityQueue<>();
		for (int i=0;i<N;i++) {
			emptyComputers.add(i);
			st = new StringTokenizer(br.readLine());
			int startTime = Integer.parseInt(st.nextToken());
			int endTime = Integer.parseInt(st.nextToken());
			people[i] = new Person(startTime, endTime);
		}
		Arrays.sort(people);

		for (int i=0;i<N;i++) {
			Person current = people[i];
			// 사람을 제거하고, 그 자리를 emptyComputers에 넣는 작업 o(N)
			while (!pq.isEmpty()) {
				Person p = pq.peek();
				// 이미 종료됐다면?
				if (current.startTime < p.endTime) break;
				pq.poll();
				emptyComputers.add(p.computer);
			}
			// emptyComputers에서 가장 앞자리를 빼고, q에 넣기
			if (emptyComputers.isEmpty()) {
				break;
			}
			current.computer = emptyComputers.poll();
			pq.add(current);
			counts[current.computer]++;
		}
		StringBuilder sb = new StringBuilder();
		int total = 0;
		for (int i=0;i<N;i++) {
			if (counts[i] == 0) break;
			sb.append(counts[i]).append(" ");
			total++;
		}
		System.out.println(total);
		System.out.println(sb);
	}
	// 들어온 사람
	static class Person implements Comparable<Person> {
		int startTime;
		int endTime;
		int computer;

		Person(int startTime, int endTime) {
			this.startTime = startTime;
			this.endTime = endTime;
			this.computer = -1;
		}
		@Override
		public int compareTo(Person o) {
			return Integer.compare(this.startTime, o.startTime);
		}
	}
}
