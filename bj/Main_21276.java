import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_21276 {
	/*
	Union-Find
	1. map으로 사람 이름과 숫자 매핑
	2. 사람이름이 언급될 때마다 Union-Find
	3. 시조가 누구냐
	4. 자식 수는 위상정렬로 위로 가면서 합치기 (트리마다)
	5. 사전순 나열 후 출력

	자료구조
	- 이름 + 숫자 매핑 맵
	- 리스트[노드], 노드: 사람이름, 직계 자식 수, 직계 자식 번호들
	- 사전 순 리스트
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Map<String, Integer> map = new HashMap<>();
		Map<Integer, String> idxMap = new HashMap<>();
		Node[] nodeArr = new Node[N];
		int cnt = 0;
		for (int i=0;i<N;i++) {
			nodeArr[i] = new Node(st.nextToken());
			map.put(nodeArr[i].name, cnt);
			idxMap.put(cnt++, nodeArr[i].name);
		}

		int M = Integer.parseInt(br.readLine());

		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			String child = st.nextToken();
			String ancestor = st.nextToken();
			int childIdx = map.get(child);
			int ancestorIdx = map.get(ancestor);
			nodeArr[childIdx].ancestorCnt++;
			nodeArr[ancestorIdx].children.add(child);
			nodeArr[childIdx].ancestors.add(ancestor);
		}
		Queue<Integer> q = new LinkedList<>();

		StringBuilder sb = new StringBuilder();
		int rootCnt = 0;
		for (int i=0;i<N;i++) {
			if (nodeArr[i].ancestors.isEmpty()) {
				rootCnt++;
				q.add(i);
			}
		}

		sb.append(rootCnt).append("\n");


		while (!q.isEmpty()) {
			int cur = q.poll();
			// 자식 찾기
			for (String child:nodeArr[cur].children) {
				nodeArr[map.get(child)].ancestorCnt--;
				if (nodeArr[map.get(child)].ancestorCnt == 0) {
					q.add(map.get(child));
					nodeArr[cur].result.add(child);
				}
			}
		}
		Arrays.sort(nodeArr);

		for (Node node:nodeArr) {
			if (node.ancestors.size() == 0) sb.append(node.name).append(" ");
		}
		sb.append("\n");
		for (Node node:nodeArr) {
			sb.append(node.name).append(" ");
			sb.append(node.result.size()).append(" ");
			Collections.sort(node.result);
			for (String name:node.result) {
				sb.append(name).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

	static class Node implements Comparable<Node> {
		String name;
		int ancestorCnt;
		Set<String> ancestors;
		Set<String> children;
		List<String> result;

		Node(String name) {
			this.name = name;
			this.ancestorCnt = 0;
			this.ancestors = new HashSet<>();
			this.children = new HashSet<>();
			this.result = new ArrayList<>();
		}

		@Override
		public int compareTo(Node o) {
			return this.name.compareTo(o.name);
		}
	}
}
