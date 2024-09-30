import java.io.*;
import java.util.*;
public class Main_12865 {
    /*
     * N개의 물건, K 부피만큼 넣을 수 있는 가방
     * N개가
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N+1][K+1];
        for (int i=1;i<N+1;i++) {
            st = new StringTokenizer(br.readLine());
            int start=Integer.parseInt(st.nextToken());
            int value=Integer.parseInt(st.nextToken());
            for (int j=1;j<K+1;j++) {
                if (j-start<0) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j]=Math.max(dp[i-1][j-start]+value,dp[i-1][j]);
                }
            }
        }
        System.out.println(dp[N][K]);
    }

}
