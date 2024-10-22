import java.util.*;
import java.io.*;
public class Main_17404 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][3];
		int[][][] dp = new int[3][N][3];
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<3;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for (int k=0;k<3;k++) {
			for (int i=0;i<N;i++) {
				for (int j=0;j<3;j++) {
					dp[k][i][j] = 1000;
				}
			}			
		}
		
		//색깔별로 시작. 나머지는 
		for (int k=0;k<3;k++) {
			dp[k][0][k] = arr[0][k];
			
			//첫번째 자리부터 결정
			for (int i=1;i<N;i++) {
				for (int j=0;j<3;j++) {
					if (j == 0) {
						dp[k][i][j] = arr[i][j]+Math.min(dp[k][i-1][1],dp[k][i-1][2]);
					} else if (j == 1) {
						dp[k][i][j] = arr[i][j]+Math.min(dp[k][i-1][0],dp[k][i-1][2]);
					} else {
						dp[k][i][j] = arr[i][j]+Math.min(dp[k][i-1][1],dp[k][i-1][0]);
					}
				}
			}
		}
		
		//결론. 시작지점과 겹치는 부분을 제외하고 최솟값 출력
		int minSum = Integer.MAX_VALUE;
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				if (i != j) {
					minSum = Math.min(dp[i][N-1][j], minSum);
				}
			}
		}
		System.out.println(minSum);
	}

}
