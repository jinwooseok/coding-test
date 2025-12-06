import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_18111 {
	/**
	 *  1. 블록을 제거하고 인벤토리에 넣는다 (2초 걸림)
	 *  2. 블록을 꺼내어 블록위에 놓는다 (1초 걸림)
	 *  N, M, B : 땅의 N, M 그리고 초기 블록의 개수
	 * 땅의 높이는 최대 256보다 작거나 같다.
	 * 1. 시작의 높이를 MAP에 저장
	 * 2. 가장 낮은 높이와 가장 높은 높이를 기준으로 각 높이를 만드는데 드는 최소 시간 측정
	 * - 0을 만들기 위해 1을 팜.
	 * - 1을 만드는 방향으로 2번을 전부 진행. 모은 것을 전부 1에 투자.
	 * 250*25000 = 17500000 정도
	 *
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long B = Integer.parseInt(st.nextToken());
		int minHeight = 256;
		int maxHeight = 0;
		int[][] arr = new int[N][M];
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				//System.out.println(arr[i][j]);
				minHeight = Math.min(minHeight, arr[i][j]);
				maxHeight = Math.max(maxHeight, arr[i][j]);
			}
		}
		//System.out.println(minHeight+" "+maxHeight);
		long minTime = Long.MAX_VALUE;
		int targetHeight = 0;

		for (int i=minHeight;i<=maxHeight;i++) {
			long spendTime = 0;
			long curBlock = B;
			long diff;
			boolean possible = true;
			// 1. 블록을 파내고 인벤토리에 담는 작업(2초). i를 목표로 하고, i를 뺐을 때 0이하라면 진행 안함.
			for (int j=0;j<N;j++) {
				for (int k = 0; k < M; k++) {

					// 시간 최적화
					if (spendTime > minTime) {
						possible = false;
						break;
					}

					if (arr[j][k] - i <= 0)
						continue;

					diff = arr[j][k] - i;
					// System.out.println("차이:"+diff);
					spendTime += diff * 2;
					curBlock += diff;
				}
				if (!possible) break;
			}
			// 불가능
			if (!possible) continue;

			for (int j=0;j<N;j++) {
				for (int k = 0; k < M; k++) {
					// 시간 최적화
					if (spendTime > minTime) {
						possible = false;
						break;
					}

					// 2. 블록을 쌓는 작업(2초). i를 목표로 하고, i에서 뺐을 때 0이하면 진행 x, 인벤토리에 블럭 부족 시 진행 x
					if (i - arr[j][k] <= 0)
						continue;
					// System.out.println("블록 쌓을거:" +(i - arr[j][k]));
					// 블록 개수 부족 및 길이 오류로 플래그 설정
					if (i - arr[j][k] > curBlock) {
						possible = false;
						break;
					}
					diff = i - arr[j][k];
					spendTime += diff;
					curBlock -= diff;
				}
				if (!possible) break;
			}

			// 다 쌓는데 성공했다면 possible은 반드시 true
			// System.out.println(spendTime);
			// System.out.println(i);
			// System.out.println(possible);

			// 불가능
			// 불가능
			if (!possible) continue;
			// System.out.println("소모된 시간:" +spendTime);
			//System.out.println(spendTime);
			if (spendTime <= minTime) {
				minTime = spendTime;
				targetHeight = i;
			}
 		}
		System.out.println(minTime+" "+targetHeight);
	}
}
