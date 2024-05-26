import java.util.*;
class Solution {
    int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int[][][] dp = new int[m][n][2];
        dp[0][0][0] = 1;
        dp[0][0][1] = 1;
        for (int i=0;i<m;i++){
            dp[i][0][0]=1;
            dp[i][0][1]=0;
            if (cityMap[i][0]==0) dp[i][1][1]+=1;
            else if (cityMap[i][0]==1) break;
        }
        for (int j=0;j<n;j++){
            dp[0][j][0]=0;
            dp[0][j][1]=1;
            if (cityMap[0][j]==0) dp[1][j][0]+=1;
            else if (cityMap[0][j]==1) break;
        }
        for (int i=1;i<m;i++){
            for (int j=1;j<n;j++){
                if (cityMap[i][j]==0){
                    if (i+1<m) dp[i+1][j][0]+=(dp[i][j][0]+dp[i][j][1])%MOD;
                    if (j+1<n) dp[i][j+1][1]+=(dp[i][j][0]+dp[i][j][1])%MOD;
                }
                else if (cityMap[i][j]==1){
                    continue;
                }
                else if (cityMap[i][j]==2){
                    if (i+1<m) dp[i+1][j][0]+=dp[i][j][0];
                    if (j+1<n) dp[i][j+1][1]+=dp[i][j][1];
                }
                //System.out.println(dp[i][j][0]+" "+dp[i][j][1]);
            }
        }
        return (dp[m-1][n-1][0]+dp[m-1][n-1][1])%MOD;
    }
}