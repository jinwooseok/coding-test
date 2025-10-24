import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_11689 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long num = Long.parseLong(br.readLine());
		List<Integer> primes = new ArrayList<>();
		boolean[] che = new boolean[1000000];
		Arrays.fill(che, true);
		for (int i=2;i<1000000;i++) {
			if (!che[i]) continue;
			int j=2;
			primes.add(i);
			while (i*j<1000000) {
				che[i*j] = false;
				j++;
			}
		}
		//System.out.println(primes);
		long result = 1;
		for (int prime:primes) {
			if (num==1) break;
			int m=0;
			while (true) {
				if (num%prime==0) {
					num/=prime;
					m++;
				} else {
					break;
				}
			}
			if (m==0) continue;
			//System.out.println(1-(1/(float)prime));
			result*=(long) (Math.pow(prime, m-1)*(prime-1));
			//System.out.println(prime+" "+m);
		}
		//System.out.println(num);
		if (num > 1) {
			result *= (num - 1);
		}
		System.out.println(result);
	}
}
