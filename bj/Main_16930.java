import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16930 {
	/*
	전체를 다 돌 수 있는 경우와 없는 경우
	- 행이 홀수 개인 경우 무조건 다 돌 수 있음
		- 명령어
		R*(M-1)+D+L*(M-1)+D 반복.. (도달 시까지)
	- 열이 홀수 개인 경우
		명령어
		D*(N-1)+R+U*(N-1)+R+D(N-1) 반복. (도달 시까지)
	- 행열이 짝수인 경우
		- 뺄 것 찾기
		- 뺄 것이 존재하는 행번호(2줄단위로 찾기)
		- 그 행을 제외하고
		R*(M-1)+D+L*(M-1)+D 반복.. (도달 시까지)
		- 그 행
		DRURDRU
		단 아래나 위에 제외하려는게 있는 경우 R수행 이후 다시 D OR U
		- 이후
		D+L(M-1)+D+R(M-1) ...

	- 행이 짝수 개인 경우, 열이 홀수 개인 경우 무조건 다 돌 수 있음
	- 행이 짝수 개, 열이 짝수 개인 경우
		1 1 1 1
		1 1 1 1
		1 1 1 1
		1 1 1 1

		1개 버릴 수 있는 경우
		1 * 3 *
		* 3 * 4
		1 * 1 *
		* 1 * 1

		3개 버릴 수 있는 경우 - 최소 1개 버릴 수 있는 경우보다 좋은 결과를 얻을 수 있어야 함
		1 * 1 *
		* 1 * 1

		6개 실험

		1 * 1 * 1 *
		* 1 * 1 * 1
		1 * 1 * 1 *
		* 1 * 1 * 1

		1*1*1*
		*1*1*1
		1개만 제외하는 경우는 다음과 같음. 그치만 그 제외보다 더 크게 제외하는 경우가 있을수도 있음
		끝과 붙은 3개를 빼는 경우가 그러함. 그 이후엔 연속된 5개짜리도 나올 듯함..

		-> 진입 지점 이후가 열이 홀수 개거나 행이 홀수개인 걸로 바꾸면 됨 (나머지는 포기)

		1. 4*3 + 마지막
		2. 3*4 + 마지막
		3. 1개만 버릴수있는 경우는?
		테스트 결과 2개 이상 버리는 경우는 항상 1개 버리는 경우를 포함함. 즉 무조건 더 많이 버리게 됨
		버릴 수 있는 1개중 가장 작은 수를 뽑음. 앞으로 그것을 제외하고 맨 아래 도달하는 경우를 찾을 거임. 나머지 다 포함해서

		나머지 부분들을 모두 포함하며 지나가는 경로를 만들어야 하는데 이것도 나름 난감하네
		1개를 제거하는 경우, 그 줄로부터 두줄을 활용해 피하고, 나머지는 순수 끝까지.

		2번하자. 그게 쉬울듯함. 나머지 줄들은 왔다리 갔다리. 뺄것이 포함된 부분(2줄)은 그 부분만 신중하게 가야함
		2줄의 마지막에 위치하게 됨 항상.

		DRURDRU 순으로 마지막 부분까지 이동. BUT 아래나 위에 있는 경우 같은 명령(R한번 더)

	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		/*
		- 행이 홀수 개인 경우 무조건 다 돌 수 있음
		- 명령어
		R*(M-1)+D+L*(M-1)+D 반복.. (도달 시까지)
		- 열이 홀수 개인 경우
			명령어
			D*(N-1)+R+U*(N-1)+R+D(N-1) 반복. (도달 시까지)
		 */
		StringBuilder sb = new StringBuilder();
		int r = 0;
		int c = 0;
		if ((N & 1) != 0) {
			while (true) {
				if (c == 0) {
					for (int i=0;i<M-1;i++) {
						c++;
						sb.append('R');
					}
					if (r == N-1 && c == M-1) break;

					r++;
					sb.append('D');
				} else if (c == M-1) {
					for (int i=0;i<M-1;i++) {
						c--;
						sb.append('L');
					}

					r++;
					sb.append('D');
				}
			}

		} else if ((M & 1) != 0) {
			while (true) {
				if (r == 0) {
					for (int i=0;i<N-1;i++) {
						r++;
						sb.append('D');
					}
					if (r == N-1 && c == M-1) break;

					c++;
					sb.append('R');
				} else if (r == N-1) {
					for (int i=0;i<N-1;i++) {
						r--;
						sb.append('U');
					}

					c++;
					sb.append('R');
				}
			}
		} else {
			/*
			- 행열이 짝수인 경우
			- 뺄 것 찾기
			- 뺄 것이 존재하는 행번호(2줄단위로 찾기)
			- 그 행을 제외하고
			R*(M-1)+D+L*(M-1)+D 반복.. (도달 시까지)
			- 그 행
			DRURDRU
			단 아래나 위에 제외하려는게 있는 경우 R수행 이후 다시 D OR U
			- 이후
			D+L(M-1)+D+R(M-1) ...
			 */

			// 뺄 것 찾기
			int targetR = 0;
			int targetC = 0;
			int min = 10000;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (((i + j) & 1) != 0) {
						if (min > arr[i][j]) {
							targetR = i;
							targetC = j;
							min = arr[i][j];
						}
					}
				}
			}

			// 뺄 것의 구간 찾기
			int start = (targetR / 2) * 2;
			// System.out.println(start);
			// System.out.println(targetR+" "+targetC);
			// 뺄 것 구간 이전까지 이동시키기
			while (r != start) {
				if (c == 0) {
					for (int i = 0; i < M - 1; i++) {
						c++;
						sb.append('R');
					}
					if (r == N - 1 && c == M - 1)
						break;

					r++;
					sb.append('D');
				} else if (c == M - 1) {
					for (int i = 0; i < M - 1; i++) {
						c--;
						sb.append('L');
					}

					r++;
					sb.append('D');
				}
			}
			// System.out.println(r+" "+c);
			if (!(r == N - 1 && c == M - 1)) {
				// r이 시작 위치 도달 -> 마지막 위치 도달 시까지 반복
				while (!(r == (start + 1) && c == M - 1)) {
					// System.out.println(r+" "+c);
					if ((r & 1) == 0) {
						if (c == targetC) {
							c++;
							sb.append('R');
						} else {
							r++;
							sb.append('D');
							if (r == (start + 1) && c == M - 1)
								break;
							c++;
							sb.append('R');
						}
					} else {
						if (c == targetC) {
							c++;
							sb.append('R');
						} else {
							r--;
							sb.append('U');
							if (r == (start + 1) && c == M - 1)
								break;
							c++;
							sb.append('R');
						}
					}
				}
			}

			if (!(r == N - 1 && c == M - 1)) {
				// 뺄 것 구간 이전까지 이동시키기
				while (true) {
					if (r == N - 1 && c == M - 1)
						break;

					r++;
					sb.append('D');

					if (c == 0) {
						for (int i = 0; i < M - 1; i++) {
							c++;
							sb.append('R');
						}
						if (r == N - 1 && c == M - 1)
							break;
					} else if (c == M - 1) {
						for (int i = 0; i < M - 1; i++) {
							c--;
							sb.append('L');
						}
					}
				}
			}
		}
		System.out.println(sb);
	}
}
