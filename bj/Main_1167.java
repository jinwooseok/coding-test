import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1167 {
	/**
	 * 1. 준비물 : graph(그래프 저장), maxLength(최대 지름 저장), nodes(1개, 2개 사이 저장)
	 * 2. 과정
	 * 	1 - 트리 구성
	 * 	2 - dfs를 통해 들어가는 과정이 아닌 돌아오는 과정 활용(LRC 후위순회)
	 * 	3 - 서브트리의 결과를 위로 올림. (최대 길이 서브 트리 2개 합과 최대 길이 서브 트리 1개 합)
	 * 	4 - ROOT 까지 반복하며 maxLength에 저장
	 * @param args
	 * @throws IOException
	 */
	static int maxLength = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int V = Integer.parseInt(br.readLine());

		Node[] nodes = new Node[V+1];
		int root = 1;

		// nodes 초기화
		for (int v=1;v<=V;v++) {
			nodes[v] = new Node();
		}

		// nodes에 간선 정보 삽입
		for (int v=1;v<=V;v++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end;
			while ((end=Integer.parseInt(st.nextToken()))!=-1) {
				 int length = Integer.parseInt(st.nextToken());
				nodes[start].children.add(new Edge(start, end, length));
			}
			Collections.sort(nodes[start].children);
			//System.out.println(nodes[start].children);
		}
		dfs(nodes, root, -1);
		System.out.println(maxLength);
	}

	public static void dfs(Node[] nodes, int curNum, int parent) {
		int maxLinkLength = 0;
		for (Edge child:nodes[curNum].children) {
			if (child.end == parent) continue;
			dfs(nodes, child.end, curNum);
			if (nodes[curNum].children.size() == 1) {
				nodes[curNum].subMaxEdgeLength = Math.max(nodes[child.end].subMaxEdgeLength+child.length,nodes[curNum].subMaxEdgeLength);
			} else if (nodes[curNum].children.size() > 1){
				int pathThroughChild = nodes[child.end].subMaxEdgeLength + child.length;
				nodes[curNum].subMaxLinks.add(pathThroughChild);

				// 현재 노드의 최대 경로 업데이트
				nodes[curNum].subMaxEdgeLength = Math.max(nodes[curNum].subMaxEdgeLength, pathThroughChild);
				while (nodes[curNum].subMaxLinks.size()>2) {
					nodes[curNum].subMaxLinks.poll();
				}
			}
		}
		if (nodes[curNum].children.isEmpty()) {
			nodes[curNum].subMaxEdgeLength = 0;
		}
		while (!nodes[curNum].subMaxLinks.isEmpty()) {
			maxLinkLength+=nodes[curNum].subMaxLinks.poll();
		}
		maxLength = Math.max(maxLength, nodes[curNum].subMaxEdgeLength);
		maxLength = Math.max(maxLength, maxLinkLength);
	}

	public static class Node {
		List<Edge> children;
		int subMaxEdgeLength; // 최대 간선 길이
		PriorityQueue<Integer> subMaxLinks; // 최대 2개 연결 길이
		Node() {
			this.children = new ArrayList<>();
			this.subMaxEdgeLength = 0;
			this.subMaxLinks = new PriorityQueue<>();
		}
	}
	static class Edge implements Comparable<Edge> {
		int start;
		int end;
		int length;
		Edge(int start, int end, int length) {
			this.start = start;
			this.end = end;
			this.length = length;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(o.length, this.length);
		}
	}
}
