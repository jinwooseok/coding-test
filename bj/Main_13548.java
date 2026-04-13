import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_13548 {
	static int[] arr;
	static Query[] queries;
	static int[] result;
	static int maxCnt;
	static int[] count;
	static int[] countSet;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		maxCnt = 0;
		countSet = new int[100001];
		count = new int[100001];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int M = Integer.parseInt(br.readLine());
		queries = new Query[M];
		result = new int[M];
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken())-1;
			int end = Integer.parseInt(st.nextToken())-1;
			queries[i] = new Query(start, end, start/(int)Math.sqrt(N), i);
		}

		Arrays.sort(queries);
		// System.out.println(Arrays.toString(queries));
		queries[0].firstQuery();
		Query prev = queries[0];
		for (int i=1;i<M;i++) {
			queries[i].query(prev);
			prev = queries[i];
		}

		StringBuilder sb = new StringBuilder();
		for (int i=0;i<M;i++) {
			sb.append(result[i]).append("\n");
		}

		System.out.println(sb);
	}

	static class Query implements Comparable<Query> {
		int start;
		int end;
		int groupId;
		int idx;

		public Query(int start, int end, int groupId, int idx) {
			this.start = start;
			this.end = end;
			this.groupId = groupId;
			this.idx = idx;
		}

		public void firstQuery() {
			for (int i=this.start;i<=this.end;i++) {
				countSet[count[arr[i]]] = Math.max(0, countSet[count[arr[i]]]-1);
				count[arr[i]]++;
				countSet[count[arr[i]]]++;

				maxCnt = Math.max(maxCnt, count[arr[i]]);
			}
			result[idx] = maxCnt;
		}

		public void query(Query prev) {
			int prevS = prev.start;
			int prevE = prev.end;
			while (!(this.start == prevS && this.end == prevE)) {
				if (this.start < prevS) {
					prevS--;
					countSet[count[arr[prevS]]] = Math.max(0, countSet[count[arr[prevS]]]-1);
					count[arr[prevS]]++;
					countSet[count[arr[prevS]]]++;

					maxCnt = Math.max(maxCnt, count[arr[prevS]]);
				} else if (this.start > prevS) {
					countSet[count[arr[prevS]]] = Math.max(0, countSet[count[arr[prevS]]]-1);
					if (count[arr[prevS]] == maxCnt && countSet[count[arr[prevS]]] == 0) {
						maxCnt = count[arr[prevS]]-1;
					}
					count[arr[prevS]]--;
					countSet[count[arr[prevS]]]++;
					prevS++;
				}

				if (this.end < prevE) {
					countSet[count[arr[prevE]]] = Math.max(0, countSet[count[arr[prevE]]]-1);
					if (count[arr[prevE]] == maxCnt && countSet[count[arr[prevE]]] == 0) {
						maxCnt = count[arr[prevE]]-1;
					}
					count[arr[prevE]]--;
					countSet[count[arr[prevE]]]++;
					prevE--;
				} else if (this.end > prevE) {
					prevE++;
					countSet[count[arr[prevE]]] = Math.max(0, countSet[count[arr[prevE]]]-1);
					count[arr[prevE]]++;
					countSet[count[arr[prevE]]]++;

					maxCnt = Math.max(maxCnt, count[arr[prevE]]);
				}
			}

			result[idx] = maxCnt;
		}

		@Override
		public int compareTo(Query o) {
			if (o.groupId == this.groupId) {
				if (this.groupId%2==0) {
					return Integer.compare(this.end, o.end);
				} else {
					return Integer.compare(o.end, this.end);
				}
			}
			return Integer.compare(this.groupId, o.groupId);
		}

		@Override
		public String toString() {
			return groupId+" "+start+" "+end;
		}
	}
}
/*
10
1 1 2 2 2 3 3 3 3 3
5
1 10
1 5
6 10
3 8
2 9
 */