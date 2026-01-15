import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1029 {
	/*
	1. 그림을 팔 때 산 가격보다 크거나 같은 가격으로 팔아야함
	2. 같은 그림을 두번 이상 사는 것은 불가능

	전제 : 1번 아티스트는 그림을 0원 주고 삼. 그걸 친구들에게 팔려고함
	그림을 소유했던 사람의 수의 최댓값

	한 사람이 두번 사는건 불가능이고..
	INPUT
	- 예술가의 수 N
	- i, j는 j가 i에게서 그림을 살 때의 가격 (0~9)

	설계
	- 가격 상승이 최대한 느려야 많은 사람이 보유 가능
	- 다음 사람이 싸게 사더라도 그 다음 사람은 엄청 비싸게 살수도 있음. 그리디 의미 X
	- 그래프인데..플로이드.
	- 15가 최대이면 조합같은거 써도 되긴 한데..
	- dfs, visit로 그 때의 가격 저장, 1번 이상 구매했는지? 저장
	*/
	static int maxCount;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] visited = new int[N][(int) Math.pow(2, 15)];
		for (int i=0;i<N;i++) {
			Arrays.fill(visited[i], 10);
		}

		int[][] arr = new int[N][N];
		for (int i=0;i<N;i++) {
			String line = br.readLine();
			for (int j=0;j<N;j++) {
				arr[i][j] = line.charAt(j)-'0';
			}
		}
		maxCount = 1;
		dfs(arr, visited, 0, 0, 0, 1);
		System.out.println(maxCount);
	}

	public static void dfs(int[][] arr, int[][] visited, int curIdx, int curBit, int curPrice, int curCount) {
		maxCount = Math.max(maxCount, curCount);
		// System.out.println(curIdx + " " + Integer.toBinaryString(curBit) + " " + curPrice + " " + maxCount);
		if (maxCount == arr.length) return;
		for (int i=0;i<arr.length;i++) {
			// 그 자리에 그 가격으로 도착한적 있는 경우 루프 진행 안함. 그런데 만약에 그게 이전에 방문했던 곳이 다르다면? 한번 더 갈수있다는 차이가 생김. 완전히 같지 않음.
			// 비트 써야할듯
			// 현재 자리로는 팔아봤자 의미없음
			if (curIdx == i) continue;

			// 현재 금액보다 팔려는 곳의 금액이 적은 것은 불가능
			if (curPrice > arr[curIdx][i]) continue;

			// dp 적으로 봤을 때 방문 비트가 같은 경우 더 높은 금액으로 와도 의미없음
			if (visited[i][(curBit | (1<<i))] <= arr[curIdx][i]) continue;

			if ((curBit & (1<<i)) != 0) continue;
			// 기록
			int prev = visited[i][(curBit | (1<<i))];
			visited[i][(curBit | (1<<i))] = arr[curIdx][i];
			// i가 0이라면 처음에 인원 수를 기록하고 시작하므로 더 세지 않음
			if (i == 0) dfs(arr, visited, i, (curBit | (1<<i)), curPrice+arr[curIdx][i], curCount);
			else dfs(arr, visited, i, (curBit | (1<<i)), arr[curIdx][i], curCount+1);
			if (maxCount == arr.length) return;
		}
	}
}
