import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_9527 {
	/**
	 * 자연수 A,B가 주어짐
	 * A<=X<=B인 모든 X를 이진수로 표현했을 때 1의 개수의 합
	 * 모든 X이다. B는 10의 16승으로 당연히 LONG단위까지 감
	 * 0 1 2 5 7 9 12 13 15 17 20 22 25 28 32
	 * 0 -> 0 0
	 * 1 -> 1 1
	 * 2 -> 10 1
	 * 3 -> 11 2
	 * 4 -> 100 1
	 * 5 -> 101 2
	 * 6 -> 110 2
	 * 7 -> 111 3
	 * 8 -> 1000 1 1 2 2 3 2 3 3 4
	 * 9 -> 1001 2
	 * 10 -> 1010 2
	 * 11 -> 1011 3
	 * 12 -> 1100 2
	 * 13 -> 1101 3
	 * 14 -> 1110 3
	 * 15 -> 1111 4
	 * 16 -> 10000 1
	 * 17 -> 10001 2
	 * 18 -> 10010 2
	 * 19 -> 10011 3
	 * 20 -> 10100 2
	 * 21 -> 10101 3
	 * 22 -> 10110 3
	 * 23 -> 10111 4
	 * 24 -> 11000 2
	 * 25 -> 11001 3
	 * 26 -> 11010 3
	 * 27 -> 11011 4
	 * 28 -> 11100 3
	 * 29 -> 11101 4
	 * 30 -> 11110 4
	 * 31 -> 11111 5
	 *
	 * 10 = 2+8
	 * 11 = 1+2+8
	 * 1. 규칙성 찾아 계산
	 * 2. DP를 통해 합친다
	 * DP배열을 10의 16승하는 것은 불가능
	 * 2의 48승과 2의 64승 사이에 최대값 위치
	 * 1010101110101000110... 매번 48~64 연산 존재 가능성
	 * 비트마스크 ->
	 * 시작값 끝값
	 * 이전단계의 +1한것과 같음
	 * 17 ~ 20 까지의 합
	 * 1 ~ 4 까지의 합 + 개수*jump (4)
	 *
	 * 25 ~ 28 까지의 합
	 * 9 ~ 12 까지의 합 + N(4)
	 * 1 ~ 4 까지의 합 + N*2(8)
	 *
	 * 14 ~ 33 까지의 합
	 * 분할
	 * 1. 14~15
	 * 2. 16~31 -> 0~
	 * 3. 32~33 -> 0~1 2*1
	 *
	 * 부분합배열로 16까지 만들어 놓기 -> 16안으로 들어오게 만들기
	 * 2 12
	 * 2 7
	 * 0 4
	 *
	 * start, end 각각 1번
	 * +8까지의 합에  (1+2+3+.._)*8 => last인 (n(n+1)/2 - k(k+1)/2)*8
	 *
	 * 분할정복
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long start = Long.parseLong(st.nextToken());
		long end = Long.parseLong(st.nextToken());
		long[] dp = new long[64];
		dp[0] = 0;
		for (int i=1;i<64;i++) {
			dp[i] = dp[i-1]*2+(long) Math.pow(2,i-1);
		}

		System.out.println(getSum(dp, end)-getSum(dp, start-1));
	}

	private static long getSum(long[] dp, long point) {
		if (point == 0) {
			return 0;
		}
		int msb = 0;
		long tmp = point;
		while (tmp > 0) {
			tmp >>= 1; // 오른쪽으로 1비트씩 이동
			msb++;
		}
		msb--;
		long msbValue = 1L << msb;
		long msbCount = point - msbValue + 1;
		return dp[msb] + getSum(dp, point-msbValue)+msbCount;
	}
}
