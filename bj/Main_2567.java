import java.io.*;
import java.util.*;
public class Main_2567 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
	
		int N=Integer.parseInt(br.readLine()); //종이 개수 받기
		boolean[][] arr = new boolean [101][101]; //최대 크기의 배열 생성
		
		/*
		 * x,y값을 받아오고 최대크기 배열에 true false값으로 등록한다.
		 */
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			for (int j=x;j<x+10;j++) {
				for (int k=y;k<y+10;k++) {
					arr[j][k]=true;
				}
			}
		}
		/* 다 등록한 후 색칠된 칸을 기준으로 주변을 탐색해서 경계선일 경우 result에 그만큼 더해준다. 
		 * 색칠된 칸의 경계면은 서로 중복되지 않기 때문에 그냥 더하더라도 중복이 발생하지 않는다는 것을 확인할 수 있었다.
		 */
		int[][] MOVE = {{0,1},{1,0},{-1,0},{0,-1}};
		int result = 0;
		//100*100의 배열을 순회하면서 색종이가 덮인 부분을 탐색함.
		for (int i=0;i<101;i++) {
			for (int j=0;j<101;j++) {
				int cnt = 0;
				if (arr[i][j]) { //색종이가 덮인 부분이라면
					for (int[] move:MOVE) { //주변이 경계면인 경우 길이가 더해진다.
						if (i+move[0]<101 && i+move[0]>=0 && j+move[1]<101 && j+move[1]>=0 && !arr[i+move[0]][j+move[1]]) {
							cnt++;
						}
					}
				}
				result+=cnt;
			}
		}
		System.out.println(result);
	}
}