import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_21736 {
	static int[][] MOVE = {{0,1},{1,0},{0,-1},{-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		char[][] arr = new char[N][M];
		int[] startPoint = new int[2];
		for (int i=0;i<N;i++) {
			String str = br.readLine();
			for (int j=0;j<M;j++) {
				arr[i][j] = str.charAt(j);
				if (arr[i][j] == 'I') {
					startPoint[0] = i;
					startPoint[1] = j;
				}
			}
		}
		int result = dfs(arr, startPoint[0],startPoint[1]);
		if (result == 0) System.out.println("TT");
		else System.out.println(result);
	}
	public static int dfs(char[][] arr, int r, int c) {
		int count = 0;
		arr[r][c] = 'X';
		for (int[] move:MOVE) {
			int nr = r+move[0];
			int nc = c+move[1];
			if (nr<0||nc<0||nr>=arr.length||nc>=arr[0].length) continue;
			if (arr[nr][nc] == 'X') continue;
			if (arr[nr][nc] == 'P') count++;
			count += dfs(arr, nr, nc);
		}
		return count;
	}
}
