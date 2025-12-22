import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1033 {
	static List<Node>[] graph;
	static long[] weight;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		graph = new List[N];
		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}
		weight = new long[N];
		Arrays.fill(weight, 1);

		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long p = Long.parseLong(st.nextToken());
			long q = Long.parseLong(st.nextToken());
			long gcdResult = gcd(p, q);
			p /= gcdResult;
			q /= gcdResult;
			graph[a].add(new Node(p, q, b));
			graph[b].add(new Node(q, p, a));
		}

		// 1단계: DFS로 각 서브트리가 필요로 하는 분모 계산
		visited = new boolean[N];
		long rootWeight = calculateWeight(0);

		// 2단계: 루트의 weight를 설정하고 비율대로 계산
		visited = new boolean[N];
		weight[0] = rootWeight;
		distribute(0);

		// 최종: GCD로 최소화
		long gcdAll = weight[0];
		for (int i = 1; i < N; i++) {
			gcdAll = gcd(gcdAll, weight[i]);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(weight[i] / gcdAll).append(" ");
		}
		System.out.println(sb);
	}

	// 각 서브트리가 필요로 하는 최소 분모 계산
	static long calculateWeight(int cur) {
		visited[cur] = true;
		long lcm = 1;

		for (Node node : graph[cur]) {
			if (visited[node.next]) continue;

			// 자식 서브트리의 필요 분모
			long childWeight = calculateWeight(node.next);

			// cur:child = p:q
			// child가 childWeight를 가지려면
			// cur는 childWeight * p / q를 가져야 함
			// 정수로 만들기 위해: lcm(q, childWeight) 사용
			long lcmValue = lcm(node.q, childWeight);
			long curNeed = lcmValue * node.p / node.q;

			lcm = lcm(lcm, curNeed);
		}

		return lcm;
	}

	// 루트부터 비율대로 분배
	static void distribute(int cur) {
		visited[cur] = true;

		for (Node node : graph[cur]) {
			if (visited[node.next]) continue;
			weight[node.next] = weight[cur] * node.q / node.p;
			distribute(node.next);
		}
	}

	static class Node {
		long p, q;
		int next;
		Node(long p, long q, int next) {
			this.p = p;
			this.q = q;
			this.next = next;
		}
	}

	public static long gcd(long a, long b) {
		if (a < b) {
			long temp = a;
			a = b;
			b = temp;
		}
		return b == 0 ? a : gcd(b, a % b);
	}

	public static long lcm(long a, long b) {
		return a / gcd(a, b) * b; // overflow 방지
	}
}