import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2942 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long x = Integer.parseInt(st.nextToken());
		long y = Integer.parseInt(st.nextToken());
		long low = Math.min(x,y);
		long max = Math.max(x,y);
		StringBuilder sb = new StringBuilder();
		long maxNum=gcd(max, low);
		for (long i=1;i<=Math.sqrt(maxNum);i++) {
			if (x%i==0 && y%i==0) {
				sb.append(i)
					.append(" ")
					.append(x/i)
					.append(" ")
					.append(y/i)
					.append("\n");
				if (i==maxNum/i) continue;
				sb.append(maxNum/i)
					.append(" ")
					.append(x/(maxNum/i))
					.append(" ")
					.append(y/(maxNum/i))
					.append("\n");
			}
		}
		System.out.println(sb);
	}

	public static long gcd(long high, long low) {
		if (low == 0) return high;
		return gcd(low,high%low);
	}
}
