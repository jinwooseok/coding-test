import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_13547 {
	/*
	i ~ j에 서로 다른 수의 개수

	정렬문제
	그거그건데 구역을 루트 n개로 나눈 후
	오프라인 쿼리
	그 구역에서 투포인터
	그 이후 건너뛰는거

	1 1 | 2 1 | 3
	a | b | c
	그룹별로 쿼리를 정렬
	시작부, 마지막 부를 그룹 정렬, 세부 정렬은 뒷자리
	2 4 a,b
	1 5 a,c
	3 5 b,c

	a | b | c | d 그룹이 있을 때
	ab ad bc cd 가 있다면?
	ab -> ad 그룹점프 방법은 b,c의 모든것 포함(
	a기준 100000
	b기준

	1000000 사이즈 배열+정답을 갖고 다니며 투포인터 배열로 체크
	일반적인 투포인터는 얼마나 건너뛰게 될지 알기 어렵지만
	범위를 두고 정렬을 할 경우 logn의 범위 내에서 단조적으로 투포인터가 가능해짐
	그룹 투포인터 + 그룹 내 이동이라고 보면 될듯함

	 */
	static int[] arr, answers, curCnt;
	static int cnt;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());
		answers = new int[M];
		curCnt = new int[1000001];
		cnt = 0;
		Query[] qArr = new Query[M];
		int sqrtN = (int) Math.sqrt(N);
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			Query q = new Query(a, b, a/sqrtN, i);
			qArr[i] = q;
			// System.out.println(q);
		}
		Arrays.sort(qArr);
		Query prevQ = qArr[0];
		prevQ.firstQuery();
		// System.out.println(curSet);
		for (int i=1;i<M;i++) {
			Query q = qArr[i];
			q.query(prevQ.start, prevQ.end);
			// System.out.println(curSet);
			prevQ = q;
		}

		StringBuilder sb = new StringBuilder();
		for (int i=0;i<M;i++) {
			sb.append(answers[i]).append("\n");
		}
		System.out.println(sb);
	}

	static class Query implements Comparable<Query> {
		int groupId;
		int start;
		int end;
		int idx;

		public Query(int a, int b, int sqrt, int i) {
			this.start = a;
			this.end = b;
			this.groupId = sqrt;
			this.idx = i;
		}

		@Override
		public int compareTo(Query o) {
			if (o.groupId == this.groupId) {
				return Integer.compare(this.end, o.end);
			}
			return Integer.compare(this.groupId, o.groupId);
		}

		public void firstQuery() {
			for (int i=this.start;i<=this.end;i++) {
				if (curCnt[arr[i]] == 0) cnt++;
				curCnt[arr[i]]++;
			}
			answers[this.idx] = cnt;
		}

		public void query(int prevS, int prevE) {
			while (!(prevS == start && prevE == end)) {
				// System.out.println(prevS+" "+start);
				// System.out.println(prevE+" "+end);
				if (prevS<start) {
					curCnt[arr[prevS]]--;
					if (curCnt[arr[prevS]] == 0) cnt--;
					prevS++;
				} else if (prevS>start) {
					prevS--;
					if (curCnt[arr[prevS]] == 0) cnt++;
					curCnt[arr[prevS]]++;
				}

				if (prevE<end) {
					prevE++;
					if (curCnt[arr[prevE]] == 0) cnt++;
					curCnt[arr[prevE]]++;
				} else if (prevE>end) {
					curCnt[arr[prevE]]--;
					if (curCnt[arr[prevE]] == 0) cnt--;
					prevE--;
				}
			}
			// System.out.println(prevS+" "+start);
			// System.out.println(prevE+" "+end);
			// System.out.println(curSet);
			answers[this.idx] = cnt;
		}

		@Override
		public String toString() {
			return this.groupId+" "+this.start+" "+this.end;
		}
	}
}
