import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5373 {
	/*
	- 큐브가 모두 풀린 상태에서 시작함.
	- w: 흰색, y: 노란색, r: 빨간색, o: 오렌지 색, g: 초록색, b: 파란색
	- 시작 시
		- U : w 위
		- D : y 아래
		- F : r 앞
		- B : o 뒤
		- L : g 왼
		- R : b 오


위
앞 오른(3 idx) 뒤(6 idx) 왼(9 idx)
아래
  123456789012
1 www
2 www
3 www      123 (1열)
4 rrrbbboooggg4
5 rrrbbboooggg5
6 rrrbbboooggg6 (1열)
7 yyy      987 (1열)
8 yyy
9 yyy

F
		- 시계 방향:
			3행(1,2,3) -> 4열(4,5,6)
			4열(4,5,6) -> 7행(3,2,1)
			7행(3,2,1) -> 12열(6,5,4)
			12열(6,5,4) -> 3행(1,2,3)
규칙
U
- 시계 방향 : 4행을 왼쪽으로
- 반시계 방향 : 4행을 오른쪽으로
D
- 시계 방향 : 6행을 오른쪽으로
- 반시계 방향 : 6행을 왼쪽으로
F
- 시계 방향:
	3행(1,2,3) -> 4열(4,5,6)
	4열(4,5,6) -> 7행(3,2,1)
	7행(3,2,1) -> 12열(6,5,4)
	12열(6,5,4) -> 3행(1,2,3)
B
- 시계 방향:
	1행(3,2,1) -> 10열 (4,5,6)
	10열 (4,5,6) -> 9행 (1,2,3)
	9행 (1,2,3) -> 6열 (6,5,4)
	6열 (6,5,4) -> 1행 (3,2,1)
L
- 시계 방향:
	1열 (1,2,3) -> 1열 (4,5,6)
	1열 (4,5,6) -> 1열 (7,8,9)
	1열 (7,8,9) -> 6열 (6,5,4)
	6열 (6,5,4) -> 1열 (1,2,3)
R
- 시계 방향:
	3열 (3,2,1) -> 7열 (4,5,6)
	7열 (4,5,6) -> 3열 (9,8,7)
	3열 (9,8,7) -> 3열 (6,5,4)
	3열 (6,5,4) -> 3열 (3,2,1)
 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<N;i++) {
			char[][] arr = {
				{' ', ' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ', 'w','w','w',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ', 'w','w','w',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ', 'w','w','w',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ', 'r','r','r','b','b','b','o','o','o','g','g','g'},
				{' ', 'r','r','r','b','b','b','o','o','o','g','g','g'},
				{' ', 'r','r','r','b','b','b','o','o','o','g','g','g'},
				{' ', 'y','y','y',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ', 'y','y','y',' ',' ',' ',' ',' ',' ',' ',' ',' '},
				{' ', 'y','y','y',' ',' ',' ',' ',' ',' ',' ',' ',' '}
			};
			int count = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0;j<count;j++) {
				String command = st.nextToken();
				char loc = command.charAt(0);
				char direction = command.charAt(1);
				if (loc == 'L') {
					turnLeft(arr, direction);
				} else if (loc == 'R') {
					turnRight(arr, direction);
				} else if (loc == 'F') {
					turnFront(arr, direction);
				} else if (loc == 'B') {
					turnBack(arr, direction);
				} else if (loc == 'U') {
					turnUp(arr, direction);
				} else if (loc == 'D') {
					turnDown(arr, direction);
				}
				// for (char[] chars : arr) {
				// 	System.out.println(Arrays.toString(chars));
				// }
			}
			for (int j=1;j<4;j++) {
				for (int k=1;k<4;k++) {
					sb.append(arr[j][k]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

	public static void turnUp(char[][] arr, char direction) {
		/*
		U
		- 시계 방향 : 4행을 왼쪽으로
		- 반시계 방향 : 4행을 오른쪽으로
		 */
		char[] tmp;
		if (direction == '-') {
			tmp = new char[] {arr[4][10], arr[4][11], arr[4][12]};
			for (int i=9;i>0;i--) {
				arr[4][i+3] = arr[4][i];
			}
			arr[4][1] = tmp[0];
			arr[4][2] = tmp[1];
			arr[4][3] = tmp[2];
			rotateSubMatrixLeft90InPlace(arr, 1, 3, 1, 3);
		} else {
			tmp = new char[] {arr[4][1], arr[4][2], arr[4][3]};
			for (int i=4;i<13;i++) {
				arr[4][i-3] = arr[4][i];
			}
			arr[4][10] = tmp[0];
			arr[4][11] = tmp[1];
			arr[4][12] = tmp[2];
			rotateSubMatrixRight90InPlace(arr, 1, 3, 1, 3);
		}
	}

	public static void turnDown(char[][] arr, char direction) {
		/*
		D
		- 시계 방향 : 6행을 오른쪽으로
		- 반시계 방향 : 6행을 왼쪽으로
		 */
		char[] tmp;
		if (direction == '-') {
			tmp = new char[] {arr[6][1], arr[6][2], arr[6][3]};
			for (int i=4;i<13;i++) {
				arr[6][i-3] = arr[6][i];
			}
			arr[6][10] = tmp[0];
			arr[6][11] = tmp[1];
			arr[6][12] = tmp[2];
			rotateSubMatrixLeft90InPlace(arr, 7, 9, 1, 3);
		} else {
			tmp = new char[] {arr[6][10], arr[6][11], arr[6][12]};
			for (int i=9;i>0;i--) {
				arr[6][i+3] = arr[6][i];
			}
			arr[6][1] = tmp[0];
			arr[6][2] = tmp[1];
			arr[6][3] = tmp[2];
			rotateSubMatrixRight90InPlace(arr, 7, 9, 1, 3);
		}
	}

	public static void turn(char[][] arr, int[][][][] set, char direction) {
		// 변경 셋들을 순서대로 나열해서 넣으면 그 순서대로 배열에 적용하기
		// 라스트를 보관, 라스트-1을 라스트로, ....첫째번에 라스트 넣기
		//[[[before1],[before2],[before3]], [[after1],[after2],[after3]],
		// [[before1],[before2],[before3]], [[after1],[after2],[after3]],
		// ]
		char[] tmp = new char[3];
		// for (char[] chars : arr) {
		// 	System.out.println(Arrays.toString(chars));
		// }
		if (direction == '+') {
			for (int i=0;i<3;i++) {
				tmp[i] = arr[set[set.length-1][0][i][0]][set[set.length-1][0][i][1]];
			}
			for (int i=set.length-2;i>=0;i--) {
				for (int j=0;j<3;j++) {
					arr[set[i][1][j][0]][set[i][1][j][1]] = arr[set[i][0][j][0]][set[i][0][j][1]];
				}
			}

			for (int j=0;j<3;j++) {
				arr[set[0][0][j][0]][set[0][0][j][1]] = tmp[j];
			}

		} else {
			for (int i=0;i<3;i++) {
				tmp[i] = arr[set[0][0][i][0]][set[0][0][i][1]];
			}
			for (int i=0;i<set.length-1;i++) {
				for (int j=0;j<3;j++) {
					arr[set[i][0][j][0]][set[i][0][j][1]] = arr[set[i][1][j][0]][set[i][1][j][1]];
				}
			}

			for (int j=0;j<3;j++) {
				arr[set[set.length-1][0][j][0]][set[set.length-1][0][j][1]] = tmp[j];
			}
		}

	}
	public static void turnRight(char[][] arr, char direction) {
		/*
		R
		- 시계 방향:
			3열 (3,2,1) -> 7열 (4,5,6)
			7열 (4,5,6) -> 3열 (9,8,7)
			3열 (9,8,7) -> 3열 (6,5,4)
			3열 (6,5,4) -> 3열 (3,2,1)
		 */
		turn(arr, new int[][][][] {
			{{{3, 3}, {2, 3}, {1, 3}}, {{4, 7}, {5, 7}, {6, 7}}},
			{{{4, 7}, {5, 7}, {6, 7}}, {{9, 3}, {8, 3}, {7, 3}}},
			{{{9, 3}, {8, 3}, {7, 3}}, {{6, 3}, {5, 3}, {4, 3}}},
			{{{6, 3}, {5, 3}, {4, 3}}, {{3, 3}, {2, 3}, {1, 3}}},
		}, direction);
		if (direction == '+') {
			rotateSubMatrixRight90InPlace(arr, 4, 6, 4, 6);
		} else {
			rotateSubMatrixLeft90InPlace(arr, 4, 6, 4, 6);
		}
	}
/*
1
1
L-
 */
	public static void turnLeft(char[][] arr, char direction) {
		/*
		L
		- 시계 방향:
			1열 (1,2,3) -> 1열 (4,5,6)
			1열 (4,5,6) -> 1열 (7,8,9)
			1열 (7,8,9) -> 7열 (6,5,4)
			7열 (6,5,4) -> 1열 (1,2,3)
		 */
		turn(arr, new int[][][][] {
			{{{1, 1}, {2, 1}, {3, 1}}, {{4, 1}, {5, 1}, {6, 1}}},
			{{{4, 1}, {5, 1}, {6, 1}}, {{7, 1}, {8, 1}, {9, 1}}},
			{{{7, 1}, {8, 1}, {9, 1}}, {{6, 9}, {5, 9}, {4, 9}}},
			{{{6, 9}, {5, 9}, {4, 9}}, {{1, 1}, {2, 1}, {3, 1}}},
		}, direction);
		if (direction == '+') {
			rotateSubMatrixRight90InPlace(arr, 4, 6, 10, 12);
		} else {
			rotateSubMatrixLeft90InPlace(arr, 4, 6, 10, 12);
		}
	}

	public static void turnFront(char[][] arr, char direction) {
		/*
		F
		- 시계 방향:
			3행(1,2,3) -> 4열(4,5,6)
			4열(4,5,6) -> 7행(3,2,1)
			7행(3,2,1) -> 12열(6,5,4)
			12열(6,5,4) -> 3행(1,2,3)
		 */

		turn(arr, new int[][][][] {
			{{{3, 1}, {3, 2}, {3, 3}}, {{4, 4}, {5, 4}, {6, 4}}},
			{{{4, 4}, {5, 4}, {6, 4}}, {{7, 3}, {7, 2}, {7, 1}}},
			{{{7, 3}, {7, 2}, {7, 1}}, {{6, 12}, {5, 12}, {4, 12}}},
			{{{6, 12}, {5, 12}, {4, 12}}, {{3, 1}, {3, 2}, {3, 3}}},
		}, direction);

		if (direction == '+') {
			rotateSubMatrixRight90InPlace(arr, 4, 6, 1, 3);
		} else {
			rotateSubMatrixLeft90InPlace(arr, 4, 6, 1, 3);
		}
	}

	public static void turnBack(char[][] arr, char direction) {
		/*
		B
		- 시계 방향:
			1행(3,2,1) -> 10열 (4,5,6)
			10열 (4,5,6) -> 9행 (1,2,3)
			9행 (1,2,3) -> 6열 (6,5,4)
			6열 (6,5,4) -> 1행 (3,2,1)
		 */
		turn(arr, new int[][][][] {
			{{{1, 3}, {1, 2}, {1, 1}}, {{4, 10}, {5, 10}, {6, 10}}},   // U -> R
			{{{4, 10}, {5, 10}, {6, 10}}, {{9, 1}, {9, 2}, {9, 3}}}, // R -> D
			{{{9, 1}, {9, 2}, {9, 3}}, {{6, 6}, {5, 6}, {4, 6}}},   // D -> L
			{{{6, 6}, {5, 6}, {4, 6}}, {{1, 3}, {1, 2}, {1, 1}}},   // L -> U
		}, direction);

		if (direction == '+') {
			rotateSubMatrixRight90InPlace(arr, 4, 6, 7, 9);
		} else {
			rotateSubMatrixLeft90InPlace(arr, 4, 6, 7, 9);
		}
	}

	public static void rotateSubMatrixRight90InPlace(char[][] arr, int r1, int r2, int c1, int c2) {
		int n = r2 - r1 + 1;

		for (int layer = 0; layer < n / 2; layer++) {
			int first = layer;
			int last = n - 1 - layer;
			for (int i = first; i < last; i++) {
				int offset = i - first;

				char top = arr[r1 + first][c1 + i];
				arr[r1 + first][c1 + i] = arr[r1 + last - offset][c1 + first];
				arr[r1 + last - offset][c1 + first] = arr[r1 + last][c1 + last - offset];
				arr[r1 + last][c1 + last - offset] = arr[r1 + i][c1 + last];
				arr[r1 + i][c1 + last] = top;
			}
		}
	}

	public static void rotateSubMatrixLeft90InPlace(char[][] arr, int r1, int r2, int c1, int c2) {
		int n = r2 - r1 + 1;

		for (int layer = 0; layer < n / 2; layer++) {
			int first = layer;
			int last = n - 1 - layer;

			for (int i = first; i < last; i++) {
				int offset = i - first;

				char top = arr[r1 + first][c1 + i];

				arr[r1 + first][c1 + i] = arr[r1 + i][c1 + last];
				arr[r1 + i][c1 + last] = arr[r1 + last][c1 + last - offset];
				arr[r1 + last][c1 + last - offset] = arr[r1 + last - offset][c1 + first];
				arr[r1 + last - offset][c1 + first] = top;
			}
		}
	}
}
/*
1
16
D- R+ F- F- R+ R- F- L+ U+ U+ U+ L+ L+ D- B+ B+

1
8
U+ B+ D+ R+ U- B- D- R-
 */