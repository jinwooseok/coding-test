import java.util.*;
import java.io.*;
public class Main_16236 {
	/*
	 * 자신보다 큰 물고기가 있는 칸 이동 불가.
	 * 초기 크기 2
	 * 상하좌우 이동
	 * 크기가 같은 물고기는 먹을 수 없지만 지나가는건 가능
	 * 자기보다 큰 물고기가 있는 칸은 지나갈 수 없음
	 * 1. 먹을 수 있는 물고기가 1마리면 먹으러간다.
	 * 2. 먹을 수 있는 물고기가 1마리보다 많다면 가장 가까운 물고기를 먹으러 간다.
	 *  - 거리는 그냥 칸의 개수 차이의 최소값
	 *  - 가장 위, 왼쪽에 있는거
	 *  - 자신의 크기와 같은 수의 물고기를 먹을 때마다 크기가 1 증가
	 *  
	 *  물고기의 위치는 bfs? 미리 체크해두자
	 */
	static int[][] MOVE = {{0,1},{1,0},{0,-1},{-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[N][N];
		Baby baby = new Baby();
		baby.size = 2;
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 9) {
					baby.r = i;
					baby.c = j;
					arr[i][j]=0;
				}
			}
		}
		int time = 0;
		while (true) {
			Queue<int[]> q = new LinkedList<>();
			boolean[][] visited = new boolean[N][N];
			q.add(new int[] {baby.r, baby.c});
			visited[baby.r][baby.c] = true;
			int beforer = baby.r;
			int beforec = baby.c;
			int cnt = 0;
			while (!q.isEmpty()) {
				int size = q.size();
				int targetr = 20;
				int targetc = 20;
				//System.out.println(cur[0]+" "+cur[1]);
				for (int i=0;i<size;i++) {
					int[] cur = q.poll();
					for (int[] move:MOVE) {
						int nr = cur[0]+move[0];
						int nc = cur[1]+move[1];
						if (nr<0||nc<0||nr>=N||nc>=N||visited[nr][nc]) {
							continue;
						}
						visited[nr][nc] = true;
						if (arr[nr][nc]>baby.size) {
							continue;
						} else if (arr[nr][nc]==baby.size || arr[nr][nc]==0) {
							q.add(new int[] {nr, nc});
						} else {
							if (nr<targetr) {
								targetr = nr;
								targetc = nc;
							} else if (nr==targetr) {
								if (nc<targetc) {
									targetr = nr;
									targetc = nc;
								}
							}
						}
					}				
				}
				cnt+=1;
				if (targetr != 20 && targetc != 20) {
					baby.r = targetr;
					baby.c = targetc;
					baby.exp++;
					baby.sizeup();
					arr[targetr][targetc] = 0;
					break;
				}
			}
			if (beforer==baby.r && baby.c==beforec) {
				break;
			}
			time+=cnt;
		}
		System.out.println(time);
	}
	
	
	static class Baby {
		int size;
		int exp;
		int r;
		int c;
		
		Baby() {}
		Baby(int r, int c) {
			this.size = 2;
			this.exp = 0;
			this.r = r;
			this.c = c;
		}
		
		boolean sizeup() {
			if (this.size == this.exp) {
				this.size++;
				this.exp = 0;
				return true;
			} else {
				return false;
			}
		}
	}
}
