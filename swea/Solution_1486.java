import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1486 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        for (int t=1;t<T+1;t++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int cnt = Integer.parseInt(st.nextToken());
            int minHeight = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine().trim());
            int[] arr = new int[cnt];
            int arrSum = 0;
            for (int i=0;i<cnt;i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                arrSum+=arr[i];
            }

            System.out.println("#"+t+" "+subset(arr, 0, minHeight, 0, arrSum));
        }

    }
    private static int subset(int[] arr, int sum, int height, int startIndex, int arrSum){
        if (startIndex>=arr.length) {
            if (sum>=height) {
                return sum-height;
            } else {
                return Integer.MAX_VALUE;
            }
        }
        int cnt1=subset(arr, sum + arr[startIndex], height, startIndex + 1,arrSum);
        int cnt2=subset(arr, sum, height, startIndex + 1,arrSum);
        return Math.min(cnt1, cnt2);
    }
}
