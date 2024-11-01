import java.io.*;
import java.util.*;
public class Main_9252 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str1 = br.readLine();
		String str2 = br.readLine();
		char[] str1Arr;
		char[] str2Arr;
		if (str1.length() > str2.length()) {
			str2Arr = str1.toCharArray();
			str1Arr = str2.toCharArray();
		} else {
			str1Arr = str1.toCharArray();
			str2Arr = str2.toCharArray();
		}
		
		int[][] dp = new int[str1Arr.length+1][str2Arr.length+1];
		for (int i=1;i<dp.length;i++) {
			for (int j=1;j<dp[0].length;j++) {
				if (str1Arr[i-1] == str2Arr[j-1]) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		
		int i=dp.length-1; int j=dp[0].length-1;
		char[] result = new char[dp[i][j]];
		int idx = dp[i][j]-1;
		while (dp[i][j] != 0) {
			//System.out.println(dp[i][j]);
			if (dp[i-1][j]==dp[i][j]) {
				i--;
			} else if (dp[i][j-1]==dp[i][j]) {
				j--;
			} else if (str1Arr[i-1]==str2Arr[j-1] && dp[i-1][j-1]==dp[i][j]-1) {
				result[idx--] = str2Arr[j-1];
				//System.out.println(str2Arr[j-1]);
				i--; j--;
			}
		}
//		for (int k=0;k<dp.length;k++) {
//			System.out.println(Arrays.toString(dp[k]));
//		}
		System.out.println(dp[dp.length-1][dp[0].length-1]);
		StringBuilder sb = new StringBuilder();
		for (char str:result) {
			sb.append(str);
		}
		System.out.println(sb);
	}

}
