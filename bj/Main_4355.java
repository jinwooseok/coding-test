import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main_4355 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// n 은 소수의 곱으로 이뤄짐. p1^k1+p2^k2...pn^kn
		// 서로소이기 위해서 gcd(a) = 1이 되어야 함.
		// (p^(k1-1))*(1-1/p)
		// 어떤 소수로 이뤄져 있는지 찾기 -> 루트n까지 : 이유: 그 이상은 곱으로 변경 불가능.
		// 오일러 피함수를 오일러(소수)들의 곱으로 치환
		// 소수의 오일러 피함수는 p1^k1일 때 p1*1, p1*2 ...p1*p1...p1*p1^(k1-1)
		// 개수는 p1*(p1^(k1-1)-1)
		List<Integer> primes = new ArrayList<>();
		boolean[] che = new boolean[100000];
		Arrays.fill(che, true);
		for (int i=2;i<100000;i++) {
			if (!che[i]) continue;
			int j=2;
			primes.add(i);
			while (i*j<100000) {
				che[i*j] = false;
				j++;
			}
		}
		//System.out.println(primes);
		StringBuilder sb = new StringBuilder();
		long num;
		while (0 != (num=Long.parseLong(br.readLine()))) {
			long result = num;
			if (num == 1) {
				sb.append(0).append('\n');
				continue;
			}
			for (int prime:primes) {
				if (num==1) break;
				if ((long)prime * prime > num) break;
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
				result = result / prime * (prime - 1);
				//System.out.println(prime+" "+m);
			}
			//System.out.println(num);
			if (num > 1) {
				result = result / num * (num - 1);
			}
			sb.append(result).append('\n');
		}

		System.out.println(sb);
	}
}
