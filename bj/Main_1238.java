import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1238 {
	/**
	 * N명의 학생이 X번째 마을에 모임
	 * 총 M개의 단방향 도로들이 있음
	 * 도로를 지날 때 T의 시간 소비
	 * 단방향이고 X마을로 오는데 가장 많은 시간을 소비하는 학생은 누구일까
	 * 각각 다익스트라하면?
	 * X마을에서 다익스트라로 마지막에 도착한 사람 뽑기 -> 단방향이기 때문에 결과가 다름
	 * X-> 집 다익으로 계산하기
	 * 집 -> X..
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		Nodes[] roots = new Nodes[N+1];
		Nodes[] reversedRoots = new Nodes[N+1];
		for (int i=0;i<N+1;i++) {
			roots[i] = new Nodes();
			reversedRoots[i] = new Nodes();
		}
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			roots[start].list.add(new Node(end, weight));
			reversedRoots[end].list.add(new Node(start, weight));
		}

		PriorityQueue<Node> q1 = new PriorityQueue<>();
		q1.add(new Node(X,0));
		int[] visited1 = new int[N+1];
		Arrays.fill(visited1, Integer.MAX_VALUE);
		visited1[X] = 0;
		while (!q1.isEmpty()) {
			Node current = q1.poll();
			for (Node node:roots[current.next].list) {
				if (visited1[node.next]>=current.weight+node.weight) {
					q1.add(new Node(node.next,current.weight+node.weight));
					visited1[node.next] = current.weight+node.weight;
				}
			}
		}
		//System.out.println(Arrays.toString(visited1));
		PriorityQueue<Node> q2 = new PriorityQueue<>();
		q2.add(new Node(X,0));
		int[] visited2 = new int[N+1];
		Arrays.fill(visited2, Integer.MAX_VALUE);
		visited2[X] = 0;
		while (!q2.isEmpty()) {
			Node current = q2.poll();
			for (Node node:reversedRoots[current.next].list) {
				if (visited2[node.next]>=current.weight+node.weight) {
					q2.add(new Node(node.next,current.weight+node.weight));
					visited2[node.next] = current.weight+node.weight;
				}
			}
		}
		int max = 0;
		for (int i=1;i<N+1;i++) {
			max = Math.max(max, visited1[i]+visited2[i]);
		}
		System.out.println(max);
	}

	static class Nodes {
		List<Node> list;
		Nodes() {
			this.list = new ArrayList<>();
		}
	}

	static class Node implements Comparable<Node> {
		int next;
		int weight;

		Node (int next, int weight) {
			this.next = next;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
}
