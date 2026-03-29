import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_24272 {
	/*
	오일러경로 dfs
	세그트리 생성
	특정 범위(자식 노드들에 대해) 화살표를 가함.
	->일 때 방향이 자식을 향하는건지, 부모를 향하는건지 파악
	-1, 0, 1로 표시
	들어오는 방향, 초기화, 나가는 방향
	업데이트 시 해당하는 범위에 그냥 반영하기(logN)
	무조건 들어가는 방향쪽에 기록(예: 1->3이면 3쪽의 in,out에 화살표 기록)
	root노드 찾기
	- 1. 들어오는 방향, 나가는 방향의 화살표가 공존하는 경우 루트노드는 없음
	- 2. 범위 기반 판단. 특정 범위 안에서 나가고 들어오는게 있는 경우 그 범위는 루트가 될 수 있음
	- 3. 쿼리가 나가는 쿼리가 추가된 경우 왼쪽, 오른쪽, 자신의 자식 전범위 탐색
	- 4. 나가는 쿼리 추가:
		왼,오 판단 규칙
		- 왼쪽, 오른쪽을 확인했을 때 나가는 쿼리가 하나라도 있는 경우 좋음 0
		- 왼쪽, 오른쪽이 무방향 or 들어가는 방향인 경우 그대로 자식 기준 판단
		자식 판단 규칙
		- 자기 자식 파악 시 나가는 쿼리가 있으면 그 자식들만 root 포함
		- 자기 자식 파악 시 들어가는 쿼리가 있으면 자식들 제외하고 root 포함
		- 무방향인 경우 전체 포함
	- 5. 들어가는 쿼리 추가:
		왼, 오 판단 규칙
		- 왼쪽, 오른쪽에서 나가는 쿼리가 있는 경우 그 나가는 쿼리의 자식만 root가능, 그리고 그 자식에 나가는 쿼리 자식 판단 규칙 적용
		- 왼쪽, 오른쪽에 들어가는 쿼리만 있는 경우 자식노드들 불가능
		- 들어가는 쿼리, 나가는 쿼리 공존 시 루트 노드는 없음
		자식 판단 규칙
		- 자식에 1(나가는 방향)이 존재하는 경우 좋음 0
		- 1이 없는 경우 자식들은 root 제외

	들어가는 쿼리 시 왼 오에서 들어가는 쿼리로 적용된 노드들을 찾기, 이후 들어가기 자식노드 규칙 적용
	나가는 쿼리 적용된 노드 찾기, 자식의 나가는 쿼리 개수가 0일때까지. 자신이 나가는 쿼리인 경우 자식 규칙 적용

	들어가는 쿼리의 자식노드 쿼리 함수()
	나가는 쿼리의 자식노드 쿼리 함수()
	-> 사실상 이것의 반복. 모든 노드에서.
	들어가고 나가는 규칙대로 재귀하면.. 사실상 모든 노드를 찾게 될 가능성이 있는데..

	점프를 해볼까
	우선 모든 노드들은 자신들의 자식노드가 뭔지 알고 있음.
	그 위치로 바로 찾아가고 싶음.
	map으로 방향간선별로

	자기 자신 기준으로 가능할 듯함
	1. 자신에게 나가는 쿼리가 있다면
		- 자기 자식 파악 시 나가는 쿼리가 있으면 그 자식들만 root 포함
		- 자기 자식 파악 시 들어가는 쿼리가 있으면 자식들 제외하고 root 포함
		- 무방향인 경우 전체 포함
	2. 자신에게 들어가는 쿼리가 있다면
		- 자식에 1(나가는 방향)이 존재하는 경우 좋음 0
		- 1이 없는 경우 자식들은 root 제외
	3. 아무것도 없다면
		- 전체 반환 후 부모의 규칙 적용

	이 3가지 자신에 대한 규칙으로 정리가 가능할 듯
	그러면 세그를 어떻게 돌리나?
	1. 자기 자신 -1,0,1인지 체크(정확히 일치하는 범위에만 체크하고 멈추기), 그리고 하위노드들의 -1,1 개수 체크 (올라오면서 체크)
	2. 아전 규칙을 재활용할 수 있으려나?
	3. 서브 쿼리 단위로 체크를 확장하기? 그래서 변화가 적용되면

	근데 쿼리를 기준으로 하면 좀 매번 규칙이 바뀌는게 귀찮아.
	자신을 기준으로 하자. 자신에게 들어오는 쿼리가 적용된 경우. 나가는 쿼리가 적용된 경우
	그걸 기준으로 자식을 어떻게 탐색할지를 정하는 거임.
	자신 노드에는 -1, 0, 1 중 뭐가 부모에게서 연결되어 있는지를 저장 (범위 저장)
	그리고 자식 노드들(세그 기준)에 몇개가 저장되어 있는지 확인
	현재 자신의 간선은 in-out에 저장
	자식이 뭐가 있는지도 거기에 저장하면 좀 간편할 것 같은데
	세그의 특성 상 특정 범위에 자식만 포함된게 아니니까..
	정확한 in out 특정 범위를 포함해서 search를 하면 그 자식노드들의 특성을 알 수 있음. (자식 노드 규칙 쉽게 찾기 가능)
	그리고 자신의 왼쪽 오른쪽은 자신의 조상들의 자식에 해당됨.
	부모에게 간선관리를 맡기면? 그러면 자식들이 여러개가 될 수 있음.
	자식에게 맞기면 그 자신을 root로 한 서브트리가 그 범위가 됨.
	그렇다면 트리의 나머지 부분을 어떻게 처리하는가..
	각노드는 자신의 현재 방향, 자식들의 방향 간선 개수를 저장해두기

	좀 더 단순해야 함
	-1, 0, 1 로 표시했을 때
	루트에 포함되려면 어떻게 외어야 하는가?
	자식에 적용된 -1과 1
	자식에 적용된게 아니라 부모에 적용된걸로 생각하면

	특정 구역(in~out)이 나갈 수 있는 쿼리가 추가됨
	특정 구역만 루트 가능

	5~8 구역이 0이라면?
	자식들이 1 혹은 -1 혹은 0으로 등록될 가능성이 있음

	- 처음 업데이트 시 본인 노드를 찾아가면서 범위 영역에 +1, -1을 함
	5~7, 8~10 이 있는 경우
	0     1
	5~6 -1 넣기
	-1

	 */
	static int[] in;
	static int[] out;
	static int[] direction;
	static int TIME;
	static int[] tree;
	static int[] value;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N =Integer.parseInt(br.readLine());
		Node[] nodes = new Node[N+1];
		TIME = 0;
		in = new int[N+1];
		out = new int[N+1];
		direction = new int[N+1];
		for (int i=1;i<N+1;i++) {
			nodes[i] = new Node();
		}

		List<int[]> edges = new ArrayList<>();
		Node[] adj = new Node[N+1];
		for (int i = 1; i <= N; i++) adj[i] = new Node();

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			int v = Integer.parseInt(st.nextToken());

			adj[u].children.add(v);
			adj[v].children.add(u);

			int dirInt;
			if (dir.equals("->")) dirInt = 1;
			else if (dir.equals("<-")) dirInt = -1;
			else dirInt = 0;

			edges.add(new int[]{u, v, dirInt});
		}

		dfs(adj, nodes, 1,0);

		tree = new int[TIME*4];
		value = new int[TIME*4];
		for (int[] edge : edges) {
			// 바깥쪽 방향이면 그 범위 이외에 +1
			// 안쪽 방향이면 그 범위에 +1
			// 무방향이면 생략
			// 바깥쪽 방향에서 안쪽으로 바뀌면 -1
			// 우선 안쪽인지 바깥인지 알아야 함. u v 중 누가 부모인지 알아야 함.
			int u = edge[0];
			int v = edge[1];
			int dirInt = edge[2];

			if (dirInt == -1) {
				setQuery(nodes, v, u, 1);
			} else if (dirInt == 1) {
				setQuery(nodes, u, v, 1);
			}
		}
		int queryCnt = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<queryCnt;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			String dir = st.nextToken();
			int v = Integer.parseInt(st.nextToken());

			int dirInt;
			if (dir.equals("->")) dirInt = 1;
			else if (dir.equals("<-")) dirInt = -1;
			else dirInt = 0;

			if (dirInt == -1) {
				setQuery(nodes, v, u, 1);
			} else if (dirInt == 1) {
				setQuery(nodes, u, v, 1);
			} else {
				// u, v 중 누가 자식인지 찾아서 넘기기
				if (nodes[u].children.contains(v)) {
					setQuery(nodes, u, v, 0); // u가 부모
				} else {
					setQuery(nodes, v, u, 0); // v가 부모
				}
			}
			sb.append(N-value[1]).append("\n");
		}
		System.out.println(sb);
	}

	static void dfs(Node[] adj, Node[] nodes, int num, int parent) {
		in[num] = TIME++;
		for (int next : adj[num].children) {
			if (next != parent) {
				nodes[num].children.add(next);
				dfs(adj, nodes, next, num);
			}
		}
		out[num] = TIME-1;
	}

	static void setQuery(Node[] nodes, int u, int v, int dir) {
		// u->v 방향으로 정규화된 상태로 들어옴
		// 자식이 누구인지 판단
		int child = nodes[u].children.contains(v) ? v : u;

		// 이전 상태 제거
		if (direction[child] == 1) { // 이전에 서브트리 밖에 +1 했던것 제거
			build(0, in[child]-1, 0, TIME-1, 1, -1);
			build(out[child]+1, TIME-1, 0, TIME-1, 1, -1);
		} else if (direction[child] == -1) { // 이전에 서브트리 안에 +1 했던것 제거
			build(in[child], out[child], 0, TIME-1, 1, -1);
		}

		// 새 상태 적용
		if (dir == 0) {
			direction[child] = 0;
		} else if (child == v) { // 부모->자식: 서브트리 안 +1
			build(in[child], out[child], 0, TIME-1, 1, +1);
			direction[child] = -1;
		} else { // 자식->부모: 서브트리 밖 +1
			build(0, in[child]-1, 0, TIME-1, 1, +1);
			build(out[child]+1, TIME-1, 0, TIME-1, 1, +1);
			direction[child] = 1;
		}
	}

	static void build(int start, int end, int left, int right, int node, int cnt) {
		if (start > right || end < left) return;

		int mid = (left + right) / 2;
		if (left >= start && right <= end) {
			tree[node] += cnt;
		} else {
			build(start, end, left, mid, node*2, cnt);
			build(start, end, mid+1, right, node*2+1, cnt);
		}

		// value 갱신
		if (tree[node] > 0)
			value[node] = right - left + 1;
		else if (left == right)
			value[node] = 0;
		else
			value[node] = value[node*2] + value[node*2+1];
	}

	static class Node {
		Set<Integer> children;

		Node() {
			this.children = new HashSet<>();
		}
	}
}
