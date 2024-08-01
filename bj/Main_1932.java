import java.util.*;
import java.io.*;
public class Main_1932 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] arr = new int[N][N];
		for (int i=0;i<N;i++) {
			st=new StringTokenizer(br.readLine());
			for (int j=0;j<i+1;j++){
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i=N-1;i>0;i--) {
			for (int j=0;j<N-1;j++) {
				arr[i-1][j] += Math.max(arr[i][j], arr[i][j+1]);
			}
		}
		System.out.println(arr[0][0]);
	}
}
