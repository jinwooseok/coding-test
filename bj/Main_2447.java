import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2447 {
	/*
	3일 때 자동화로 구성하는 함수를 만들자. 그것에 규칙성을 활용해서 재귀하자.

	0. 규칙 체크
		1일때
		- *
		3일때
		- 1일때를 시작점부터 갓길을 따라 한바퀴 돌림
		9일때
		- 3일때를 시작점부터 갓길에 따라 한바퀴 돌림

	1. 목표하는 배열을 만들고 공백으로 채워넣는다.
	1부터 시작해서 재귀를 진행한다. 최대 7번 채워넣는 과정이 진행됨
	그런데 채워넣을 때 3+9+27+81+243+729+2200+6600 시간은 충분 => 하나하나 채워넣는 것으로 함.
	3의 8승은 대략 6600 정도로 메모리 공간, 시간적으로 배열로 표현하며 재귀 과정을 진행하기 적합하다.

	2. 배열을 돌릴 때 설계
	- 3**k 만큼의 배열을 돌릴 배열로 선택
	- 현재 위치 + 3**k의 위치에 * 혹은 공백 생성
	- n/(3**k) 횟수만큼 오른쪽 반복 진행, 그 다음부터 n/(3**k)-1 만큼 반복 진행
	 */
	static int[][] MOVE = {{0,1},{1,0},{0,-1},{-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] pows = new int[8];
		pows[0] = 1;
		for (int i=0;i<7;i++) {
			pows[i+1] = pows[i]*3;
		}

		char[][] arr = new char[N][N];
		for (int i=0;i<N;i++) {
			Arrays.fill(arr[i], ' ');
		}
		arr[0][0] = '*';
		make(arr, N, 1);
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	public static void make(char[][] arr, int n, int idx) {
		if (idx > n) return;
		int startR = 1;
		int startC = 1;
		for (int[] move:MOVE) { // 이동 방향
			// System.out.println((startR-1)+" "+(startC-1));
			// System.out.println(move[0]+" "+move[1]);
			for (int i=1;i<3;i++) { // 몇 개 생성?
				for (int j=0;j<idx/3;j++) { // 한 블럭의 행
					for (int k=0;k<idx/3;k++) { // 한 블럭의 열
						arr[startR+j-1][startC+k-1] = arr[j][k]; // idx만큼 건너뛴 위치에 생성
					}
				}
				startR = startR+idx/3*move[0];
				startC = startC+idx/3*move[1];
			}
		}
		make(arr, n, idx*3);
	}
}
