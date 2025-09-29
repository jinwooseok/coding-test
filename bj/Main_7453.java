import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_7453 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int[][] arrays = new int[4][N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				arrays[j][i] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dp = new int[2][N * N];
		calculate_sum(dp, arrays, 0, 1, N);
		calculate_sum(dp, arrays, 2, 3, N);

		long answer = 0;
		int i = 0, j = N * N - 1;

		while (i < N * N && j >= 0) {
			int sum = dp[0][i] + dp[1][j];
			if (sum > 0) {
				j--;
			} else if (sum < 0) {
				i++;
			} else {
				int iVal = dp[0][i];
				int jVal = dp[1][j];
				int iCount = 0;
				int jCount = 0;

				while (i < N * N && dp[0][i] == iVal) {
					i++;
					iCount++;
				}
				while (j >= 0 && dp[1][j] == jVal) {
					j--;
					jCount++;
				}
				answer += (long) iCount * jCount;
			}
		}

		System.out.println(answer);
	}

	public static void calculate_sum(int[][] dp, int[][] arrays, int st, int ed, int N) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dp[st / 2][i * N + j] = arrays[st][i] + arrays[ed][j];
			}
		}
		Arrays.sort(dp[st / 2]);
	}
}

// public static boolean search(int num, int[] array, int N) {
// 	int left = 0;
// 	int right = N-1;
// 	int target = num*(-1);
// 	int mid = (left+right)/2;
//
// 	while (left <= right) {
// 		if (array[mid]<target) {
// 			left = mid+1;
// 			mid = (mid+right)/2;
// 		} else if (array[mid]>target) {
// 			right = mid-1;
// 			mid = (mid+left)/2;
// 		} else {
// 			return true;
// 		}
// 	}
// 	return false;
// }
