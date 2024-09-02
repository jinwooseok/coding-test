import java.io.*;
import java.util.*;
public class Main_2583 {
	static int N,M,K;
	static int width;
	//왼쪽아래 0,0 오른쪽위 N,M
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		//얼어붙은 영역의 양 끝 꼭짓점 값을 줌. 
		int[][] arr = new int[M][N];
		for (int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int c1=Integer.parseInt(st.nextToken());
			int r1=Integer.parseInt(st.nextToken());
			int c2=Integer.parseInt(st.nextToken());
			int r2=Integer.parseInt(st.nextToken());
			for (int j=r1;j<r2;j++) {
				for (int k=c1;k<c2;k++) {
					arr[j][k] = 1;
				}
			}
		}
		int partitionCnt = 0;
		List<Integer> partitionWidth = new ArrayList<>();
		for (int i=0;i<M;i++) {
			for (int j=0;j<N;j++) {
				if (arr[i][j]==0) {
					width = 0;
					dfs(arr, i, j);
					partitionWidth.add(width);
					partitionCnt++;
				}
			}
		}
		Object[] partitionArr=partitionWidth.stream().sorted().toArray();
		StringBuilder sb = new StringBuilder();
		//System.out.println(partitionWidth);
		sb.append(partitionCnt).append('\n');
		for(Object width:partitionArr) {
			sb.append((Integer) width).append(' ');			
		}
		System.out.println(sb);
	}
	private static void dfs(int[][] arr, int r, int c) {
		if (r<0 || c<0 || r>=arr.length || c>=arr[0].length) return;
		if (arr[r][c]==1) return;
		arr[r][c]=1;
		width++;
		dfs(arr, r+1, c);
		dfs(arr, r, c+1);
		dfs(arr, r-1, c);
		dfs(arr, r, c-1);
		return;
	}

}
