import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_28707 {
	/**
	 * 1. 각 M별 기회비용 계산
	 * 2. 1번 로직 실행 시
	 * 	- 4의 비용
	 * 	4 1 3 2
	 * 	- 제자리를 찾기 위해 각각 4, 9의 비용이 추가됨
	 * 	-
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		Map<Integer, Integer> visited = new HashMap<>();

		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken())-1;
		}

		// target(맞출거, 시작점 찾기)
		int start = 0;
		int target = 0;
		for (int i=0;i<N;i++) {
			start+=(int)Math.pow(10,i)*arr[N-i-1];
		}
		// 정렬
		Arrays.sort(arr);

		// 시작
		for (int i=0;i<N;i++) {
			target+=(int)Math.pow(10,i)*arr[N-i-1];
		}
		int M = Integer.parseInt(br.readLine());
		int[][] module = new int[M][3];
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<3;j++) {
				module[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// tree 형으로 bfs

		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(start, 0));
		// System.out.println(start);
		// System.out.println(target);
		int result = -1;
		while (!q.isEmpty()) {
			Node cur = q.poll();
			//System.out.println(visited);
			if (visited.containsKey(cur.idx)) {
				continue;
			}
			visited.put(cur.idx, cur.weight);
			if (cur.idx==target) {
				result = cur.weight;
				break;
			}
			for (int i=0;i<M;i++) {
				int next = swap(cur.idx, N-module[i][0], N-module[i][1]);
				if (!visited.containsKey(next)) {
					q.add(new Node(next, cur.weight + module[i][2]));
				}
			}
		}

		if (result == -1)
			System.out.println(-1);
		else
			System.out.println(result);
	}

	public static int swap(int num, int idx1, int idx2) {
		//System.out.println(num);
		// System.out.println(idx1);
		// System.out.println(idx2);
		int a=(num%(int)Math.pow(10,idx1+1))/(int)Math.pow(10,idx1);
		// System.out.println(a);
		int b=(num%(int)Math.pow(10,idx2+1))/(int)Math.pow(10,idx2);
		// System.out.println(b);
		num-=a*(int)Math.pow(10, idx1);
		num-=b*(int)Math.pow(10, idx2);
		num+=a*(int)Math.pow(10, idx2);
		num+=b*(int)Math.pow(10, idx1);
		//System.out.println(num);
		return num;
	}

	static class Node implements Comparable<Node> {
		int idx;
		int weight;
		Node(int idx, int weight) {
			this.idx = idx;
			this.weight = weight;
		}
		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
}
/*
8
1 2 3 4 5 6 7 8
10
1 2 3
1 3 3
1 4 3
2 3 3
2 4 1
3 4 1
1 8 10
2 6 10
3 7 8
4 5 5
 */