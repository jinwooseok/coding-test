import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_1557 {
	/*
	arr: 구성된 소수의 개수, 제곱수인지 아닌지
	포함 배제의 원리를 사용해 짝수개면 -, 홀수개면 +, 제곱수 이상이면 0

	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[300000][2];
		for (int i=2;i<300000;i++) {
			if (arr[i][0]==0) {
				int cur = 2;
				arr[i][0] = 1; // 첫 수는 무조건 소수
				while (i*cur<300000) {
					arr[i*cur][0]++;
					if ((i*cur)%(i*i)==0) arr[i*cur][1]=1;
					cur++;
				}
			}
		}
		// for (int i=2;i<100000;i++) {
		// 	System.out.println(Arrays.toString(arr[i]));
		// }
		long left = 1L;
		long right = 30_0000_0000L;
		long mid = 0;
		long[] result;
		if (N>1) {
			while (left<=right) {
				mid = (left+right)/2;
				result = isNo(arr, mid);
				// System.out.println(result[0]+" "+result[1]+" "+mid);
				if (result[0] == -1 && N<=result[1]) {
					right = mid;
				} else if (result[0] == -1 && N>result[1]) {
					left = mid;
				}else if (N<result[1]) {
					right = mid;
				} else if (N>result[1]) {
					left = mid;
				} else {
					// System.out.println(mid);
					break;
				}
			}
			System.out.println(mid);
		} else {
			System.out.println(1);
		}
	}

	static long[] isNo(int[][] arr, long K) {
		long total = 0;
		boolean isNoNo = true;
		for (int i=2;i<300000;i++) {
			if (K%((long)i*i) == 0) {
				isNoNo = false;
			}
			if (arr[i][1] == 1) continue;
			if ((long)i*i>K) break;
			long cnt = K/((long)i*i);
			if (arr[i][0]%2==0) {
				total-=cnt;
			} else {
				total+=cnt;
			}
		}
		if (!isNoNo) {
			return new long[]{-1, K-total};
		}
		return new long[]{1, K-total};
	}
}
// 999999999
// 1000000000
// 1 2 3 5 6 7 10 11 13 14 15 17 19 21 22 23 26 29