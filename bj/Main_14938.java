import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_14938 {
	/**
	 * 될거같은데.. 완탐.
	 * 1. 플로이드 + 각 100*100 O(N^3)
	 * 2. 각 지역마다 다른 지역과의 거리 체킹 - 100*50
	 * 3.
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];
		int[][] map = new int[N][N];
		for (int i=0;i<N;i++) {
			Arrays.fill(map[i], Integer.MAX_VALUE);
			map[i][i] = 0;
		}
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int a, b, l;
		for (int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken())-1;
			b = Integer.parseInt(st.nextToken())-1;
			l = Integer.parseInt(st.nextToken());

			map[a][b] = Math.min(map[a][b],l);
			map[b][a] = map[a][b];
		}
		// for (int i=0;i<N;i++) {
		// 	System.out.println(Arrays.toString(map[i]));
		// }
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				for (int k=0;k<N;k++) {
					if (map[j][i] == Integer.MAX_VALUE || map[i][k] == Integer.MAX_VALUE) continue;
					map[j][k] = Math.min(map[j][k], map[j][i]+map[i][k]);
					map[k][j] = map[j][k];
				}
			}
		}
		// for (int i=0;i<N;i++) {
		// 	System.out.println(Arrays.toString(map[i]));
		// }
		int sum;
		int max = 0;
		for (int i=0;i<N;i++) {
			sum = 0;
			for (int j=0;j<N;j++) {
				if (map[i][j]>M) continue;
				sum += arr[j];
			}
			max = Math.max(max, sum);
		}
		System.out.println(max);
	}
}
