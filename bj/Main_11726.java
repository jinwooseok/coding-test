import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_11726 {
	/*
	2*3일 때, 2*1 경우의 수 * 2*2 경우의 수 * 3
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[] arr = new long[1001];
		arr[0] = 1;
		arr[1] = 1;
		arr[2] = 2;
		int N = Integer.parseInt(br.readLine());
		for (int i=3;i<=N;i++) {
			arr[i] = (arr[i-1]%10007+arr[i-2]%10007)%10007;
		}
		System.out.println(arr[N]);
	}
}
