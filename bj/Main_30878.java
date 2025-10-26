import java.io.*;

public class Main_30878 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());

		// 분자: M^2 * (180 - 2M)
		long numerator = (long) M * M * (180 - 2 * M);

		// 분모: 60^3 = 216000
		long denominator = 216000;

		// 기약분수로 만들기
		long gcd = gcd(numerator, denominator);

		System.out.println((numerator/gcd) + "/" + (denominator/gcd));
	}

	static long gcd(long a, long b) {
		return b == 0 ? a : gcd(b, a % b);
	}
}