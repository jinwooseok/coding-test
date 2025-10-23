import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_14466 {
	static int[] parents;
	static int[][] MOVE = {{0,1},{1,0},{0,-1},{-1,0}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][N];
		Map<Integer, Set<Integer>> lines = new HashMap<>();

		int[][] cows = new int[K][2];
		parents = new int[N*N];
		for (int i=0;i<R;i++) {
			st = new StringTokenizer(br.readLine());
			int r1 = Integer.parseInt(st.nextToken())-1;
			int c1 = Integer.parseInt(st.nextToken())-1;
			int r2 = Integer.parseInt(st.nextToken())-1;
			int c2 = Integer.parseInt(st.nextToken())-1;

			lines.putIfAbsent(r1*N+c1, new HashSet<>());
			lines.putIfAbsent(r2*N+c2, new HashSet<>());
			lines.get(r1*N+c1).add(r2*N+c2);
			lines.get(r2*N+c2).add(r1*N+c1);
		}
		int subsetId = 1;
		for (int i=0;i<N;i++) {
			for (int j=0;j<N;j++) {
				if (arr[i][j]!=0) continue;
				// System.out.println(subsetId);
				dfs(arr, lines, i, j, subsetId++);
			}
		}

		for (int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			cows[i][0]=r;
			cows[i][1]=c;
		}

		int result = 0;
		for (int i=0;i<K;i++) {
			for (int j=i+1;j<K;j++) {
				if (arr[cows[i][0]][cows[i][1]]!=arr[cows[j][0]][cows[j][1]]) result++;
			}
		}
		System.out.println(result);
		// for (int i=0;i<N;i++) {
		// 	System.out.println(Arrays.toString(arr[i]));
		// }
	}

	public static void dfs(int[][] arr, Map<Integer, Set<Integer>> lines, int r, int c, int subset) {
		arr[r][c] = subset;
		for (int[] move:MOVE) {
			int nr = move[0]+r;
			int nc = move[1]+c;
			if (nr<0||nc<0||nr>=arr.length||nc>=arr.length) continue;
			if (arr[nr][nc]==subset) continue;
			if (lines.containsKey(r*arr.length+c) && lines.get(r*arr.length+c).contains(nr*arr.length+nc)) continue;
			dfs(arr, lines, nr, nc, subset);
		}
	}
	//
	// public static int find(int x) {
	// 	if (x == parents[x]) return x;
	// 	return parents[x] = find(parents[x]);
	// }
	//
	// public static void union(int a, int b) {
	//
	// }
}
