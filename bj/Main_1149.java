import java.io.*;
import java.util.*;
public class Main_1149 {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //[N][3] 을 하여 자신을 포함하지 않는 경우
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][3];
        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<3;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] dp = new int[N][3];
        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];
        for (int i=0;i<N-1;i++) {
            minSum(arr, dp, i);
        }
        System.out.println(Arrays.stream(dp[N-1]).min().getAsInt());
    }

    //최소 dp찾아서+ 본인해서 저장하기
    public static void minSum(int[][] arr, int[][] dp, int n) {
        dp[n+1][0]=arr[n+1][0]+Math.min(dp[n][1], dp[n][2]);
        dp[n+1][1]=arr[n+1][1]+Math.min(dp[n][0], dp[n][2]);
        dp[n+1][2]+=arr[n+1][2]+Math.min(dp[n][0], dp[n][1]);
    }

}
