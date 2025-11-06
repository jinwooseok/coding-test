import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_19236 {
	static int[][] MOVE = {{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
	static int maxCnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int[][] arr = new int[4][4];
		Fish[] fishes = new Fish[16];

		for (int i=0; i<4; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<4; j++) {
				int num = Integer.parseInt(st.nextToken()) - 1;
				int dir = Integer.parseInt(st.nextToken()) - 1;
				fishes[num] = new Fish(i, j, dir);
				arr[i][j] = num;
			}
		}

		// 상어 첫 입장 - (0,0) 물고기 먹음
		int firstFish = arr[0][0];
		int firstDir = fishes[firstFish].dir;
		int firstCnt = firstFish + 1;
		fishes[firstFish] = null;
		arr[0][0] = 16;

		Shark shark = new Shark(0, 0, firstDir, firstCnt);

		// 상어 이동 시작
		moveShark(arr, fishes, shark);

		System.out.println(maxCnt);
	}

	// 물고기 이동 함수
	static void moveFishes(int[][] arr, Fish[] fishes) {
		for (int i=0; i<16; i++) {
			if (fishes[i] == null) continue;

			Fish fish = fishes[i];

			for (int j=0; j<8; j++) {
				int nextDir = (fish.dir + j) % 8;
				int nr = fish.r + MOVE[nextDir][0];
				int nc = fish.c + MOVE[nextDir][1];

				// 범위 밖이거나 상어가 있으면 회전
				if (nr<0 || nc<0 || nr>=4 || nc>=4 || arr[nr][nc]==16) {
					continue;
				}

				// 이동 가능
				// 빈 칸으로 이동
				if (arr[nr][nc] == -1) {
					arr[nr][nc] = i;
					arr[fish.r][fish.c] = -1;
				}
				// 다른 물고기와 위치 교환
				else {
					int otherFish = arr[nr][nc];
					fishes[otherFish].r = fish.r;
					fishes[otherFish].c = fish.c;
					arr[fish.r][fish.c] = otherFish;
					arr[nr][nc] = i;
				}

				fish.r = nr;
				fish.c = nc;
				fish.dir = nextDir;

				break;
			}
		}
	}

	// 상어 이동 함수
	static void moveShark(int[][] arr, Fish[] fishes, Shark shark) {
		// 먼저 물고기 이동
		moveFishes(arr, fishes);

		boolean canMove = false;

		// 상어가 이동할 수 있는 모든 경우 탐색
		for (int jump=1; jump<4; jump++) {
			int nr = shark.r + MOVE[shark.dir][0] * jump;
			int nc = shark.c + MOVE[shark.dir][1] * jump;

			// 범위 밖
			if (nr<0 || nc<0 || nr>=4 || nc>=4) break;

			// 빈 칸
			if (arr[nr][nc] == -1) continue;

			// 물고기가 있는 칸
			canMove = true;

			// *** 각 이동마다 새로운 복사본 생성 ***
			int[][] copyArr = copyArray(arr);
			Fish[] copyFishes = copyFishes(fishes);

			// 물고기 먹기
			int fishNum = copyArr[nr][nc];
			int newDir = copyFishes[fishNum].dir;
			int newCnt = shark.cnt + fishNum + 1;

			copyFishes[fishNum] = null;
			copyArr[shark.r][shark.c] = -1;  // 원래 상어 위치는 빈 칸
			copyArr[nr][nc] = 16;  // 새 상어 위치

			// 다음 상어 이동 (새 복사본으로)
			moveShark(copyArr, copyFishes, new Shark(nr, nc, newDir, newCnt));
		}

		// 더 이상 이동할 수 없으면 최댓값 갱신
		if (!canMove) {
			maxCnt = Math.max(maxCnt, shark.cnt);
		}
	}

	// 배열 깊은 복사
	static int[][] copyArray(int[][] arr) {
		int[][] copy = new int[4][4];
		for (int i=0; i<4; i++) {
			for (int j=0; j<4; j++) {
				copy[i][j] = arr[i][j];
			}
		}
		return copy;
	}

	// Fish 배열 깊은 복사
	static Fish[] copyFishes(Fish[] fishes) {
		Fish[] copy = new Fish[16];
		for (int i=0; i<16; i++) {
			if (fishes[i] != null) {
				Fish f = fishes[i];
				copy[i] = new Fish(f.r, f.c, f.dir);
			}
		}
		return copy;
	}

	static class Fish {
		int r, c, dir;
		Fish(int r, int c, int dir) {
			this.r = r;
			this.c = c;
			this.dir = dir;
		}
	}

	static class Shark {
		int r, c, dir, cnt;
		Shark(int r, int c, int dir, int cnt) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.cnt = cnt;
		}
	}
}