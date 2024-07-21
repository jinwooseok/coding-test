import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.Math.max;

public class Main_11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];

        for (int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 1;
        }

        for (int i=1;i<n;i++){
            for (int j=0;j<i;j++){
                if (arr[i]>arr[j]) dp[i]=max(dp[i],dp[j]+1);
            }
        }
        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}
