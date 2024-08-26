import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_1861 {
	/*
	 * 핵심: 1이 3이 됐다면 2는 무조건 2, 3은 무조건 1이다.
	 * 이를 활용해 한번 연결됐을 때 그 사이에 있는 수들의 연결 횟수도 업데이트 해준 후, 순회 도중 그 수가 나오면 건너뛸 수 있음
	 * 즉, 1의 순회 도중 1,2,3의 연결을 확인했다면 1,2,3의 연결횟수 업데이트 후, 그 이후의 순회에서 2,3은 연결횟수를 알아보지 않아도됨
	 * 그러므로 1,2,3을 순회할 시간을 1을 순회하는 시간으로 아낄 수 있음 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t=1;t<T+1;t++) {
			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			for (int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0;j<N;j++) {				
					arr[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			int[] cntArr = traversal(arr);
			int maxCnt = 0;
			int maxCntNum = 0;
			for (int i=0;i<cntArr.length;i++) {
				if (cntArr[i] > maxCnt) {
					maxCnt = cntArr[i];
					maxCntNum = i;
				}
			}
			//System.out.println(Arrays.toString(cntArr));
			System.out.println("#"+t+" "+(maxCntNum+1)+" "+maxCnt);
		}
	}
	private static int[] traversal(int[][] arr) {
		//cntArr는 각 숫자마다 연결된 것의 개수를 저장할 배열입니다.
		int[] cntArr=new int[arr.length*arr.length];
		int[][] MOVE = {{-1,0},{0,-1},{1,0},{0,1}};
		for (int i=0;i<arr.length;i++) {
			
			for (int j=0;j<arr.length;j++) {
				//cntArr가 0이면 실행. 아니면 건너뛰어도 될듯
				if (cntArr[arr[i][j]-1] != 0) {
					continue;
				}
				// 0이면 그 숫자 주변을 순회하면서 어디까지 이어지는지 확인. 그 이후에 어디서부터 어디까지 이어지는지를 확인
				int row=i; int col=j;
				int curNum = arr[row][col];
				//주변으로 퍼질수없으면 break
				while (true) {
					boolean flag = false;
					for (int[] move:MOVE) {
						if (row+move[0]<0 || col+move[1]<0 || row+move[0]>=arr.length || col+move[1]>=arr.length) {
							continue;
						}
						if (arr[row][col]+1==arr[row+move[0]][col+move[1]]) {
							row+=move[0];
							col+=move[1];
							flag = true;
							break;
						}
					}
					if (!flag) {
						break;
					}
				}
				
				int lastNum = arr[row][col];
				//개수배열 저장. 현재숫자에서 마지막숫자까지가 현재숫자와 연결된 수의 총 개수라면 그것보다 큰 숫자들의 연결개수는 1씩 줄어듭니다.
				//예 1~3까지 연결됐을 때, cntArr = {3,2,1}, 1~2일때 {2,1,0}
				for (int k=curNum;k<lastNum+1;k++) {
					cntArr[k-1] = lastNum-k+1;
				}
			}
		}
		
		return cntArr;
	}

}
