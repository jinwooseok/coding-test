import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1016 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int limit = 1000001;
		boolean[] isPrime = new boolean[limit];
		Arrays.fill(isPrime, true);
		isPrime[0] = isPrime[1] = false;

		List<Integer> primes = new ArrayList<>();
		for (int i = 2; i < limit; i++) {
			if (!isPrime[i]) continue;
			primes.add(i);

			// 에라토스테네스의 체
			for (long j = (long)i * i; j < limit; j += i) {
				isPrime[(int)j] = false;
			}
		}
		//System.out.println(sosu);
		StringTokenizer st = new StringTokenizer(br.readLine());
		long min = Long.parseLong(st.nextToken());
		long max = Long.parseLong(st.nextToken());
		Queue<Long> nums = new LinkedList<>();

		for (long i=min;i<max+1;i++) {
			nums.add(i);
		}

		int size = (int)(max - min + 1);
		boolean[] removed = new boolean[size];  // Queue 대신 배열!

		// 소수의 제곱으로 제거
		for (int p : primes) {
			long square = (long)p * p;
			if (square > max) break;

			long start = ((min - 1) / square + 1) * square;

			for (long num = start; num <= max; num += square) {
				removed[(int)(num - min)] = true;
			}
		}

		int count = 0;
		for (boolean r : removed) {
			if (!r) count++;
		}

		System.out.println(count);
	}
}
