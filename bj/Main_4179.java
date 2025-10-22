import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_4179 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] arr = new char[R][C];
		List<int[]> fireStartPoint = new ArrayList<>();
		int[] jStartPoint = new int[2];
		for (int i=0;i<R;i++) {
			String line = br.readLine();
			for (int j=0;j<C;j++) {
				arr[i][j] = line.charAt(j);
				if (arr[i][j]=='J') {
					jStartPoint[0] = i;
					jStartPoint[1] = j;
				} else if (arr[i][j] == 'F') {
					fireStartPoint.add(new int[]{i,j});
				}
			}
		}
 		Queue<int[]> q = new LinkedList<>();
		int[][] MOVE = {{0,1},{1,0},{0,-1},{-1,0}};
		q.add(new int[]{0,jStartPoint[0],jStartPoint[1],0});
		for (int[] array:fireStartPoint) {
			q.add(new int[]{1,array[0],array[1],0});
		}
		int result = -1;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			//if (cur[0]==0) System.out.println(Arrays.toString(cur));
			if (cur[0] == 0 && arr[cur[1]][cur[2]]=='F') continue;
			for (int[] move:MOVE) {
				int nr = cur[1]+move[0];
				int nc = cur[2]+move[1];
				boolean b = nr < 0 || nc < 0 || nr >= R || nc >= C;
				if (cur[0]==0 && b) {
					result = cur[3]+1;
					q.clear();
					break;
				}
				if (cur[0]==1 && b) continue;
				if (arr[nr][nc]=='#') continue;
				if (cur[0] == 0 && arr[nr][nc] != 'F' && arr[nr][nc] != 'J') {
					arr[nr][nc] = 'J';
					q.add(new int[]{0,nr,nc,cur[3]+1});
				} else if (cur[0] == 1 && arr[nr][nc] != 'F') {
					arr[nr][nc] = 'F';
					q.add(new int[]{1,nr,nc,cur[3]+1});
				}
			}
				
		}
		if (result != -1) {
			System.out.println(result);
		} else {
			System.out.println("IMPOSSIBLE");
		}
	}
}
