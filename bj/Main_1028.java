import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1028 {
	/*
	dfs, 첫번째 자리부터 시작. 넓은 것부터 작은것까지 차례대로 검사.
	이전 과정에서 더 넓은게 됐다면 그 아래는 볼 필요 없어짐.
	길이는 2~400
	한번에 8+...+1600
	1600*800 = 1440000
	1440000*10000
	어느 점에서 시작해서 길이가 2~..인 +인 선이 있는지 체크
	반대로 -인 방향으로 선이 있는지 체크
	[r][c][방향:0->왼쪽,1->오른쪽][길이:l]:true, false
	r,c를 정해놓고, 사방향을 체크해야 하는데
	[r][c][0][i] [r][c][1][i] 가 동시에 true인지 체크,
	true라면
	[r+i][c-i][1][i] [r+i][c+i][0][i] 가 존재하는지 체크
	5600000
	메모리는 최대 10000000
	정합성 검사
	2,2일 때,
	i가 3인게 있음.
	하지만 인덱스는 2만큼 크고 작음.
	굳

	설계
	1. 각 점에서 시작해서 좌우 아래방향으로 파져갔을 때 어디까지 존재하는지 체크
	최대는 넉넉하게 400*2번
	800번을 매번 반복 -> 700*700*800 = 49000000
	애매함..
	퍼져가면서 다른 행렬들의 검사도 동시에 해준다면..?
	그건.. 가능은 할듯함.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[][] arr = new int[R][C];
		int[][][] lines = new int[R][C][2];
		boolean one = false;
		for (int i=0;i<R;i++) {
			String line = br.readLine();
			for (int j=0;j<C;j++) {
				arr[i][j] = line.charAt(j)-'0';
				if (arr[i][j] == 1) one=true;
			}
		}
		for (int i=0;i<R;i++) {
			for (int j=0;j<C;j++) {
				if (arr[i][j] == 0) continue;
				boolean rCompleted = false;
				boolean lCompleted = false;
				int l = 1;
				int r = 1;
				while (!(rCompleted && lCompleted)) {
					if (!rCompleted) {
						if (i + r < R && j + r < C && arr[i + r][j + r] != 0) {
							r++;
						} else {
							lines[i][j][1] = r-1;
							rCompleted = true;
						}
					}
					if (!lCompleted) {
						if (j-l<0 || i+l>=R || arr[i+l][j-l] == 0) {
							lines[i][j][0] = l-1;
							lCompleted = true;
						} else {
							l++;
						}
					}
				}
				// System.out.println(i+" "+j);
				// System.out.println(lines[i][j][0]);
				// System.out.println(lines[i][j][1]);
			}
		}
		// 각각의 최대길이를 파악.
		int maxLength = 0;
		int maxCandidate;
		for (int i=0;i<R;i++) {
			for (int j=0;j<C;j++) {
				maxCandidate=Math.min(lines[i][j][0], lines[i][j][1]);
				if (maxLength>=maxCandidate) continue;
				for (int k=maxCandidate;k>0;k--) {
					if (j-k<0 || i+k>=R || j+k>=C) continue;
					if (maxLength >= k) break;
					if (lines[i + k][j - k][1] >= k && lines[i + k][j + k][0] >= k) {
						maxLength = k;
						break;
					}
				}
			}
		}
		if (!one)
			System.out.println(0);
		else
			System.out.println(maxLength+1);
	}
}
