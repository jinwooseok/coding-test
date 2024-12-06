import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long[] arr = new long[N];
        long[] sumArr = new long[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=1;i<=N;i++) {
            arr[i-1] = Integer.parseInt(st.nextToken());
            sumArr[i] = arr[i-1]+sumArr[i-1];
        }
        int start = 0;
        int end = 1;
        int minCnt = Integer.MAX_VALUE;
        while (end<=N) {
            if (sumArr[end]-sumArr[start]>=K && end>start+1) {
                //System.out.println(end+" "+ start);
                minCnt = Math.min(minCnt,end-start);
                start++;
            }
            else if (sumArr[end]-sumArr[start]>=K && end==start+1) {
                minCnt = Math.min(minCnt,end-start);
                end++;
            } else {
                end++;
            }
        }
        if (minCnt == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(minCnt);
        }
    }
}
//import java.util.Scanner;
//
//public class Main_1806 {
//
//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//
//        int N = sc.nextInt();
//        int target = sc.nextInt();
//        int[] A = new int[N + 1];
//        int[] S = new int[N + 1];
//        S[0] = 0;
//        for (int i = 1; i <= N; i++) {
//            A[i] = sc.nextInt();
//            S[i] = S[i - 1] + A[i];
//        }
//        int min_len = N + 1;
//        int start = 0;
//        int end = 1;
//        int sum;
//        while (end <= N && start <= N) {
//            sum = S[end] - S[start];
//            if (sum == target && min_len > end - start) {
//                min_len = end - start;
//            }
//            if (sum >= target && start < end) {
//                start++;
//            }
//            if (sum < target || start == end) {
//                end++;
//            }
//        }
//        if (min_len == N + 1) {
//            System.out.println("0");
//        } else {
//            System.out.println(min_len);
//        }
//    }
//}

//5 0
//0 0 0 0 0