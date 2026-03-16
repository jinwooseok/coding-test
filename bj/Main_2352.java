import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2352 {
	/*
	우선 나열
	그리고 현재 구조에서 오름차순으로 되도록 함
	가장 긴 증가하는 부분수열
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] record = new int[N+1];
		Stack<Integer> stack = new Stack<>();
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for (int i=0;i<N;i++) {
			int max = 0;
			for (int j=1;j<arr[i];j++) {
				max = Math.max(record[j], max);
			}
			record[arr[i]] = max+1;
		}

		// System.out.println(Arrays.toString(record));
		int maxNum = 0;
		for (int cnt:record) {
			maxNum = Math.max(maxNum, cnt);
		}
		System.out.println(maxNum);
	}
}
