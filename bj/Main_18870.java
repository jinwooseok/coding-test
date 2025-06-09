import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_18870 {
	/**
	 * N: 좌표의 수
	 * Xn : 각 좌표
	 * X'n : 압축된 결과
	 * 압축 결과는 Xi보다 작은 Xj의 개수와 같음
	 * 1000000개이므로 최대 nlogn
	 * 1. 정렬 후 인덱스로 추출. 단 중복되는 수에 대해서는 체크가 어려울 듯 함 set 작업 후 sort하면 해결될 듯
	 * 	- treemap 변환 - 숫자 정렬 오름차순
	 * 	- 이후 value에 순차적으로 인덱스 저장
	 * 	- 인덱스를 더 작은 수를 세는 것에 사용
	 *
	 * 1번 방식 활용
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringBuilder sb = new StringBuilder();
		Map<Integer, Integer> sorted = new TreeMap<>();
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			sorted.put(arr[i], 0);
		}
		int idx = 0;

		for (int key:sorted.keySet()) {
			sorted.put(key, idx++);
		}

		for (int i=0;i<N;i++) {
			sb.append(sorted.get(arr[i])).append(" ");
		}

		System.out.println(sb);
	}
}
