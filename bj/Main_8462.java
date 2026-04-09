import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_8462 {
	/*
	N개로 이뤄진 배열
	부분 수열 안에 있는 s의 개수
	각 수 * 개수의 제곱
	부분 배열

	mo's

	x*2*2+y*3*3 = result
	인 상태에서 x에 +1되면
	result-x*2*2+x*3*3

	1 1 1 -1 1 -1
	1 2 3 2 4 3

	1:[1]
	2:[2,4]
	3:[3,6]
	4:[5]



	 */
	static int[] arr, counts;
	static long[] answer;
	static Query[] queries;
	static long total;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		total = 0;
		arr = new int[N];
		counts = new int[1_000_001];
		answer = new long[M];
		queries = new Query[M];

		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int sqrtN = (int) Math.sqrt(N);
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			queries[i] = new Query(a, b, a/sqrtN, i);
		}
		Arrays.sort(queries);

		Query prevQ = queries[0];
		prevQ.firstQuery();
		// System.out.println(total);
		for (int i=1;i<M;i++) {
			// System.out.println(queries[i]);
			queries[i].query(prevQ.start, prevQ.end);
			prevQ = queries[i];
		}

		StringBuilder sb = new StringBuilder();
		for (int i=0;i<M;i++) {
			sb.append(answer[i]).append("\n");
		}
		System.out.println(sb);
	}

	static class Query implements Comparable<Query> {
		int start;
		int end;
		int groupId;
		int idx;
		Query(int start, int end, int groupId, int idx) {
			this.start = start;
			this.end = end;
			this.groupId = groupId;
			this.idx = idx;
		}

		@Override
		public int compareTo(Query o) {
			if (this.groupId == o.groupId) {
				return Integer.compare(this.end, o.end);
			}
			return Integer.compare(this.groupId, o.groupId);
		}

		public void firstQuery() {
			for (int i=start;i<=end;i++) {
				total -= (long) arr[i]*counts[arr[i]]*counts[arr[i]];
				counts[arr[i]]+=1;
				// Sy/stem.out.println(counts[i]);
				total += (long) arr[i]*counts[arr[i]]*counts[arr[i]];
				// System.out.println(total);
				answer[idx] = total;
			}
		}

		public void query(int prevS, int prevE) {
			while (!(prevS == this.start && prevE == this.end)) {
				if (prevS < this.start) {
					total -= (long) arr[prevS]*counts[arr[prevS]]*counts[arr[prevS]];
					counts[arr[prevS]]-=1;
					total += (long) arr[prevS]*counts[arr[prevS]]*counts[arr[prevS]];
					prevS++;
				} else if (prevS > this.start) {
					prevS--;
					total -= (long) arr[prevS]*counts[arr[prevS]]*counts[arr[prevS]];
					counts[arr[prevS]]+=1;
					total += (long) arr[prevS]*counts[arr[prevS]]*counts[arr[prevS]];
				}

				if (prevE < this.end) {
					prevE++;
					total -= (long) arr[prevE]*counts[arr[prevE]]*counts[arr[prevE]];
					counts[arr[prevE]]+=1;
					total += (long) arr[prevE]*counts[arr[prevE]]*counts[arr[prevE]];
				} else if (prevE > this.end) {
					total -= (long) arr[prevE]*counts[arr[prevE]]*counts[arr[prevE]];
					counts[arr[prevE]]-=1;
					total += (long) arr[prevE]*counts[arr[prevE]]*counts[arr[prevE]];
					prevE--;
				}
			}
			answer[idx] = total;
		}

		@Override
		public String toString() {
			return start+" "+end+" "+groupId+" "+idx;
		}
	}
}
