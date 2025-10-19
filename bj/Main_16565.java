import java.io.*;

public class Main_16565 {
	static final int MOD = 10007;
	static long[][] C = new long[53][53];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		// 조합 전처리 (파스칼의 삼각형)
		for (int i = 0; i <= 52; i++) {
			C[i][0] = 1;
			for (int j = 1; j <= i; j++) {
				C[i][j] = (C[i-1][j-1] + C[i-1][j]) % MOD;
			}
		}

		long answer = 0;
		// 포함-배제 원리
		for (int i = 1; i * 4 <= n; i++) {
			long val = (C[13][i] * C[52 - 4*i][n - 4*i]) % MOD;
			if (i % 2 == 1) {
				answer = (answer + val) % MOD;
			} else {
				answer = (answer - val + MOD) % MOD;
			}
		}

		System.out.println(answer);
	}
}
