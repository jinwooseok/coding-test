import java.io.*;
import java.util.StringTokenizer;
public class Main_2961 {
	/*
	 * 신맛 : 신맛의 곱
	 * 쓴맛 : 쓴맛의 합
	 * 목표 : 신맛과 쓴맛의 차이를 줄이기
	 * + 최소 한개의 재료를 사용해야함. 최소한의 신맛과 쓴맛을 내는 요리를 만들기 -> 부분집합 문제이긴 핟.
	 * 입력값
	 * 1. 재료의 개수 N
	 * 2. N개 줄에 신맛과 쓴맛이 공백으로 구분된다.
	 * 
	 * 설계
	 * - 신맛과 쓴맛의 자료들을 배열에 넣는다. SBArr[0]:신맛, SBArr[1]:쓴맛
	 * - 인덱스를 통해 탐색을 한다.
	 * - 매 단계마다 최소값을 연산한다.
	 * - 최소값을 return
	 * 제약조건 : index가 최대길이를 초과할때
	 * 
	 * subset(int[][] SBArr, int startIndex,int curSB, int minSB) 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] SBArr = new int[N][2];
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			SBArr[i][0] = Integer.parseInt(st.nextToken());
			SBArr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(subset(SBArr, 0, 1, 0));
	}
	
	//dp쓸수도 있을듯
	private static int subset(int[][] SBArr, int startIndex, int curS, int curB) {
		if (startIndex>=SBArr.length) {
			if (curS==1 && curB==0) {
				return Integer.MAX_VALUE;
			} else {
				return Math.abs(curS-curB);				
			}
		}
		int a=subset(SBArr, startIndex+1, curS*SBArr[startIndex][0], curB+SBArr[startIndex][1]);
		int b=subset(SBArr, startIndex+1, curS, curB);
		return Math.min(a, b);
	}

}