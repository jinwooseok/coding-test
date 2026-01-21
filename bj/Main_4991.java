import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_4991 {
	/*
	한 번 움직일 때 인접한 칸 이동
	더러운 칸을 모두 깨끗한 칸으로 만드는데 필요한 비용
	. : 깨끗한 칸
	* : 더러운 칸
	x : 가구
	o : 시작 위치

	W, H 제한 : 1~20
	더러운 칸 개수 제한 : 10

	설계
	- 노드 간 이동거리 측정
	- 모든 노드를 방문하는 최소 거리 목표 (왔던 곳 또 갈 수 있지만 갈 필요는 없음)

	1. 노드 간 이동거리 측정
	2. 플로이드로 통합
	3. 순열 DFS
	- 시작 지점은 동일함
	- visit 배열, 방문노드는 비트로 표시

	static 변수
	- minDistance

	visited boolean[nodeNum]
	dp int[nodeNum][bit]
	nodes List<Node>
	Node
	- r
	- c
	- next: List<Integer> (다른 노드 간의 거리)
	 */
	static int[][] MOVES = {{0,1},{1,0},{0,-1},{-1,0}};
	static int minDistance;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while (true) {
			st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			if (W == 0 && H == 0) break;

			char[][] arr = new char[W][H];
			int[][] nodeNum = new int[W][H];
			for (int i=0;i<W;i++) {
				Arrays.fill(nodeNum[i], -1);
			}

			List<Node> nodes = new ArrayList<>();
			minDistance = 100000;
			int startIdx = -1;

			for (int i=0;i<W;i++) {
				String line = br.readLine();
				for (int j=0;j<H;j++) {
					arr[i][j] = line.charAt(j);
					if (arr[i][j] == 'o') {
						startIdx = nodes.size();
						nodeNum[i][j] = nodes.size();
						nodes.add(new Node(i, j));
					}
					if (arr[i][j] == '*') {
						nodeNum[i][j] = nodes.size();
						nodes.add(new Node(i, j));
					}
				}
			}

			// 노드 별 인접 배열 초기화
			int[][] adjArr = new int[nodes.size()][nodes.size()];
			int[][] dp = new int[nodes.size()][1<<10];
			// 최대 거리 채우기 - 본인 방문 못하게 설정
			for (int i=0;i<dp.length;i++) {
				Arrays.fill(adjArr[i], 100000);
				adjArr[i][i] = 0;
			}
			for (int i=0;i<dp.length;i++) {
				Arrays.fill(dp[i], 100000);
				dp[i][i] = 0;
			}

			// 노드 간 거리 측정
			calculate_distance(arr, nodeNum, nodes, adjArr);
			// System.out.println(Integer.toBinaryString((1<<adjArr.length)-1));
			// for (int[] ints : adjArr) {
			// 	System.out.println(Arrays.toString(ints));
			// }
			// 시작 노드에서 dfs 돌리기
			dfs(adjArr, startIdx, 1<<startIdx, dp, 0);
			//System.out.println(minDistance);
			if (minDistance >= 100000) sb.append(-1).append("\n");
			else sb.append(minDistance).append("\n");
		}
		System.out.println(sb);
	}

	static void calculate_distance(char[][] arr, int[][] nodeNum, List<Node> nodes, int[][] adjArr) {
		Queue<int[]> q = new LinkedList<>();
		for (int i=0;i<nodes.size();i++) {
			boolean[][] visited = new boolean[arr.length][arr[0].length];
			q.add(new int[] {nodes.get(i).r, nodes.get(i).c, 0});
			visited[nodes.get(i).r][nodes.get(i).c] = true;

			while (!q.isEmpty()) {
				int[] cur = q.poll();
				if (nodeNum[cur[0]][cur[1]] != -1) {
					adjArr[i][nodeNum[cur[0]][cur[1]]] = cur[2];
				}
				for (int[] move:MOVES) {
					int nr = cur[0]+move[0];
					int nc = cur[1]+move[1];
					if (nr<0 || nc<0 || nr>=arr.length || nc>=arr[0].length || arr[nr][nc] == 'x') continue;
					if (visited[nr][nc]) continue;
					visited[nr][nc] = true;
					q.add(new int[] {nr, nc, cur[2]+1});
				}
			}
		}
		// for (int[] ints : adjArr) {
		// 	System.out.println(Arrays.toString(ints));
		// }
		for (int i=0;i<adjArr.length;i++) {
			for (int j=0;j<adjArr.length;j++) {
				for (int k=0;k<adjArr.length;k++) {
					adjArr[j][k] = Math.min(adjArr[j][k],adjArr[j][i]+adjArr[i][k]);
				}
			}
		}
		// for (int[] ints : adjArr) {
		// 	System.out.println(Arrays.toString(ints));
		// }
	}

	static void dfs(int[][] adjArr, int curIdx, int visited, int[][] dp, int distance) {
		// 끊기
		// System.out.println(Integer.toBinaryString((1<<adjArr.length)-1));
		if (visited == (1<<adjArr.length)-1) {
			//System.out.println(Integer.toBinaryString((visited)));
			minDistance = Math.min(minDistance, distance);
		}
		for (int nextIdx=0;nextIdx<adjArr.length;nextIdx++) {
			// visited 체크
			//System.out.println(Integer.toBinaryString(visited)+ " "+Integer.toBinaryString((1<<nextIdx)));
			if ((visited & (1<<nextIdx)) != 0) continue;
			if (dp[nextIdx][visited | (1<<nextIdx)] < distance+adjArr[curIdx][nextIdx]) continue;
			dp[nextIdx][visited | (1<<nextIdx)] = distance+adjArr[curIdx][nextIdx];
			dfs(adjArr, nextIdx, visited | (1<<nextIdx), dp, distance+adjArr[curIdx][nextIdx]);
		}
	}

	static class Node {
		int r;
		int c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
/*

10 10
..........
..o.......
..........
..........
...*.*....
.....xxxxx
.....x....
.....x....
.....x....
.....x....
0 0
 */