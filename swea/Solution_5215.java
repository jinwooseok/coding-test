import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_5215 {

	/*
	 * 가장 선호하는 햄버거를 조합해주는 프로그램
	 * 재료(만족도, 칼로리) 를 조합하기
	 * 선호도 : 맛에 대한 점수의 합의 최대를 구하되 칼로리 제한이 있다.
	 * 1. 부분집합을 활용해 가장 좋은 경우의 수름 탐색한다.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for (int t=1;t<T+1;t++) {
			st = new StringTokenizer(br.readLine().trim());
			int elementCnt = Integer.parseInt(st.nextToken());
			int limitKcal = Integer.parseInt(st.nextToken());
			int[][] arr = new int[elementCnt][2];
			for (int i=0;i<elementCnt;i++) {
				st = new StringTokenizer(br.readLine().trim());
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			int maxCnt = 0;
			for (int i=elementCnt;i>0;i--) {
				 maxCnt = Math.max(maxCnt, combination(arr, 0, 0, i, 0, 0, 0,limitKcal));
			}
			System.out.println("#"+t+" "+maxCnt);
		}
		
	}
	public static int combination(int[][] arr, int startIdx, int depth, int r, int scoreSum, int kcalSum, int maxScore, int limitKcal) {
		if (kcalSum > limitKcal) {
			return maxScore;
		}
		if (depth==r) {
			if (maxScore < scoreSum) {
				maxScore = scoreSum;
			}
			return maxScore;
		} else {
			for (int i=startIdx;i<arr.length;i++) {
				maxScore = combination(arr, i+1, depth+1, r, scoreSum+arr[i][0], kcalSum+arr[i][1], maxScore, limitKcal);
			}
		}
		return maxScore;
	}
	
}