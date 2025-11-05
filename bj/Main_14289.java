import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14289 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[][] arr = new long[N][N];
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a-1][b-1] = 1;
			arr[b-1][a-1] = 1;
		}
		long D = Integer.parseInt(br.readLine());
		long[][] result = powerMatrix(arr, D);
		System.out.println(result[0][0]);
	}
	public static long[][] powerMatrix(long[][] matrix, long d) {
		if (d == 1) return matrix;
		long[][] result = powerMatrix(matrix, d/2);
		if (d%2 == 0) {
			return calculate(result, result);
		} else {
			return calculate(matrix,calculate(result, result));
		}
	}
	public static long[][] calculate(long[][] A, long[][] B) {
		long[][] C = new long[A.length][B.length];
		for (int i=0;i<A.length;i++) {
			for (int j=0;j<A.length;j++) {
				for (int k=0;k<A.length;k++) {
					C[j][k] = C[j][k]+(A[j][i]*B[i][k]%1_000_000_007);
					C[j][k]%=1_000_000_007;
				}
			}
		}
		return C;
	}
}
