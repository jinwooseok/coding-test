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
		long tmp;
		while(low>0) {
			tmp = max%low;
			max = low;
			low = tmp;
		}
		double sq = Math.sqrt(max);
		for (long i=1;i<=sq;i++) {
			if (x%i==0 && y%i==0) {
				sb.append(i)
					.append(" ")
					.append(x/i)
					.append(" ")
					.append(y/i)
					.append("\n");
				if (i==max/i) continue;
				sb.append(max/i)
					.append(" ")
					.append(x/(max/i))
					.append(" ")
					.append(y/(max/i))
					.append("\n");
			}
		}
		System.out.println(sb);
	}
}
