import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1326 {
	/*
	도착 지점에서 시작하여 도달할 수 있는 위치를 찾는다.
	그 위치를 방문 처리
	그리고 그 위치에서 다시 그 위치로 도달할 수 있는 위치를 찾는다.
	방문한 위치가 시작지점인 경우 점프를 멈춘다.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Queue<Integer> remains = new ArrayDeque<>();
		Queue<Integer> next = new ArrayDeque<>();
		int N = Integer.parseInt(br.readLine());
		boolean[] visited = new boolean[N+1];
		int[] arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1;i<=N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		for (int i=1;i<=N;i++) {
			if (i == end) {
				next.add(end);
			} else {
				remains.add(i);
			}
		}

		visited[end] = true;
		int turn = 0;
		boolean flag = false;
		while (!next.isEmpty()) {
			if (flag) break;
			turn++;
			int nCnt = next.size();
			for (int j=0;j<nCnt;j++) {
				if (flag) break;
				int n = next.poll();
				int cnt = remains.size();
				for (int i=0;i<cnt;i++) {
					int m = remains.poll();
					if (visited[m]) continue;
					if ((Math.abs(n-m) >= arr[m]) && (Math.abs(n-m)%arr[m]==0)) {
						if (start == m) {
							flag = true;
							break;
						}
						visited[m] = true;
						next.add(m);
					} else {
						remains.add(m);
					}
				}
			}
		}
		if (flag)
			System.out.println(turn);
		else
			System.out.println(-1);
	}
}
