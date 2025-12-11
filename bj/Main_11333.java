import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_11333 {
	static final long MOD = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[] arr4 = new long[10001];
		arr4[0] = 1;  // 초기값
		arr4[1] = 0;
		arr4[2] = 0;
		arr4[3] = 3;
		arr4[4] = 2;
		arr4[5] = 2;
		int T = Integer.parseInt(br.readLine());

		// 최대값까지 미리 계산
		for (int i = 6; i <= 10000; i++) {
			if (i % 3 == 0) {
				arr4[i] = (arr4[i-3] * 3 % MOD + arr4[i-2] % MOD + arr4[i-1] % MOD) % MOD;
			} else if (i % 3 == 1) {
				arr4[i] = (arr4[i-4] * 2) % MOD + arr4[i-3] % MOD;
			} else {
				arr4[i] = (arr4[i-5] * 2 % MOD + arr4[i-4] % MOD + arr4[i-3] % MOD) % MOD;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			System.out.println(arr4[N]);
			if (N%3 != 0) sb.append(0).append("\n");
			else sb.append(arr4[N]).append("\n");
		}

		System.out.print(sb);
	}
}