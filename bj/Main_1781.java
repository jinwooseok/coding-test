import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1781 {
	/*
	시간 내에 풀어야 컵라면을 줌.
	데드라인, 컵라면 수 순으로 정렬
	2165347
	1122336
	7654211
	시간 1일때 시간이 1인것 중에 가장큰거 선택
	시간 2일때 시간이 1,2인것 중에 가장 큰거 선택
	시간 3일때 시간이 1,2,3인것 중에 가장 큰거 선택
	시간 4일때 시간이 1,2,3,4인것 중에 가장 큰거 선택
	1. 우선순위큐로 컵라면 수가 가장 큰것을 뽑음 - 근데 컵라면 수가 가장크더라도 시간이 안될 수 있으니까 조회를 반복하게 되는 오류가 생김
	시간 우선 - 컵라면 둘째로 한다면? 어려움
	각 시간별로 우선순위 큐를 준비함.
	시간은 최대 200000, 개수도 200000개
	시간별로 가장큰것, 그다음 큰 것을 미리 저장.
	현재 시간보다 낮으면서 가장 큰 것 추출?
1 1
2 1
3 7
3 6
이면 시간 우선일때 3 6이 뽑히지 않음.. 계획 수정
1 6
2 7
3 1
	이라면 개수 우선일때 1,6을 놓치게 될수도 있음.
	단순 시간이나 개수 우선으로는 해결 불가
	1233
	1176

	123
	671
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		PriorityQueue<Ramen> pq1 = new PriorityQueue<>((a, b) -> Integer.compare(b.deadline, a.deadline));
		PriorityQueue<Ramen> pq2 = new PriorityQueue<>((a, b) -> Long.compare(b.count, a.count));
		int N = Integer.parseInt(br.readLine());
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());
			pq1.add(new Ramen(d,c));
		}
		long total = 0;
		while (N>0) {
			while (!pq1.isEmpty()) {
				Ramen next = pq1.poll();
				if (next.deadline<N) {
					pq1.add(next);
					break;
				} else {
					pq2.add(next);
				}
			}
			if (!pq2.isEmpty()) {
				Ramen best = pq2.poll();
				// System.out.println(best.deadline+" "+best.count);
				total+=best.count;
			}
			N--;
		}
		System.out.println(total);
	}

	public static class Ramen {
		int deadline;
		long count;

		Ramen(int deadline, long count) {
			this.deadline = deadline;
			this.count = count;
		}
	}
}
