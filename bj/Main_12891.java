import java.io.*;
import java.util.*;
public class Main_12891 {
	/*
	 * ACGT인 문자열 :DNA문자열
	 * 제약
	 * - 부분문자열에서 등장하는 문자의 개수가 특정 개수 이상이여야 비밀번호로 활용이 가능함
	 * 이때 만들 수 있는 비밀번호의 종류의 수를 구하여라
	 * 
	 * 입력값
	 * 1. 임의의 문자열의 길이 (S), 부분문자열의 길이 (P)
	 * 2. A, C, G, T의 최소 개수
	 * 
	 * 설계
	 * 1. 입력값을 받는다.
	 * 2. map에 값을 하나씩 밀어 넣는다.
	 * 3. p의 길이만큼 들어왔을 때 슬라이딩 로직을 시작한다.
	 * 4. 첫번째 문자를 맵에서 빼주고 마지막 문자를 맵에서 더한다.
	 * 5. 이 과정중에 매번 검사를 수행한다. 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine().trim());
		//s p
		int strLength = Integer.parseInt(st.nextToken());
		int subsetLength = Integer.parseInt(st.nextToken());
		
		//문자열 받기
		String[] dnaArray = br.readLine().trim().split("");
		
		//제약조건 받기 0: A 1:C 2:G 3:T
		int[] restricts = new int[4];
		st = new StringTokenizer(br.readLine().trim());
		for (int i=0;i<4;i++) {
			restricts[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dnaCntArray = new int[4];
		//초기값 입력
		for (int i=0;i<subsetLength;i++) {
			insertCntArray(dnaCntArray, dnaArray[i]);
		}
		
		//슬라이딩 시작 전 점검
		int sum = 0;
		if (isPass(dnaCntArray, restricts)) sum++;
		//시작~
		for (int i=subsetLength;i<strLength;i++) {
			//이전것 빼기
			deleteCntArray(dnaCntArray, dnaArray[i-subsetLength]);
			//다음것 더하기
			insertCntArray(dnaCntArray, dnaArray[i]);
			//검사
			if (isPass(dnaCntArray, restricts)) sum++;
		}
		System.out.println(sum);
	}
	
	private static boolean isPass(int[] dnaCntArray, int[] restricts) {
		for (int i=0;i<4;i++) {
			if (dnaCntArray[i]<restricts[i]) return false;
		}
		return true;
	}
	
	private static void insertCntArray(int[] dnaCntArray, String dna) {
		if (dna.equals("A")) dnaCntArray[0]+=1;
		else if (dna.equals("C")) dnaCntArray[1]+=1;
		else if (dna.equals("G")) dnaCntArray[2]+=1;
		else if (dna.equals("T")) dnaCntArray[3]+=1;
	}
	private static void deleteCntArray(int[] dnaCntArray, String dna) {
		if (dna.equals("A")) dnaCntArray[0]-=1;
		else if (dna.equals("C")) dnaCntArray[1]-=1;
		else if (dna.equals("G")) dnaCntArray[2]-=1;
		else if (dna.equals("T")) dnaCntArray[3]-=1;
	}

}

