import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_12920 {
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Integer> weights = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        for (int i=1;i<N+1;i++) {
            st = new StringTokenizer(br.readLine());
            int weight=Integer.parseInt(st.nextToken());
            int value=Integer.parseInt(st.nextToken());
            int cnt=Integer.parseInt(st.nextToken());
            int j = cnt;
            int num = 1;
            while (j>0) {
                if (j-num < 0) {
                    weights.add(weight*j);
                    values.add(value*j);
                    break;
                } else {
                    weights.add(weight*num);
                    values.add(value*num);
                    j-=num;
                }
                num*=2;
            }
//            System.out.println(weights);
//            System.out.println(values);
        }
        int[][] dp = new int[weights.size()+1][M+1];
        for (int i=1;i<weights.size()+1;i++) {
            int weight=weights.get(i-1);
            int value=values.get(i-1);
            for (int j=1;j<M+1;j++) {
                if (j-weight<0) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j]=Math.max(dp[i-1][j-weight]+value,dp[i-1][j]);
                }
            }
        }
        System.out.println(dp[weights.size()][M]);
    }

}

