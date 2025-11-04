import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2618 {

	public static int encode(int i, int j, int N) {
		return i * (N + 1) + j;
	}

	public static int[] decode(int encoded, int N) {
		return new int[]{encoded / (N + 1), encoded % (N + 1)};
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int M = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());

		int[][] dp = new int[N+1][N+1];
		int[][] cases = new int[N+1][2];
		int[][] choices = new int[N+1][N+1];  // 이전 상태를 하나의 숫자로 저장

		StringTokenizer st;
		for (int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			cases[i][0] = Integer.parseInt(st.nextToken());
			cases[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int i=0;i<=N;i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
			Arrays.fill(choices[i], -1);
		}

		// 초기화
		dp[0][1] = getDist(M,M,cases[1][0],cases[1][1]);
		dp[1][0] = getDist(1,1,cases[1][0],cases[1][1]);
		choices[0][1] = encode(0, 0, N);
		choices[1][0] = encode(0, 0, N);

		for (int i=2;i<N+1;i++) {
			dp[0][i] = dp[0][i-1]+getDist(cases[i][0],cases[i][1],cases[i-1][0],cases[i-1][1]);
			dp[i][0] = dp[i-1][0]+getDist(cases[i][0],cases[i][1],cases[i-1][0],cases[i-1][1]);
			choices[0][i] = encode(0, i-1, N);
			choices[i][0] = encode(i-1, 0, N);
		}

		// DP 채우기
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				if (i==j) continue;
				if (dp[i][j] == Integer.MAX_VALUE) continue;

				int next = Math.max(i,j)+1;
				if (next > N) continue;

				// 경1이 next 처리
				int x1 = (i == 0) ? 1 : cases[i][0];
				int y1 = (i == 0) ? 1 : cases[i][1];
				int newDist1 = dp[i][j] + getDist(x1, y1, cases[next][0], cases[next][1]);
				if (newDist1 < dp[next][j]) {
					dp[next][j] = newDist1;
					choices[next][j] = encode(i, j, N);
				}

				// 경2가 next 처리
				int x2 = (j == 0) ? M : cases[j][0];
				int y2 = (j == 0) ? M : cases[j][1];
				int newDist2 = dp[i][j] + getDist(x2, y2, cases[next][0], cases[next][1]);
				if (newDist2 < dp[i][next]) {
					dp[i][next] = newDist2;
					choices[i][next] = encode(i, j, N);
				}
			}
		}

		// 최소값 찾기
		int minDist = Integer.MAX_VALUE;
		int lastI = -1, lastJ = -1;

		for (int i=0;i<=N;i++) {
			if (dp[i][N] < minDist) {
				minDist = dp[i][N];
				lastI = i;
				lastJ = N;
			}
			if (dp[N][i] < minDist) {
				minDist = dp[N][i];
				lastI = N;
				lastJ = i;
			}
		}

		System.out.println(minDist);

		// 역추적
		List<Integer> path = new ArrayList<>();
		int curI = lastI, curJ = lastJ;

		while (curI != 0 || curJ != 0) {
			int encoded = choices[curI][curJ];
			int[] prev = decode(encoded, N);
			int prevI = prev[0];
			int prevJ = prev[1];

			// 어느 경찰차가 처리했는지 판단
			if (prevI != curI) {
				path.add(1);
			} else {
				path.add(2);
			}
			curI = prevI;
			curJ = prevJ;
		}

		// 역순으로 출력
		Collections.reverse(path);
		for (int p : path) {
			System.out.println(p);
		}
	}

	public static int getDist(int a, int b, int c, int d) {
		return Math.abs(c-a)+Math.abs(d-b);
	}
}