import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main_2156 {
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[][] dp = new int[N][3];
		for (int i=0;i<N;i++) {
			Arrays.fill(dp[i], Integer.MIN_VALUE);
		}
		result = 0;
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		dp[0][0] = 0;
		dp[0][1] = arr[0];
		dp[0][2] = 0;
		for (int i=1;i<N;i++) {
			dp[i][0] = Math.max((Math.max(dp[i-1][0],dp[i-1][1])),dp[i-1][2]);
			dp[i][1] = dp[i-1][0]+arr[i];
			dp[i][2] = dp[i-1][1]+arr[i];
		}
		// for (int i=0;i<N;i++) {
		// 	System.out.println(Arrays.toString(dp[i]));
		// }
		for (int i=0;i<3;i++) {
			result = Math.max(result,dp[N-1][i]);
		}
		System.out.println(result);
	}
	//
	// public static void main(String[] args) throws IOException {
	// 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// 	int N = Integer.parseInt(br.readLine());
	// 	int[] arr = new int[N];
	// 	int[][] dp = new int[N][3];
	// 	for (int i=0;i<N;i++) {
	// 		Arrays.fill(dp[i], Integer.MIN_VALUE);
	// 	}
	// 	result = 0;
	// 	for (int i=0;i<N;i++) {
	// 		arr[i] = Integer.parseInt(br.readLine());
	// 	}
	// 	dfs(dp, arr, 0, 0, 0);
	// 	System.out.println(result);
	// }
	//
	// public static void dfs(int[][] dp, int[] arr, int index, int total, int seq) {
	// 	// 만약 이번 타이밍에 dp에 저장된 부분이 더 크다면 이후에는 무조건 반복됨.
	// 	if (index == arr.length) {
	// 		result = Math.max(result, total);
	// 		return;
	// 	}
	// 	// System.out.println("크기 : " + index +" "+total);
	//
	// 	if (dp[index][seq]>=total) return;
	// 	// 아니라면 저장해둔다.
	// 	dp[index][seq] = total;
	// 	// System.out.println("크기 : " + index +" "+total);
	//
	// 	// 연속된 것이 2보다 작다면 먹을 수 있음. 이번 것을 먹고 다음 걸로 간다.
	// 	if (seq<2) {
	// 		dfs(dp, arr, index+1, total+arr[index], seq+1);
	// 	}
	//
	// 	// 이번 것을 안먹고 다음 걸로 간다. seq는 0으로 초기화
	// 	dfs(dp, arr, index+1, total, 0);
	// }
}
