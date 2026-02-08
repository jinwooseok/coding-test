import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2473 {
	/*
	3개를 선택
	3개의 합이 0에 가깝게 해야 함.
	최대 5000개, -10억 ~ 10억으로 존재.
	선택한 경우와 선택안한 경우로 dp?
	1. 정렬하기
	-97 -6 -2 6 98
	2. 5000*4999/2로 2개를 선택
	3. 나머지 한개의 최적을 이분탐색으로 선택
	예)
	-97, -6 선택 시
	103의 상한 하한 탐색
	only 98
	-97, -2 선택 시
	-99
	99의 상한 하한 탐색
	98
	-24 -6 -3 -2 61 98 100
	문제 - 중복이 안되게 배열에서 2개는 빼야함.
	어떻게?
	링크드리스트.
	전체 인덱스와 값 분포는
	 */
	static long minAbs = Long.MAX_VALUE;
	static long[] minFair = new long[]{Long.MAX_VALUE,Long.MAX_VALUE,Long.MAX_VALUE};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		long[] arr = new long[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		for (int i=0;i<N;i++) {
			for (int j=i+1;j<N-1;j++) {
				long target = -(arr[i]+arr[j]);
				long a = arr[i];
				long b = arr[j];
				//System.out.println(a+" "+b);
				binarySearch(arr, target, a, b, i, j);
			}
		}
		StringBuilder sb = new StringBuilder();
		Arrays.sort(minFair);
		for (long num:minFair) {
			sb.append(num).append(" ");
		}
		System.out.println(sb);
	}

	public static void binarySearch(long[] arr, long target, long a, long b, int i, int j) {
		int left = j+1;
		int right = arr.length-1;
		int mid;

		while (left<right) {
			mid = (left+right)/2;
			if (target>arr[mid]) {
				left = mid+1;
			} else {
				right = mid;
			}
		}
		mid = left-1;
		//System.out.println(left+" "+right);
		long result;
		long c;
		if (mid == arr.length-1) {
			result = arr[mid]-target;
			c = arr[mid];
		} else if (mid == j) {
			result = arr[mid+1]-target;
			c = arr[mid+1];
		} else {
			if (Math.abs(arr[mid]-target) > Math.abs(arr[mid+1]-target)) {
				result = arr[mid+1]-target;
				c = arr[mid+1];
			} else {
				result = arr[mid]-target;
				c = arr[mid];
			}
		}
		//System.out.println(minAbs+" "+result);
		if (Math.abs(minAbs)>Math.abs(result)) {
			minAbs = result;
			minFair[0] = a;
			minFair[1] = b;
			minFair[2] = c;
		}
	}
}
