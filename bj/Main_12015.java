import java.util.*;
import java.io.*;
public class Main_12015 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[N];
		//각 숫자가 들어갈 자리를 이분탐색으로 변경
		dp[0] = arr[0];
		int idx = 1;
		for (int i=1;i<N;i++) {
			if (dp[idx-1]>arr[i]) {
				//이분탐색.
				int left = 0; int right = idx-1; int mid = (right+left)/2;
				while (left<=right) {
					if (dp[mid]<arr[i]) {
						left = mid+1;
					} else if (dp[mid]>arr[i]) {
						right = mid-1;
					} else {
						break;
					}
					mid = (left+right)/2;
				}
				//System.out.println(left+" "+mid+" "+right);
				if (dp[mid]<arr[i]) {
					dp[left] = arr[i];
				} else {					
					dp[mid] = arr[i];					
				}
			} else {
				dp[idx++] = arr[i];
			}
		}
		
		//30 10 25 50 28 30 26 60
//		System.out.println(Arrays.toString(dp));
		System.out.println(idx);
//		StringBuilder sb = new StringBuilder();
//		for (int i=0;i<idx;i++) {
//			sb.append(dp[i]).append(" ");
//		}
//		System.out.println(sb);
	}
}
