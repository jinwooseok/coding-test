import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2887 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Node[][] arr = new Node[3][N];
		StringTokenizer st;
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0;j<3;j++) {
				arr[j][i] = new Node(i, Integer.parseInt(st.nextToken()));
			}
		}
		PriorityQueue<Edge> edges = new PriorityQueue<>();
		for (int j=0;j<3;j++) {
			Arrays.sort(arr[j]);
			for (int i=0;i<N-1;i++) {
				edges.add(new Edge(arr[j][i].idx, arr[j][i+1].idx, Math.abs(arr[j][i].x-arr[j][i+1].x)));
			}
		}
		int[] parents = new int[N];
		for (int i=0;i<N;i++) {
			parents[i] = i;
		}
		int dist = 0;
		int edgeCnt = 0;
//		boolean[] visited = new boolean[N];
		while (!edges.isEmpty() && edgeCnt<N-1) {
			Edge edge = edges.poll();
			if (!checkUnion(parents, edge.start, edge.end)) {
				union(parents, edge.start, edge.end);
				dist+=edge.dist;
				edgeCnt+=1;
			}
		}
		System.out.println(dist);
	}
	static boolean checkUnion(int[] parents, int a, int b) {
		int parentA = find(parents, a);
		int parentB = find(parents, b);
		
		if (parentA != parentB) {
			return false;
		}
		return true;
	}
	static void union(int[] parents, int a, int b) {
		int parentA = find(parents, a);
		int parentB = find(parents, b);
		parents[parentA] = parentB;
	}
	static int find(int[] parents, int x) {
		if (parents[x] == x) {
			return parents[x];
		} else {
			return parents[x] = find(parents, parents[x]);
		}
	}
	
	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		int dist;
		
		Edge(int start, int end, int dist) {
			this.start = start;
			this.end = end;
			this.dist = dist;
		}
		
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.dist-o.dist;
		}
		
	}
	
	static class Node implements Comparable<Node> {
		int idx;
		int x;
		Node (int idx, int x) {
			this.idx = idx;
			this.x = x;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.x-o.x;
		}
	}
}
