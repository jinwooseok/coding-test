import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main_6574 {
	/*
	두 과일의 이름을 부분 문자열로 포함하는 문자열 중, 가장 짧은 것
	부분 문자열로 두 과일의 이름을 포함해야 함. (연속되지 않아도 됨)
	 A P P L E
	P0 1 1 1 1
	E0 1 1 1 2
	A0 1 1 1 2
	C0 1 1 1 2
	H0 1 1 1 2

	  A N A N A S
	B 0 0 0 0 0 0
	A 1 1 1 1 1 1
	N 1 2 2 2 2 2
	A 2 2 3 3 3 3
	N 2 3 3 4 4 4
	A 3 3 4 4 5 5

	역추적. 이후 양옆 붙이기
	A
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			StringTokenizer st = new StringTokenizer(line);
			String a = st.nextToken();
			String b = st.nextToken();

			int N = a.length();
			int M = b.length();

			int[][] dp = new int[N+1][M+1];
			for (int i=1;i<=N;i++) {
				for (int j=1;j<=M;j++) {
					boolean same = a.charAt(i-1) == b.charAt(j-1);
					if (same) {
						dp[i][j] = dp[i-1][j-1]+1;
					} else {
						dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
					}
				}
			}
			// for (int i=0;i<N;i++) {
			// 	System.out.println(Arrays.toString(dp[i]));
			// }
			int i=N;
			int j=M;
			char[] result = new char[N+M-dp[N][M]];
			int idx = result.length-1;
			while (i>0 && j>0) {
				if (a.charAt(i-1)==b.charAt(j-1)) {
					result[idx--] = a.charAt(i-1);
					i = i-1;
					j = j-1;
				} else {
					if (dp[i][j-1] > dp[i-1][j]) {
						result[idx--] = b.charAt(j-1);
						j = j-1;
					} else {
						result[idx--] = a.charAt(i-1);
						i = i-1;
					}
				}
			}
			while (i > 0) {
				result[idx--] = a.charAt(i-1);
				i--;
			}

			while (j > 0) {
				result[idx--] = b.charAt(j-1);
				j--;
			}
			for (char c:result) {
				// System.out.println(c);
				sb.append(c);
			}
			sb.append("\n");
		}

		System.out.println(sb);
	}
}
