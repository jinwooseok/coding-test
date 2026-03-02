import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_10164 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int point = Integer.parseInt(st.nextToken())-1;
		int pointR = point/c;
		int pointC = point%c;
		// System.out.println(pointR+" "+pointC);
		// 2*3일때 경로의 수 -> 3,
		// 1*3일때 경로의 수 -> 1
		// 3*3일때 경로의 수 -> 6
		//
		// point == 0이라면 그냥 처음에서 끝까지 가는 경로의 수
		// point != 0이라면 start->point->end로 가는 경로의 수를 구함.
		int[][] arr = new int[r][c];
		arr[0][0] = 1;
		if (point == -1) {
			for (int i=0;i<r;i++) {
				for (int j=0;j<c;j++) {
					if (i==0 && j==0) continue;
					else if (j==0) arr[i][j] = arr[i-1][j];
					else if (i==0) arr[i][j] = arr[i][j-1];
					else arr[i][j]=arr[i-1][j]+arr[i][j-1];
				}
			}
		} else {
			for (int i=0;i<=pointR;i++) {
				for (int j=0;j<=pointC;j++) {
					if (i==0 && j==0) continue;
					else if (j==0) arr[i][j] = arr[i-1][j];
					else if (i==0) arr[i][j] = arr[i][j-1];
					else arr[i][j]=arr[i-1][j]+arr[i][j-1];
				}
			}
			for (int i=pointR;i<r;i++) {
				for (int j=pointC;j<c;j++) {
					if (i==0 && j==0) continue;
					else if (j==0) arr[i][j] = arr[i-1][j];
					else if (i==0) arr[i][j] = arr[i][j-1];
					else arr[i][j]=arr[i-1][j]+arr[i][j-1];
				}
			}
		}
		// for (int i=0;i<r;i++) {
		// 	System.out.println(Arrays.toString(arr[i]));
		// }
		System.out.println(arr[r-1][c-1]);
	}
}
