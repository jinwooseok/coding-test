import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_13459 {
	/*
	N*M 사이즈
	move 시 중력을 통해 끝까지 감.
	빨간 구슬이 구멍에 빠지면 성공, 파란 구슬이 구멍에 빠지면 실패
	동시에 빠져도 실패
	사방으로 이동하는데 실패한 경우 종료
	최대 10번 실행.
	4*10번 실행
	dfs로 진행. 빨강과 파랑을 한칸 한칸 이동시킴. 기울이기 특성 상 동시에 같은 칸에 가면서 충돌하는 경우는 없음
	기울일 때 나란한 상태가 되는 경우? 충돌하지 않게끔 이동시켜야 함.
	1. dfs로 4방향 이동
	2. 각 이동 시 각 구슬을 끝까지 이동시키는데 이동 방향에 따라 먼저 움직이는게 다름
		- 위로 기울이기: 행을 -시킴. 행이 더 낮은 구슬 먼저 이동
		- 오른쪽 기울이기: 열을 +시킴. 열이 더 큰 구슬 먼저 이동
		- 왼쪽 기울이기: 열을 -시킴. 열이 더 낮은 구슬 먼저 이동
		- 아래 기울이기: 행을 +시킴. 행이 더 큰 구슬 먼저 이동
	3. 각 구슬 이동 시 벽에 부딫히거나 구멍에 빠질 때까지 이동
	4. 이 때 빨간 구슬이 먼저 구멍에 빠지면 성공, 파란 구슬이 빠지면 실패. 동시에 빠지면 실패
		- 구슬이 빠졌으면 배열에 구슬이 없는 것처럼 처리 후 다음 구슬 진행
		- 구슬이 빠졌는지는 매번 차수마다 체크. 빨간 구슬만 빠졌으면 성공 처리 후 return
	5. 10번 실행 이후 경과 확인. 빠진 구슬이 없다면 실패
	백트래킹 처리 -> r,c들을 원래대로 복구.

	 */
	static boolean flag = false;
	static int[][] MOVES = {{0,1},{1,0},{-1,0},{0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] arr = new char[N][M];
		int redR = -1;
		int redC = -1;
		int blueR = -1;
		int blueC = -1;
		String line;
		for (int i=0;i<N;i++) {
			line = br.readLine();
			for (int j=0;j<M;j++) {
				arr[i][j] = line.charAt(j);
				if (arr[i][j] == 'R') {
					redR = i;
					redC = j;
				} else if (arr[i][j] == 'B') {
					blueR = i;
					blueC = j;
				}
			}
		}
		dfs(arr, redR, redC, blueR, blueC, 0);
		if (flag) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}

	static void dfs(char[][] arr,int redR, int redC, int blueR, int blueC, int count) {
		if (count>=10 || flag) return;
		MoveResult moveResultRed = null;
		MoveResult moveResultBlue = null;
		for (int[] move: MOVES) {
			// 어떤 공이 더 우선순위 위치에 있는지 체크
			if (move[0]==1) {
				// 행이 큰 것 진행
				if (blueR<redR) {
					moveResultRed = moving(arr, redR, redC, move[0], move[1], 'R');
					moveResultBlue = moving(arr, blueR, blueC, move[0], move[1], 'B');
				} else {
					moveResultBlue = moving(arr, blueR, blueC, move[0], move[1], 'B');
					moveResultRed = moving(arr, redR, redC, move[0], move[1], 'R');
				}
			} else if (move[0]==-1) {
				if (blueR>redR) {
					moveResultRed = moving(arr, redR, redC, move[0], move[1], 'R');
					moveResultBlue = moving(arr, blueR, blueC, move[0], move[1], 'B');
				} else {
					moveResultBlue = moving(arr, blueR, blueC, move[0], move[1], 'B');
					moveResultRed = moving(arr, redR, redC, move[0], move[1], 'R');
				}
			} else if (move[1]==1) {
				if (blueC<redC) {
					moveResultRed = moving(arr, redR, redC, move[0], move[1], 'R');
					moveResultBlue = moving(arr, blueR, blueC, move[0], move[1], 'B');
				} else {
					moveResultBlue = moving(arr, blueR, blueC, move[0], move[1], 'B');
					moveResultRed = moving(arr, redR, redC, move[0], move[1], 'R');
				}
			} else if (move[1]==-1) {
				if (blueC>redC) {
					moveResultRed = moving(arr, redR, redC, move[0], move[1], 'R');
					moveResultBlue = moving(arr, blueR, blueC, move[0], move[1], 'B');
				} else {
					moveResultBlue = moving(arr, blueR, blueC, move[0], move[1], 'B');
					moveResultRed = moving(arr, redR, redC, move[0], move[1], 'R');
				}
			}
			// 구멍에 들어갔는지 체크. 구멍에 들어간 경우가 있다면 체크 이후 flag true 표시 이후 return
			if (moveResultRed.result && !moveResultBlue.result) {
				flag = true;
				return;
			} else if (!moveResultRed.result && !moveResultBlue.result) {
				// 둘 다 아직 안들어갔으면 다음 dfs

				dfs(arr, moveResultRed.nr, moveResultRed.nc, moveResultBlue.nr, moveResultBlue.nc, count+1);
			}
			arr[moveResultRed.nr][moveResultRed.nc] = '.';
			arr[moveResultBlue.nr][moveResultBlue.nc] = '.';
			arr[redR][redC] = 'R';
			arr[blueR][blueC] = 'B';
		}
	}

	static MoveResult moving(char[][] arr, int r, int c, int dr, int dc, char color) {
		// 공 하나를 굴렸는데, 벽을 만나면 그 이전 위치에 멈추고 마킹
		// 구멍을 만났으면 구멍에 들어감(true 반환) 및 구멍은 사라진걸로 취급함.
		// System.out.println("이전 배열");
		// for (char[] chars : arr) {
		// 	System.out.println(Arrays.toString(chars));
		// }
		int nr = r;
		int nc = c;

		while (true) {
			// System.out.println(color);
			// System.out.println(nr+" "+nc+" "+dr+" "+dc);
			if (arr[nr+dr][nc+dc]=='#' || arr[nr+dr][nc+dc]=='R' || arr[nr+dr][nc+dc]=='B') {
				arr[r][c] = '.';
				arr[nr][nc] = color;
				// System.out.println("이후 배열 - 구멍 안 빠짐");
				// for (char[] chars : arr) {
				// 	System.out.println(Arrays.toString(chars));
				// }
				return new MoveResult(nr, nc, false);
			}
			if (arr[nr+dr][nc+dc]=='O') {
				// System.out.println("이후 배열 - 구멍빠짐");
				// for (char[] chars : arr) {
				// 	System.out.println(Arrays.toString(chars));
				// }
				arr[r][c] = '.';
				return new MoveResult(nr, nc, true);
			}
			nr+=dr;
			nc+=dc;
		}
	}

	static class MoveResult {
		int nr;
		int nc;
		boolean result;
		MoveResult(int nr, int nc, boolean result) {
			this.nr = nr;
			this.nc = nc;
			this.result = result;
		}
	}
}
