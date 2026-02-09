import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1445 {
	/*
	1. 쓰레기를 지나가면 안된다.
	2. 쓰레기의 인접한 부분을 지나가서는 안된다.

	S: 시작 위치
	F: 꽃의 위치
	g: 쓰레기 위치
	.: 일반 칸

	쓰레기가 차있는 칸을 적게 지나가기
	이동은 가로 OR 세로
	쓰레기 위치를 가장 적게 이동하되 쓰레기가 인접한 칸을 적게 지나가야 함.
	N과 M: 3 ~ 50
	쓰레기 위치를 최대한 적게 이동 + 쓰레기 인접을 가장 적게 지나가는게 우선순위

	- 최단거리가 아님. 쓰레기 위치에 가장 적게 가야 함.
	- 무조건 도달은 하되 어쩔 수 없이 인접하거나 밟게 될 수 있다.
	브루트 포스? 위험
	메모이제이션 가능?
	그리디하게 가능? 불가능. 이번에 쓰레기를 밟는게 다음을 고려했을 때 더 나을 수 있음.
	visited[N][M][쓰레기를 몇번 밟았는지][쓰레기가 몇개 인접]
	 */
	static int totalG = 0;
	static int[][] MOVE = {{0,1},{1,0},{0,-1},{-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] arr = new char[N][M];
		int[][][] memo = new int[N][M][2];
		int[] start = {0,0};
		int[] flower = {0,0};
		for (int i=0;i<N;i++) {
			for (int j=0;j<M;j++) {
				Arrays.fill(memo[i][j], Integer.MAX_VALUE);
			}
		}
		for (int i=0;i<N;i++) {
			String line = br.readLine();
			for (int j=0;j<M;j++) {
				arr[i][j] = line.charAt(j);
				if (arr[i][j] == 'S') {
					start[0] = i;
					start[1] = j;
				} else if (arr[i][j] == 'F') {
					flower[0] = i;
					flower[1] = j;
				} else if (arr[i][j] == 'g') {
					totalG++;
				}
			}
		}
		memo[start[0]][start[1]][0] = 0;
		memo[start[0]][start[1]][1] = 0;
		dfs(arr, memo, start[0], start[1], flower, new int[]{0,0});

		// dfs(arr, minG, visited, start[0], start[1], flower, new int[]{0,0});
		System.out.println(memo[flower[0]][flower[1]][0]+" "+memo[flower[0]][flower[1]][1]);
	}

	public static void dfs(char[][] arr, int[][][] memo, int r, int c, int[] flower, int[] curG) {
		// System.out.println(r+" "+c);
		if (curG[0]>memo[flower[0]][flower[1]][0]) return;
		if (curG[0]==memo[flower[0]][flower[1]][0] && curG[1]>=memo[flower[0]][flower[1]][1]) return;
		if (r == flower[0] && c == flower[1]) {
			return;
		}
		for (int[] move:MOVE) {
			int nr = r+move[0];
			int nc = c+move[1];
			// 방문 여부
			if (nr<0 || nc<0 || nr>=arr.length || nc>=arr[0].length) continue;
			// 쓰레기 개수가 이전 방문보다 많은가?
			int garbageCnt = 0;
			int idjGarbageCnt = 0;
			if (arr[nr][nc] != 'F') {
				if (arr[nr][nc]=='g') {
					garbageCnt++;
				} else {
					// 인접한 쓰레기를 스쳐가는 경우
					if (nc > 0 && arr[nr][nc - 1] == 'g') {
						idjGarbageCnt++;
					} else if (nc < arr[0].length - 1 && arr[nr][nc + 1] == 'g') {
						idjGarbageCnt++;
					} else if (nr > 0 && arr[nr - 1][nc] == 'g') {
						idjGarbageCnt++;
					} else if (nr < arr.length - 1 && arr[nr + 1][nc] == 'g') {
						idjGarbageCnt++;
					}
				}
			}
			// System.out.println(curG[0]+garbageCnt);
			// System.out.println(curG[1]+idjGarbageCnt);
			// System.out.println(memo[nr][nc][0] + " " + memo[nr][nc][1]);
			if (memo[nr][nc][0]<curG[0]+garbageCnt) {
				continue;
			} else if ((memo[nr][nc][0]==curG[0]+garbageCnt) && (memo[nr][nc][1]<=curG[1]+idjGarbageCnt)) {
				continue;
			} else {
				// 진행
				memo[nr][nc][0] = curG[0]+garbageCnt;
				memo[nr][nc][1] = curG[1]+idjGarbageCnt;
			}
			curG[0] += garbageCnt;
			curG[1] += idjGarbageCnt;
			dfs(arr, memo, nr, nc, flower, curG);
			curG[0] -= garbageCnt;
			curG[1] -= idjGarbageCnt;
		}
	}
}
