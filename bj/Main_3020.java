import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_3020 {
	/*
	특정 높이보다 낮은 석순의 개수를 각각 구함
	부분합 처리 -> 이 구간으로 가면 이만큼의 석순을 맞는다.
	1. 각 석순의 높이를 저장
	2. 하나하나 저장하려면 개빡세므로 각각 저장 후 누적합으로 처리
	부분합으로 저장하게 되면 특정 구간을 갔을 때, 몇개의 석순을 파괴하는지 파악 가능
	종유석은 반대에 위치. 동일하게 한 후,
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int[] under = new int[H];
		int[] underSum = new int[H+1];
		int[] overSum = new int[H+1];
		int[] over = new int[H];
		for (int i=0;i<N;i++) {
			if (i % 2 == 0) {
				int length = Integer.parseInt(br.readLine());
				under[length]++;
			} else {
				int length = Integer.parseInt(br.readLine());
				over[length]++;
			}
		}

		for (int i=0;i<H;i++) {
			underSum[i+1] = underSum[i]+under[i];
			overSum[i+1] = overSum[i]+over[i];
		}
		// System.out.println(Arrays.toString(underSum));
		int minCount = 200001;
		int[] result = new int[H+1];
		for (int i=1;i<H+1;i++) {
			int overCnt = 0;
			int underCnt = 0;
			underCnt += underSum[underSum.length-1]-underSum[i];
			// System.out.println(i+" "+cnt);
			overCnt += overSum[overSum.length-1]-overSum[H-i+1];
			// System.out.println("종유석 "+(H-i+1)+" "+overCnt + " 석순 " + (i-1) + " " + underCnt);
			result[i] = underCnt+overCnt;
			minCount = Math.min(minCount, result[i]);
		}

		int totalCnt = 0;
		for (int i=1;i<H+1;i++) {
			if (result[i] == minCount) {
				totalCnt++;
			}
		}
		System.out.println(minCount+" "+totalCnt);
	}
}
