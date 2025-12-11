import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Objects;

public class Main_1793 {
	/*
	2*3일 때, 2*1 경우의 수 * 2*2 경우의 수 * 3
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BigInteger[] arr = new BigInteger[1001];
		arr[0] = BigInteger.valueOf(1L);
		arr[1] = BigInteger.valueOf(1L);
		arr[2] = BigInteger.valueOf(3L);
		for (int i=3;i<=250;i++) {
			arr[i] = arr[i-1].add(arr[i-2].multiply(BigInteger.valueOf(2)));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
			int N = Integer.parseInt(line);
			sb.append(arr[N]).append("\n");
		}
		System.out.println(sb);
	}
}
