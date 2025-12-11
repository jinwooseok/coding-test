import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main_2667 {
	static int totalCount;
	static int[][] MOVE = {{0,1},{1,0},{0,-1},{-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		totalCount = 0; // 마지막에 총 단지 수가 되도록
		int[][] arr = new int[N][N];
		List<Integer> houseCounts = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<N;i++) {
			String st = br.readLine();
			for (int j=0;j<N;j++) {
				arr[i][j] = st.charAt(j)-'0';
			}
		}
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				if (arr[i][j] != 1) continue;
				totalCount++;
				houseCounts.add(dfs(arr, i, j));
			}
		}

		sb.append(totalCount).append("\n");
		Collections.sort(houseCounts);
		for (int cnt:houseCounts) {
			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}

	public static int dfs(int[][] arr, int r, int c) {
		int count = 1;
		arr[r][c] = 0;

		for (int[] move:MOVE) {
			int nr = r+move[0];
			int nc = c+move[1];
			if (nr<0||nc<0||nr>=arr.length||nc>= arr.length) continue;
			if (arr[nr][nc] == 0) continue;
			count += dfs(arr, nr, nc);
		}
		return count;
	}
}
