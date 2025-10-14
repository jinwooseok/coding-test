import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2632 {
	/**
	 * A배열 준비
	 * B배열 준비
	 * A누적합 배열(사이즈 2배짜리)
	 * B누적합 배열(사이즈 2배짜리)
	 *
	 * 1. 누적합 배열 생성
	 * 2. A, B를 각각 누적합 슬라이딩을 통해(최대 크기 제한을 통해 사이즈 초과 방지) 경우의 수 찾기 가능
	 * 3. A+B : A 슬라이딩 내부 FOR문을 통해 B슬라이딩
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int minSize = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arrA = new int[N];
		int[] arrB = new int[M];

		int[] sumA = new int[N*2+1];
		int[] sumB = new int[M*2+1];

		for (int i=0;i<N;i++) {
			arrA[i] = Integer.parseInt(br.readLine());
		}
		for (int i=0;i<M;i++) {
			arrB[i] = Integer.parseInt(br.readLine());
		}

		// 누적합 배열 생성
		for (int i=0;i<2*N;i++) {
			sumA[i+1] = sumA[i]+arrA[i%N];
		}
		for (int i=0;i<2*M;i++) {
			sumB[i+1] = sumB[i]+arrB[i%M];
		}
		//System.out.println(Arrays.toString(sumA));
		int MAX_SIZE = 2000001;
		int[] countArrA = new int[MAX_SIZE];
		int[] countArrB = new int[MAX_SIZE];
		// 선택할 조각의 수
		for (int i=1;i<N;i++) {
			// 시작지점
			for (int j=0;j<N;j++) {
				// 선택한 조각의 합은 사이즈 최대 크기인 2000000만의 배열에 +1을 한다.
				countArrA[sumA[j+i]-sumA[j]]+=1;
			}
		}
		countArrA[sumA[N]]+=1;
		for (int i=1;i<M;i++) {
			// 시작지점
			for (int j=0;j<M;j++) {
				// 선택한 조각의 합은 사이즈 최대 크기인 2000000만의 배열에 +1을 한다.
				countArrB[sumB[j+i]-sumB[j]]+=1;
			}
		}
		countArrB[sumB[M]]+=1;
		// System.out.println(Arrays.toString(countArrA));
		// System.out.println(Arrays.toString(countArrB));
		// // 카운트 누적합 배열 생성
		// for (int i=0;i<MAX_SIZE;i++) {
		// 	countSumArrA[i+1] = countSumArrA[i]+countArrA[i];
		// 	countSumArrB[i+1] = countSumArrB[i]+countArrB[i];
		// }
		// System.out.println(Arrays.toString(countSumArrA));
		// System.out.println(Arrays.toString(countSumArrB));
		// A가 0일때부터 최대일때까지
		int count = 0;
		for (int i=0;i<=minSize;i++) {
			int selectedA = countArrA[i];
			int requestB = minSize-i;
			int selectedB = countArrB[requestB];
			if (selectedA == 0 && requestB == minSize) count+=selectedB;
			else if (selectedB == 0 && requestB == 0) count+=selectedA;
			else count+=selectedA*selectedB;
		}
		System.out.println(count);
	}
}
/*

6 3 3
1 1 1 1 1 1
 */