import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_13510 {
	/*
	트리

	1: i번 간선의 비용을 c로 바꿈
	2: u->v로 가는 단순 경로에 존재하는 비용 중 최대 비용 출력

	경로 사이의 비용이므로 트리를 연속된 배열로 표현할 수 있는 HLD가 적합함.
	ETT는 서브트리를 기준으로 배열을 구분하기 때문에 적합하지 않음

	1. HLD로 트리를 배열로 변환
	2. in을 기준으로 최댓값 세그 트리 생성 및 업데이트
	3. 업데이트는 지점 업데이트, 쿼리는 범위 쿼리

	준비물: in, out, size, depth, tree, arr, top, parent
		- in, out: dfs2에서 HL 순서로 DFS를 진행하며 확정된 in, out. 세그트리 실질적 노드
		- size: HLD를 구성하기 위한 서브트리 크기 배열
		- depth: 체인 쿼리 시 최종 지점을 루트로 하기 위해 깊은 지점을 우선 시행하기 위한 준비물
		- tree: in의 크기(arr 크기)의 4배 사이즈 세그트리 본체
		- arr: 원본 배열
		- top: 각 체인의 헤더를 의미
		- parent: 부모 배열. 유틸이자 노드의 부모로 양방향 간선을 dfs할 시 순환 방지 + 체인 점프
	준비함수: dfs1, dfs2, update, segQuery, query, init
	- dfs1: 각 서브트리의 사이즈 체크, 깊이 체크, 부모 체크, heavy-light 이동(heavy를 맨 앞으로)
	- dfs2: 각 서브트리의 in, out 선정
	- update: 세그트리의 지점 업데이트 (전형적)
	- segQuary: 내부 세그(한 체인의 쿼리 수행)
	- query: 전체 쿼리 수행(체인 점프 및 내부 세그쿼리 수행)
		- 한 체인 내에 있는 경우 segQuery를 실행시킴
		- 각 노드가 다른 체인에 있다면 while문으로 같아질때까지 깊이 순서로 계속 실행
		- 최종에 같은 체인에 도달한 경우 한번 더 실행시켜야함

	= init: 초기화 - 주로 N+1, 양방향 Node 배열(인접리스트), u,v,w이므로 비용도 함께 저장

	시복: M*log(N)^2

	간선의 비용 1000000보다 작음. 최댓값 기준이므로 long 필요 xxxxx

	아 잘못했다. 비용이 간선에 있음. 간선번호가 있기도 해서 간선 정보를 기록할 테이블이 필요함
	그 간선의 자식노드의 in에 붙이는 방향으로 세그 해야함.
	 */
	static int[] in, out, size, depth, tree, top, parent, weight;
	static Edge[] edges;
	static Node[] nodes;
	static int N, TIME;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		// 초기화
		init();

		// 간선 입력 (N-1개)
		for (int i=1;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			nodes[u].children.add(new int[]{v,w});
			nodes[v].children.add(new int[]{u,w});
			edges[i] = new Edge(u, v, w);
		}

		// HLD 생성
		top[1] = 1;
		dfs1(1);
		dfs2(1);

		// 세그 트리 빌드 (기존 정보를 사용해 업데이트 해야함)
		// System.out.println(Arrays.toString(in));
		// System.out.println(Arrays.toString(out));
		tree = new int[(N+1)*4];
		// System.out.println(TIME);
		for (int i=1;i<=N;i++) {
			update(1, in[i], 1, N, weight[i]);
		}

		// 쿼리 입력 및 실행
		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if (command == 1) {
				if (parent[edges[a].u] == edges[a].v) {
					update(1, in[edges[a].u], 1, N, b);
				} else {
					update(1, in[edges[a].v], 1, N, b);
				}
			} else {
				sb.append(query(a, b)).append("\n");
			}
		}

		System.out.println(sb);
	}

	static void init() {
		in = new int[N+1];
		out = new int[N+1];
		size = new int[N+1];
		depth = new int[N+1];
		nodes = new Node[N+1];
		edges = new Edge[N+1];
		top = new int[N+1];
		parent = new int[N+1];
		weight = new int[N+1];

		for (int i=1;i<=N;i++) {
			nodes[i] = new Node();
			parent[i] = i;
		}

		TIME = 0;
	}

	static void dfs1(int cur) {
		size[cur] = 1;
		int tmp;
		int tmpWeight;
		// System.out.println(nodes[cur].children);
		for (int i=0;i<nodes[cur].children.size();i++) {
			int next = nodes[cur].children.get(i)[0];
			int nextWeight = nodes[cur].children.get(i)[1];
			if (parent[cur] == next) continue;
			parent[next] = cur;
			depth[next] = depth[cur]+1;
			dfs1(next);
			size[cur] += size[next];
			if (size[nodes[cur].children.get(0)[0]] < size[next]) {
				tmp = nodes[cur].children.get(0)[0];
				tmpWeight = nodes[cur].children.get(0)[1];
				nodes[cur].children.set(0, new int[]{next, nextWeight});
				nodes[cur].children.set(i, new int[]{tmp, tmpWeight});
			}
		}
	}

	static void dfs2(int cur) {
		in[cur]=++TIME;
		for (int i=0;i<nodes[cur].children.size();i++) {
			int next = nodes[cur].children.get(i)[0];
			int nextWeight = nodes[cur].children.get(i)[1];
			if (parent[cur] == next) continue;

			weight[next] = nextWeight;
			if (i == 0) top[next] = top[cur];
			else top[next] = next;
			dfs2(nodes[cur].children.get(i)[0]);
		}
		out[cur]=TIME;
	}

	static void update(int node, int idx, int left, int right, int weight) {
		if (left == right) {
			tree[node] = weight;
			return;
		}

		int mid = (left+right)/2;
		if (idx>=left && idx<=mid) {
			update(node*2, idx, left, mid, weight);
		} else if (idx>=(mid+1) && idx<=right) {
			update(node*2+1, idx, mid+1, right, weight);
		}

		tree[node] = Math.max(tree[node*2], tree[node*2+1]);
	}

	static int segQuery(int node, int start, int end, int left, int right) {
		if (end < left || start > right) return 0;
		if (start <= left && right <= end) {
			return tree[node];
		}
		int mid = (left+right)/2;
		return Math.max(segQuery(node*2, start, end, left, mid), segQuery(node*2+1, start, end, mid+1, right));
	}

	static int query(int a, int b) {
		int tmp;
		int max = 0;
		// 난 무조건 a에서 트리탈거임. 더 깊은거 있으면 a로 바꾸삼
		while (top[a] != top[b]) {
			if (depth[top[a]] < depth[top[b]]) {
				tmp = a;
				a = b;
				b = tmp;
			}

			max = Math.max(max, segQuery(1, in[top[a]], in[a], 1, N));
			a = parent[top[a]];
		}
		// b가 depth가 깊으면 b와 a를 바꿈 -> b가 항상 a보다 얕음
		// 체인이므로 당연히 depth가 체인의 순서가 됨
		if (depth[a]<depth[b]) {
			tmp = a;
			a = b;
			b = tmp;
		}

		max = Math.max(max, segQuery(1, in[b]+1, in[a], 1, N));

		return max;
	}

	static class Edge {
		int u;
		int v;
		Edge(int u, int v, int w) {
			this.u = u;
			this.v = v;
		}
	}

	static class Node {
		List<int[]> children;
		Node() {
			children = new ArrayList<>();
		}
	}
}
