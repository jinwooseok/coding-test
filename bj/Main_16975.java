import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16975 {
	/*
	1. 특정 구간에 k를 더하는 쿼리
	2. 특정 숫자를 출력

	일단 long
	세그먼트 트리인가? 구간에 k를 더한다고 쳐.
	그러면 create없이 update를 통해 매번 특정 구간에 k를 더해준다.
	출력할 때는 얼마나 더해졌는지만 트리를 타고 내려가면서 총합을 더해준다.
	 */
	static long[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		tree = new long[(N+1)*4];
		st = new StringTokenizer(br.readLine());
		for (int i=1;i<N+1;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int M = Integer.parseInt(br.readLine());
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int query = Integer.parseInt(st.nextToken());
			if (query == 1) {
				int left = Integer.parseInt(st.nextToken());
				int right = Integer.parseInt(st.nextToken());
				int sum = Integer.parseInt(st.nextToken());
				update(1,1, N, left, right, sum);
			} else {
				int index = Integer.parseInt(st.nextToken());
				//System.out.println(search(1, 1, N, index));
				sb.append(arr[index]+search(1, 1, N, index)).append("\n");
			}
		}
		System.out.println(sb);
	}
	public static long search(int node, int start, int end, int index) {
		long sum = 0;
		if (start == end) {
			return tree[node];
		}
		sum += tree[node];
		int mid = (start+end)/2;
		if (index<=mid) {
			sum+=search(node*2, start, mid, index);
		} else {
			sum+=search(node*2+1, mid+1, end, index);
		}
		return sum;
	}
	public static void update(int node, int start, int end, int left, int right, int sum) {
		// left, right : 바꾸고자 하는 범위
		// start, end : 현재 범위
		if (start>right || end<left) return;
		// System.out.println(start+" "+end+" "+left+" "+right);
		if (start>=left && end<=right) {
			tree[node] += sum;
			// System.out.println(sum);
			return;
		}
		int mid = (start+end)/2;
		update(node*2, start, mid, left, right, sum);
		update(node*2+1, mid+1, end, left, right, sum);
	}
}
