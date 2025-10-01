import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_17143 {
	/**
	 * 상어 배열 2개 생성
	 * 첫번째 열
	 * 가장 근처에 있는 상어 찾기
	 * 상어 이동 알고리즘 (이동 후 상어 처리 및 다음 열 가장 위쪽 행 기록)
	 * for 유저 이동
	 * 위에거 반복
	 * @param args
	 * @throws IOException
	 */
	static int[][] MOVE = {{0,0},{-1,0},{1,0},{0,1},{0,-1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// answer
		int answer = 0;

		// 샤크 배열 생성
		Shark[][] sharks1 = new Shark[R][C];
		Shark[][] sharks2 = new Shark[R][C];

		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			sharks1[r-1][c-1] = new Shark(r-1,c-1,s,d,z);
		}

		int nextSharkLoc = 999;
		// 첫번째 열 로직 작동
		for (int i=0;i<R;i++) {
			if (sharks1[i][0] != null) {
				nextSharkLoc = i;
				break;
			}
		}

		// 두번째부터 정규 로직 작동
		for (int i=0;i<C;i++) {
			// for (int j=0;j<R;j++) {
			// 	System.out.println(Arrays.toString(sharks1[j]));
			// }
			// for (int j=0;j<R;j++) {
			// 	System.out.println(Arrays.toString(sharks2[j]));
			// }
			if (i%2==1) {
				// 가장 앞에 있는 상어 포획
				if (nextSharkLoc!=999) {
					answer+=getShark(sharks2, i, nextSharkLoc).size;
				}
				// 상어 이동 (move)
				nextSharkLoc = move(sharks2, sharks1, i);
			} else {
				// 가장 앞에 있는 상어 포획
				if (nextSharkLoc!=999) {
					answer+=getShark(sharks1, i, nextSharkLoc).size;
				}
				// 상어 이동 (move)
				nextSharkLoc = move(sharks1, sharks2, i);
			}
		}
		System.out.println(answer);
	}

	static Shark getShark(Shark[][] targetArr, int currentC, int sharkRow) {
		Shark shark = targetArr[sharkRow][currentC];
		targetArr[sharkRow][currentC] = null;
		return shark;
	}

	static int move(Shark[][] from, Shark[][] to, int currentC) {
		int nextSharkRow = 999;

		// 상어 이동 로직 작동
		for (int i=0;i<from.length;i++) {
			for (int j=0;j<from[0].length;j++) {
				// 상어 이동
				if (from[i][j] == null) {
					continue;
				} else {
					Shark shark =from[i][j];
					from[i][j] = null;
					shark.r = shark.r+MOVE[shark.direction][0]*shark.speed;
					shark.c = shark.c+MOVE[shark.direction][1]*shark.speed;

					int shiftR = Math.abs(shark.r)/(from.length-1);
					int leftR = Math.abs(shark.r)%(from.length-1);
					int shiftC = Math.abs(shark.c)/(from[0].length-1);
					int leftC = Math.abs(shark.c)%(from[0].length-1);
					// + 인경우
					// if shift가 짝수면 방향이 같음. 위치는 (총인덱스-나머지)와 같음
					// if shift가 홀수면 방향 반대. 위치는 나머지와 같음
					// - 인경우 if shift기준 위와 정반대
					if (shark.r<0) {
						if (shiftR%2==0) {
							shark.r=leftR;
							shark.direction=2;
						} else {
							shark.r=(from.length-1)-leftR;
						}
					} else if (shark.r>=from.length) {
						if (shiftR%2==0) {
							shark.r=leftR;
						} else {
							shark.r=(from.length-1)-leftR;
							shark.direction=1;
						}
					}
					if (shark.c<0) {
						if (shiftC%2==0) {
							shark.c=leftC;
							shark.direction=3;
						} else {
							shark.c=(from[0].length-1)-leftC;
						}
					} else if (shark.c>=from[0].length) {
						if (shiftC%2==0) {
							shark.c=leftC;
						} else {
							shark.c=(from[0].length-1)-leftC;
							shark.direction=4;
						}
					}
					if (to[shark.r][shark.c] == null ||(to[shark.r][shark.c] != null && to[shark.r][shark.c].size<shark.size)) {
						to[shark.r][shark.c] = shark;
					}
					// nextSharkRow 업데이트
					if (shark.c == currentC+1) {
						nextSharkRow = Math.min(nextSharkRow, shark.r);
					}
				}
			}
		}
		return nextSharkRow;
	}

	static class Shark {
		int r;
		int c;
		int speed;
		int direction;
		int size;

		Shark(int r, int c, int speed, int direction, int size) {
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.direction = direction;
			this.size = size;
		}
	}
}
