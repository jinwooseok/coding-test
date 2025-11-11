import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_16978 {
	static long[] tree;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1;i<N+1;i++) {
			arr[i] =  Integer.parseInt(st.nextToken());
		}
		tree = new long[(N+1)*4];
		init(1, 1, N);
		List<int[]> queryOne = new ArrayList<>();
		PriorityQueue<Query> queries = new PriorityQueue<>();
		int M = Integer.parseInt(br.readLine());
		int index = 0;
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int method = Integer.parseInt(st.nextToken());
			if (method == 1) {
				int[] query = new int[2];
				query[0] = Integer.parseInt(st.nextToken());
				query[1] = Integer.parseInt(st.nextToken());
				queryOne.add(query);
			} else {
				int queryNum = Integer.parseInt(st.nextToken());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				queries.add(new Query(start, end, index++, queryNum));
			}
		}
		long[] result = new long[queries.size()];
		int num = 0;
		while(!queries.isEmpty()) {
			Query query = queries.poll();
			while (num < query.queryNum) {
				int pos = queryOne.get(num)[0];
				int newVal = queryOne.get(num)[1];
				long diff = newVal - arr[pos];
				update(1, 1, N, pos, diff);
				arr[pos] = newVal;
				num++;
			}
			result[query.idx] = search(1, 1, N, query.start, query.end);
		}
		StringBuilder sb = new StringBuilder();
		for (long l : result) {
			sb.append(l).append("\n");
		}
		System.out.println(sb);
	}

	static class Query implements Comparable<Query> {
		int start;
		int end;
		int idx;
		int queryNum;

		Query(int start, int end, int idx, int queryNum) {
			this.start = start;
			this.end = end;
			this.idx = idx;
			this.queryNum = queryNum;
		}

		@Override
		public int compareTo(Query o) {
			return this.queryNum - o.queryNum;
		}
	}

	static long init(int node, int start, int end) {
		if (start == end) {
			return tree[node] = arr[start];
		}
		int mid = (start + end) / 2;
		return tree[node] = init(node*2, start, mid) + init(node*2+1, mid+1, end);
	}

	public static void update(int node, int start, int end, int index, long diff) {
		if (index < start || index > end) return;

		tree[node] += diff;

		if (start != end) {
			int mid = (start + end) / 2;
			update(node*2, start, mid, index, diff);
			update(node*2+1, mid+1, end, index, diff);
		}
	}

	public static long search(int node, int start, int end, int left, int right) {
		if (right < start || left > end) return 0;  // 범위 밖
		if (left <= start && right >= end) return tree[node];

		int mid = (start + end) / 2;
		return search(node*2, start, mid, left, right) +
			search(node*2+1, mid+1, end, left, right);
	}
}
