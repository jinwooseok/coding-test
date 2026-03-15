import java.io.*;
import java.util.*;

public class Main_2014 {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		long[] primes = new long[K];
		int[] idx = new int[K];
		long[] dp = new long[N + 1];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < K; i++) {
			primes[i] = Long.parseLong(st.nextToken());
		}

		dp[0] = 1;

		for (int i = 1; i <= N; i++) {

			long next = Long.MAX_VALUE;

			for (int j = 0; j < K; j++) {
				next = Math.min(next, primes[j] * dp[idx[j]]);
			}

			dp[i] = next;

			for (int j = 0; j < K; j++) {
				if (primes[j] * dp[idx[j]] == next) {
					idx[j]++;
				}
			}
		}

		System.out.println(dp[N]);
	}
}