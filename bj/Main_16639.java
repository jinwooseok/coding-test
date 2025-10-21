import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_16639 {
	static long[][] dpMax, dpMin;
	static int[] nums;
	static char[] opers;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String line = br.readLine();

		int numCnt = N/2 + 1;
		nums = new int[numCnt];
		opers = new char[N/2];

		for (int i = 0; i < N; i++) {
			if (i % 2 == 0) {
				nums[i/2] = line.charAt(i) - '0';
			} else {
				opers[i/2] = line.charAt(i);
			}
		}

		dpMax = new long[numCnt][numCnt];
		dpMin = new long[numCnt][numCnt];

		// 초기화
		for (int i = 0; i < numCnt; i++) {
			for (int j = 0; j < numCnt; j++) {
				dpMax[i][j] = Long.MIN_VALUE;
				dpMin[i][j] = Long.MAX_VALUE;
			}
			dpMax[i][i] = dpMin[i][i] = nums[i];
		}

		// 길이
		for (int len = 1; len < numCnt; len++) {
			for (int i = 0; i < numCnt - len; i++) {
				int j = i + len;
				for (int k = i; k < j; k++) {
					long[] results = calc(
						dpMax[i][k], dpMin[i][k],
						dpMax[k+1][j], dpMin[k+1][j],
						opers[k]
					);
					dpMax[i][j] = Math.max(dpMax[i][j], results[0]);
					dpMin[i][j] = Math.min(dpMin[i][j], results[1]);
				}
			}
		}

		System.out.println(dpMax[0][numCnt-1]);
	}

	public static long[] calc(long forwardMax, long forwardMin, long backwardMax, long backwardMin, char operator) {
		long[] result = new long[2];
		if (operator == '+') {
			result[0] = forwardMax+backwardMax;
			result[1] = forwardMin+backwardMin;
		} else if (operator == '-') {
			result[0] = forwardMax-backwardMin;
			result[1] = forwardMin-backwardMax;
		} else {
			result[0] = Math.max(Math.max(forwardMax*backwardMax, forwardMin*backwardMin), Math.max(forwardMin*backwardMax, forwardMax*backwardMin));
			result[1] = Math.min(Math.min(forwardMax*backwardMax, forwardMin*backwardMin), Math.min(forwardMin*backwardMax, forwardMax*backwardMin));
		}
		return result;
	}
}

