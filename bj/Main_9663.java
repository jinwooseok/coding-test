import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main_9663 {
	/**
	 * 첫번째 row에 어디에 놓을지 정함.
	 * 다음 row에서 선택 가능한 column을 선택, 반복
	 */
	static int[][] arr;
	static int count;
	static int[][] MOVE = {{0,1},{1,1},{-1,1}};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		count = 0;
		arr = new int[N][N];
		for (int i=0;i<N;i++) {
			dfs(N, i,0);
		}
		System.out.println(count);
	}

	public static void dfs(int N, int row, int col) {
		if (col == N-1) {
			//System.out.println("전체성공 :"+ row+ " "+col);
			count++;
			return;
		}
		int nr, nc, cnt;
		for (int[] move:MOVE) {
			cnt = 1;
			nr = row;
			nc = col;
			while (nr >= 0 && nc >= 0 && nr < N && nc < N) {
				arr[nr][nc]+=1;
				nr = row+move[0]*cnt;
				nc = col+move[1]*cnt;
				cnt++;
			}
		}
		// System.out.println("true 처리");
		// for (int i=0;i<N;i++) {
		// 	System.out.println(Arrays.toString(arr[i]));
		// }
		for (int i=0;i<N;i++) {
			if (arr[i][col+1]!=0) {
				//System.out.println("실패 :"+ (i)+ " "+(col+1));
				continue;
			}
			//System.out.println("성공 :"+ (i)+ " "+(col+1));
			dfs(N,i,col+1);
		}

		for (int[] move:MOVE) {
			cnt = 1;
			nr = row;
			nc = col;
			while (nr >= 0 && nc >= 0 && nr < N && nc < N) {
				arr[nr][nc]-=1;
				nr = row+move[0]*cnt;
				nc = col+move[1]*cnt;
				cnt++;
			}
		}
		//System.out.println("false 처리");
		// for (int i=0;i<N;i++) {
		// 	System.out.println(Arrays.toString(arr[i]));
		// }
	}
}
