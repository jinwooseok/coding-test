import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_18809 {
	/*
	배양액을 뿌릴 수 있는 땅은 정해져있음
	초록 배양액, 빨간 배양액 : 배양액이 동일 시간에 도달한 땅에서는 꽃이 피어남. 이후 퍼지지 않음.

	하얀색 땅 : 배양액 못뿌림
	황토색 칸 : 배양액 뿌릴 수 있음
	파란색 칸 : 벽

	모든 배양액을 뿌려야 함.

	N,M: 50*50
	R,G: 5, 5
	0: 호수
	1: 배양액 못뿌림
	2: 배양액 뿌릴 수 있음
	항상 모든 배양액을 뿌릴 수 있음

	꽃이 피면 배양액이 사라져 배양액을 퍼트리지 않음
	그 땅에 다시 배양액을 뿌리는 것은 불가능으로 보여짐.
	배양액은 사방으로 퍼짐

	동시에 만나야 하는듯.
	배양액을 뿌릴 장소는 처음에 정해야 함.

	1. 각 배양액을 뿌릴 장소 선택
		장소를 선택 -> 2500개중에 5개선택 후 5개 선택
		2500!/5!2495! * 2495!/5!2490!
		뿌리는 방식은 모든 칸을 순회하며 조합을 찾음(comb). 조합을 다 찾은 이후
		퍼지는 과정 진행. 그 조합을 q에 저장
		이후 백트래킹으로 제거하기
		근데 이렇게 하면 찾는데만 시간초과가 날 수 있음..
		배양액을 뿌릴 수 있는 칸은 10개 이하.
		10개 중 r,g개를 찾음

	2. 배양액이 퍼짐
		배양액이 퍼지다가 동일 시간에 동일 칸에 도달한 경우 꽃 핀 이후 사라짐
	 	bfs를 통해 진행.
	 	배열은 오염됨. 매번 초기화하는 방식? bfs는 결국 2500번 순회하게 됨.(완전탐색됨)

	 조합 찾기 경우의 수 : 252
	 bfs + 배열 생성 : 2500 + 2500*2 (선형)

	 입력 방식
	 1. 기본 배열 입력, 뿌릴 수 있는 땅 목록: list<Integer> (M*r+c로 계산)
	 2. r,g 정해지면 배열과 q에 바로 입력 (Liquid 클래스)
	 3. bfs 실행 배열은 boolean[N][M][2], execute 함수에서 시작 시 시작위치 부여

	 메모리 초과 걱정? 있지만 우선 패스 (없을 것 같음)
	 */
	static int[][][] visited;
	static int[][] arr;
	static List<Integer> possibleLands;
	static Queue<Liquid> liquids;
	static int N, M, G, R;
	static int maxCnt;
	static int[][] MOVES = {{0,1},{0,-1},{1,0},{-1,0}};
	static final int FLOWER = -1;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		maxCnt = 0;
		arr = new int[N][M];
		possibleLands = new ArrayList<>();
		liquids = new ArrayDeque<>();
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					possibleLands.add(M*i+j);
				}
			}
		}
		int[] assigned = new int[possibleLands.size()];
		// System.out.println(possibleLands);
		comb(0, G, R, assigned);
		System.out.println(maxCnt);
	}

	static void comb(int idx, int gRemains, int rRemains, int[] assigned) {
		// 다썼으면 진행
		if (gRemains==0 && rRemains==0) {
			visited = new int[N][M][2];
			for (int i=0;i<possibleLands.size();i++) {
				if (assigned[i] != 0) {
					visited[possibleLands.get(i)/M][possibleLands.get(i)%M][assigned[i]-1] = 1;
					liquids.add(new Liquid(assigned[i],possibleLands.get(i)/M, possibleLands.get(i)%M, 1));
				}
			}
			// System.out.println(liquids);
			int cnt = execute();
			// System.out.println("결과 :"+cnt);
			maxCnt = Math.max(cnt, maxCnt);
			return;
		}
		// 다 안쓰고 끝자리를 넘어선 경우 리턴
		if (idx>=possibleLands.size()) {
			return;
		}
		if (rRemains>0) {
			assigned[idx] = 1;
			comb(idx+1, gRemains, rRemains-1, assigned);
			assigned[idx] = 0;
		}
		if (gRemains>0) {
			assigned[idx] = 2;
			comb(idx+1, gRemains-1, rRemains, assigned);
			assigned[idx] = 0;
		}
		comb(idx+1, gRemains, rRemains, assigned);
	}

	static int execute() {
		int cnt = 0;
		while (!liquids.isEmpty()) {
			Liquid liquid = liquids.poll();
			if (visited[liquid.r][liquid.c][0] == visited[liquid.r][liquid.c][1])
				continue;
			for (int[] move:MOVES) {
				int nr = liquid.r+move[0];
				int nc = liquid.c+move[1];

				if (nr<0 || nc<0 || nr>=N || nc>=M || arr[nr][nc]==0) continue;

				if (visited[nr][nc][0] == FLOWER) continue;

				if (visited[nr][nc][liquid.color-1]>0
					&& visited[nr][nc][liquid.color-1]<=liquid.time+1) continue;

				if (visited[nr][nc][liquid.color-1] == 0) {
					visited[nr][nc][liquid.color-1] = liquid.time+1;

					if (visited[nr][nc][0]==liquid.time+1 && visited[nr][nc][1]==liquid.time+1) {
						cnt++;
						visited[nr][nc][0] = FLOWER;
						visited[nr][nc][1] = FLOWER;
						continue;
					}
				}

				liquids.add(new Liquid(liquid.color, nr, nc, liquid.time+1));
			}
		}
		// System.out.println(cnt);
		return cnt;
	}
}

class Liquid {
	int color; // R: 1, G: 2
	int r;
	int c;
	int time;

	Liquid(int color, int r, int c, int time) {
		this.color = color;
		this.r = r;
		this.c = c;
		this.time = time;
	}

	@Override
	public String toString() {
		return color+" "+r+" "+c+" "+time;
	}
}
/*
[2 0 3 1, 1 0 5 1, 2 1 6 1, 1 3 4 1, 2 4 1 1]
결과 :6

10ggfrf
11g0r0g
2100rfg
10rrrr0
0ggf0r2

 */