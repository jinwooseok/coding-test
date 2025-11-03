import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main_12850 {
	/*
	1 -> B:1, C:1
	A B
	A C
	2 -> A:2, B:1, C:1, D:2, E:1
	A B C
	A B A
	A B D
	A C A
	A C B
	A C D
	A C E
	3 -> A:4, B:5, C:6, D:3, E:3, F:3, G:1
	4 -> A:15(4+5+6) , B:13(4+6+3), C:15(4+5+3+3), D:17(5+6+3+3),

	매 횟수 마다 8번 * 8번 이하로 고정,
	 */
	static long MODULER = 1_000_000_007;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] graph = {{1,2},{0,2,3},{0,1,3,4},{1,2,4,5},{2,3,5,6},{3,4,7},{4,7},{5,6}};
		long[][] matrixs = new long[8][8];
		long[][] A = {
			{0, 1, 1, 0, 0, 0, 0, 0},
			{1, 0, 1, 1, 0, 0, 0, 0},
			{1, 1, 0, 1, 1, 0, 0, 0},
			{0, 1, 1, 0, 1, 1, 0, 0},
			{0, 0, 1, 1, 0, 1, 1, 0},
			{0, 0, 0, 1, 1, 0, 0, 1},
			{0, 0, 0, 0, 1, 0, 0, 1},
			{0, 0, 0, 0, 0, 1, 1, 0},
		};
		long[][] result = power(A, N);
		System.out.println(result[0][0]);
	}

	public static long[][] power(long[][] A, int N) {
		if (N==1) {
			return A;
		}

		long[][] half = power(A, N / 2);

		if (N % 2 == 0) {
			return multiply(half, half);
		} else {
			long[][] temp = multiply(half, half);
			return multiply(A, temp);
		}
	}

	public static long[][] multiply(long[][] A, long[][] B) {
		long[][] C = new long[8][8];

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				for (int k = 0; k < 8; k++) {
					C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MODULER;
				}
			}
		}

		return C;
	}
}
