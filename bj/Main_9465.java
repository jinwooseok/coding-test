import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_9465 {
	/**
	 * 뭘 떼고 안 떼고 이거 결정하기
	 * 2n개의 스티커를 각각 떼는 경우와 안떼는 경우로 DP 계산
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		int N;
		int[][] arr;
		StringBuilder sb = new StringBuilder();
		for (int t=0;t<T;t++) {
			// 테스트 한번 당
			N = Integer.parseInt(br.readLine());
			arr = new int[2][N+1];
			// 배열 채우기
			for (int i=0;i<2;i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=1;j<N+1;j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			/**
			 * 10 30
			 * 20 50
			 */

			// dp 이전걸 선택하면 바로 옆은 선택하지 못하므로 이전꺼가 현재 위치의 최대값
			// 선택하지 않으면 현재 것을 선택할 수 있음.
			int[][] dp = new int[2][N+1];
			dp[0][1] = arr[0][1];
			dp[1][1] = arr[1][1];
			for(int j=2; j<N+1; j++) {
				dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + arr[0][j];
				dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + arr[1][j];
			}
			sb.append(Math.max(dp[0][N], dp[1][N])).append("\n");
		}
		System.out.println(sb);
	}
}
