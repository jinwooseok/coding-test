import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_2927 {
	/*
	bridge 해결 : 다리를 이으면 하나의 분리집합으로 편입
	penguins 해결 : 세그트리, 지점 업데이트 - logn
	excursion 해결 : A~B 사이의 펭귄 수의 합 (logn) 범위 탐색
		이동할 수 없는 경우 (분리집합에 포함되지 않은 경우) - parents 배열로 판별

	세그트리 생성 - 오경테, 헤라디
	오경테는 dfs 순서 따라감. 가능은 할듯
	헤라디도 당연히 가능은 할듯

	분리집합이 생성되었을 때, 이미 연결되었으면 다리를 추가하지 않는다는 점에서
	분리집합 자체가 하나의 트리처럼 될 수 있으며(간선이 n-1개), 그 트리를 세그먼트로 변환하기

	중요한 것은 매번 세그먼트 트리를 새로 생성하면서 계산이 가능한가?
	세그먼트 트리의 생성 -> 노드별로 1번씩 삽입이 필요함 logn값 넣을 노드의 개수
	그러므로 브릿지를 미리 연결했다는 가정하에 시작하는게 맞고, 업데이트가 이뤄지는 방향
	업데이트 한번 -> logn, 전체 쿼리 300000.

	1. 브릿지를 미리 다 연결시켜서 각각 포레스트의 트리 별로 hld 및 세그 트리 생성
	2. 실제 연결되어있는지 확인 = 초기화한 분리집합으로 판별
	3. 펭귄 개수 업데이트 => 포함된 체인에 세그먼트 트리 업데이트
	4. 펭귄 수 합 => 체인 점프 및 세그먼트 구간합 사용. 아직 브릿지 생성안된 경우는 이미 분리집합에서 커트당함
	 */
	// sz: 서브트리의 크기, dep: 깊이, top: 체인헤더, par: 부모배열, in,out (dfs 순서)
	static int[] sz, dep, par, top, in, out, tree, uf;
	static int TIME = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 쿼리를 받아들이고, 그 때 유니온 파인드 저장. 쿼리는 다시 큐에 저장
		// 그러면 각 섬이 집합별로 구분됨.
		// 각 섬에서 가장 빠른 노드들을 루트로 함
		// 우선 브릿지 명령을 받아 간선리스트로 저장.
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		Node[] nodes = new Node[N+1];
		sz  = new int[N+1]; // heavy-light 구분 용도
		dep = new int[N+1]; // 트리의 깊이로 쿼리 시 루트를 종점으로 하기 위한 요소. 이게 없으면 루트를 초과해버림. 0의 부모는 계속 0이기 때문에 무한루프 발생 (부모 -> 자식노드로는 점프 불가하기 때문)
		par = new int[N+1]; // 헤비 체인 헤더와 연결된 다른 체인의 인접점
		top = new int[N+1]; // 특정 체인의 헤더(헤비 체인 헤더)
		in  = new int[N+1]; // in, out은 dfs 순서로 실질적 세그트리 노드 번호
		out = new int[N+1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1;i<=N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i=0;i<=N;i++) {
			nodes[i] = new Node();
		}

		// 브릿지만 찾아 인접리스트, 부모 배열 생성
		List<Query> queries = new ArrayList<>();
		uf = new int[N+1];
		for (int i=0;i<=N;i++) uf[i] = i;
		int qCnt = Integer.parseInt(br.readLine());
		for (int i=0;i<qCnt;i++) {
			st = new StringTokenizer(br.readLine());
			String op = st.nextToken();
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (op.equals("bridge")) {
				if (!isSameGroup(a, b)) {
					union(a, b);
					nodes[a].children.add(b);
					nodes[b].children.add(a);
				}
			}
			queries.add(new Query(op, a, b));
		}

		// 포레스트에서 각 트리별 가장 앞의 노드를 찾고, 0번 노드에 연결(하나의 트리로)
		Set<Integer> roots = new HashSet<>();
		for (int i = 1; i <= N; i++) {
			roots.add(find(i));
		}
		for (int root : roots) {
			nodes[0].children.add(root);
		}

		// HLD 배열 생성
		dfs1(nodes, 0);
		dfs2(nodes, 0);
		// System.out.println(Arrays.toString(in));
		// 세그 트리 빌드
		tree = new int[(N+1)*4];

		// 부모 배열 초기화
		uf = new int[N+1];
		for (int i=0;i<=N;i++) uf[i] = i;
		for (int i=1;i<=N;i++) {
			update(1, 1, N, in[i], arr[i]);
		}
		StringBuilder sb = new StringBuilder();
		for (Query q : queries) {
			// 실제 쿼리 처리
			if (q.command.equals("bridge")) {
				if (isSameGroup(q.firstInput, q.secondInput)) {
					sb.append("no");
				} else {
					union(q.firstInput, q.secondInput);
					sb.append("yes");
				}
				sb.append("\n");
			} else if (q.command.equals("penguins")) {
				update(1, 1, N, in[q.firstInput], q.secondInput);
			} else {
				if (isSameGroup(q.firstInput, q.secondInput)) {
					sb.append(query(q.firstInput, q.secondInput, N));
				} else {
					sb.append("impossible");
				}
				sb.append("\n");
			}
 		}
		// System.out.println(Arrays.toString(top));
		// System.out.println(Arrays.toString(arr));
		System.out.println(sb);
	}

	// HLD를 구현하기 위해 서브트리 사이즈를 체크. 이 과정에서 깊이, 부모배열 생성
	static void dfs1(Node[] nodes, int v) {
		sz[v] = 1;
		int tmp;
		for (int i=0;i<nodes[v].children.size();i++) {
			int next = nodes[v].children.get(i);
			if (next == par[v]) continue;
			dep[next] = dep[v] + 1;
			par[next] = v;
			dfs1(nodes, next);
			sz[v] += sz[next];
			// 맨 앞의 서브트리와 비교했을 때 더 큰 경우(heavy 간선인 경우 앞으로 보냄)
			// 이미 내려왔고, 돌아가는 길이기 때문에 순서를 바꿔도 문제가 발생하지 않음
			if (sz[next]>sz[nodes[v].children.get(0)]) {
				tmp = nodes[v].children.get(0);
				nodes[v].children.set(0, next);
				nodes[v].children.set(i, tmp);
			}
		}
	}

	static int find(int x) {
		if (uf[x] == x) return x;
		return uf[x] = find(uf[x]);
	}

	static void union(int a, int b) {
		int parA = find(a);
		int parB = find(b);

		if (parA>parB) {
			uf[parA] = parB;
		} else {
			uf[parB] = parA;
		}
	}

	static boolean isSameGroup(int a, int b) {
		int parA = find(a);
		int parB = find(b);
		return parA == parB;
	}

	// HLD를 구현하기 위해 각각의 노드에 헤더 할당. in, out 배열을 동시에 채워주는데, 헤더와 연결된 지점을 찾을 수 있음
	static void dfs2(Node[] nodes, int v) {
		in[v] = TIME++;
		for (int i=0;i<nodes[v].children.size();i++) {
			int next = nodes[v].children.get(i);
			if (next == par[v]) continue;
			if (i == 0) top[next] = top[v];  // 직진 (가장 긴 child로 맨 앞으로 옮겼던 부분)
			else top[next] = next;            // 분기점 (새로운 체인이 시작됨)
			dfs2(nodes, next);
		}
		out[v] = TIME;
	}

	static void update(int node, int left, int right, int idx, int count) {
		if (left == right) {
			tree[node] = count;
			return;
		}

		int mid = (left+right)/2;
		if (idx<=mid) update(node*2, left, mid, idx, count);
		else update(node*2+1, mid+1, right, idx, count);
		tree[node] = tree[node*2]+tree[node*2+1];
	}

	static int segQuery(int node, int start, int end, int left, int right) {
		if (start>right || end<left) return 0;
		if (left>=start && end>=right) return tree[node];

		int mid = (left+right)/2;
		return segQuery(node*2, start, end, left, mid) + segQuery(node*2+1, start, end, mid+1, right);
	}

	static int query(int a, int b, int N) {
		int sum = 0;
		// 다른 체인에 a, b가 있으면 계속 찾음.
		int tmp = 0;
		while (top[a] != top[b]) {
			// 헤드의 깊이가 깊은것 먼저 진행 (최종적으로 메인 헤비 체인에서 만나거나 그 전에 끊김)
			if (dep[top[a]] < dep[top[b]]) {
				tmp = a;
				a = b;
				b = tmp;
			}
			// 한 체인의 헤드에서 자기 자신까지
			sum += segQuery(1, in[top[a]], in[a], 1, N);
			a = par[top[a]]; // 헤드의 부모(다른 체인으로 점프)
		}
		// 같은 체인에 도달함. 같은 체인 내에서 마지막 쿼리 진행. 깊은 것이 in이 더 크므로 dep로 구분
		if (dep[a] > dep[b]) {
			tmp = a;
			a = b;
			b = tmp;
		}
		sum += segQuery(1, in[a], in[b], 1, N);
		return sum;
	}

	static class Node {
		List<Integer> children;
		Node() {
			children = new ArrayList<>();
		}
	}

	static class Query {
		String command;
		int firstInput;
		int secondInput;

		Query(String command, int firstInput, int secondInput) {
			this.command = command;
			this.firstInput = firstInput;
			this.secondInput = secondInput;
		}
	}
}