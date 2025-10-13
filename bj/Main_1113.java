import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1113 {
	/**
	 * 1. bfs
	 * 2. 방문한 노드를 기준으로 불가능 확정 체크
	 * 3. bfs로 돌면서 나가는 길 없이 다 체크가 된 경우
	 * 4. 만난 벽들 중 가장 낮은 벽으로 업데이트
	 * 5. 반복해서 끝까지
	 *
	 * 준비물
	 * arr, q, wall(물 담는게 절대 불가능한 부분 체크), sum, tmpq(임시로 바꿀 것들을 저장해놨다 한바퀴 더 돌리기-선형)
	 *
	 * @param args
	 * @throws IOException
	 */
	static int[][] MOVE = {{0,1},{1,0},{0,-1},{-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		int sum = 0;
		Queue<Integer> q = new LinkedList<>();;
		Queue<Integer> temp = new LinkedList<>();;
		Queue<Integer> visitedq = new LinkedList<>();;
		boolean[][] visited = new boolean[N][M];
		boolean[][] notBottom = new boolean[N][M];
		for (int i=0;i<N;i++) {
			String row = br.readLine();
			for (int j=0;j<M;j++) {
				arr[i][j]=row.charAt(j)-'0';
			}
		}
		// for (int i=0;i<N;i++) {
		// 	System.out.println(Arrays.toString(arr[i]));
		// }
		for (int i=0;i<N;i++) {
			for (int j=0;j<M;j++) {
				sum+=bfs(q, temp, arr, i, j, notBottom, visited);
				for (int k=0;k<N;k++) {
					Arrays.fill(visited[k], false);
				}
				// System.out.println("-----------");
				// for (int k=0;k<arr.length;k++) {
				// 	System.out.println(Arrays.toString(visited[k]));
				// }
			}
		}
		System.out.println(sum);
	}
	public static int bfs(Queue<Integer> q,Queue<Integer> temp,int[][] arr, int startR, int startC, boolean[][] notBottom, boolean[][] visited) {
		// 무조건 바닥이 아니라면 0 반환
		if (notBottom[startR][startC]) return 0;

		// 최소벽은 bottom보다는 높아야함.
		int minWall = 10;
		int sum = 0;
		int bottom = arr[startR][startC];
		int start = startR*arr[0].length+startC;
		q.add(start);
		while(!q.isEmpty()) {
			int cur = q.poll();
			int cr = cur/arr[0].length;
			int cc = cur%arr[0].length;
			temp.add(cur);
			for (int[] move:MOVE) {
				int nr = cr+move[0];
				int nc = cc+move[1];
				// 나간 경우 물이 잘 흐른다는 것으로 절대 bottom이 될 수 없음.
				if (nr>=arr.length||nc>=arr[0].length||nr<0||nc<0) {
					while (!q.isEmpty()) {
						cur = q.poll();
						cr = cur/arr[0].length;
						cc = cur%arr[0].length;
					 	if (bottom>arr[cr][cc]) continue;
						notBottom[cr][cc] = true;
					}
					while (!temp.isEmpty()) {
						temp.poll();
					}
					return 0;
				}
				// 만약 자기보다 높은 벽이 나오는 경우 - 벽 최소 높이 업데이트 후 다음 루프
				// 만약 자기보다 낮거나 같은 벽이 나오는 경우 - 진행
				if (visited[nr][nc]) continue;

				if (arr[nr][nc]>arr[cr][cc]) {
					minWall = Math.min(minWall, arr[nr][nc]);
					continue;
				}

				visited[nr][nc] = true;
				q.add(nr*arr[0].length+nc);

			}
		}
		// System.out.println(minWall);
		// System.out.println(temp);
		while(!temp.isEmpty()) {
			int cur = temp.poll();
			int cr = cur/arr[0].length;
			int cc = cur%arr[0].length;
			if (minWall<=arr[cr][cc]) continue;
			sum+=minWall-arr[cr][cc];
			arr[cr][cc] = minWall;
		}
		return sum;
	}
}
