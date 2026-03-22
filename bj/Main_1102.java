import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1102 {
	/*
	고장나지 않은 발전소 -> 고장난 발전소 재시작
	가중치에 따라 달라짐.
	적어도 P개의 발전소가 고장나지 않아야 함.
	방향이 있는 그래프임
	현재 상태가 기준이 아니라 1번으로 2번 키고 -> 2번으로 3번 키고 이런 식도 가능함
	P는 최대 36 (고쳐야할 발전소는 최대 36개)
	N은 최대 16. 비트마스킹 가능, 조합 가능
	dfs가 바로 생각나긴 함
	dfs와 비트마스킹?
	해당 비트를 방문한 적이 있으면 더 진행하지 않는다.

	 */
	static int minPrice;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[][] adjArr = new int[N][N];
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<N;j++) {
				adjArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		String line = br.readLine();
		int curVisited = 0;
		int curCnt = 0;
		for (int i=0;i<line.length();i++) {
			if (line.charAt(i) == 'Y') {
				curVisited |= (1<<i);
				curCnt++;
			}
		}
		int[][] visited = new int[16][2<<15];
		for (int i=0;i<16;i++) {
			Arrays.fill(visited[i],100000);
		}
		int targetCnt = Integer.parseInt(br.readLine());
		minPrice = 100000;
		for (int i=0;i<N;i++) {
			// System.out.println(curVisited);
			// System.out.println(1<<i);
			// System.out.println((curVisited & (1<<i)));
			if ((curVisited & (1<<i)) != 0)  {
				visited[i][curVisited] = 0;
				dfs(adjArr, visited, curVisited, i, 0, curCnt, targetCnt);
			}
		}
		if (targetCnt == 0)
			System.out.println(0);
		else if (minPrice == 100000)
			System.out.println(-1);
		else
			System.out.println(minPrice);
	}

	static void dfs(int[][] adjArr, int[][] visited, int curVisited, int idx, int curPrice, int curCnt, int targetCnt) {
		// System.out.println(curVisited);
		if (curCnt >= targetCnt) {
			minPrice = Math.min(minPrice, curPrice);
			return;
		}
		for (int i=0;i<adjArr.length;i++) {
			// 동일 발전소라면 continue
			if (idx == i) continue;
			if ((curVisited & (1<<i)) != 0) {
				if (visited[i][curVisited]<=curPrice) continue;
				visited[i][curVisited] = curPrice;
				dfs(adjArr,visited,curVisited, i, curPrice, curCnt, targetCnt);
			}
			else {
				if (visited[i][(curVisited | (1<<i))]<=curPrice+adjArr[idx][i]) continue;
				visited[i][(curVisited | (1<<i))] = curPrice+adjArr[idx][i];
				dfs(adjArr,visited,curVisited | (1<<i), i,curPrice+adjArr[idx][i], curCnt+1, targetCnt);
			}
		}
	}
}
