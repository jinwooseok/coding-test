import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_3830 {
	/**
	 * 5시 시작
	 * @param args
	 * @throws IOException
	 *
	 * 1. 배열[Node(idx, idjMap)] 준비
	 * 2. 배열에 관계대로 넣기 (양방향 그래프)
	 * 3. 찾기 (bfs, 양방향에 대비한 방문 배열 추가)
	 *
	 * nono
	 *
	 * union-find로 상향 정렬 (a,b 중 큰거 기준으로 정렬)
	 * 1. 숫자 들어올때마다 union 진행, 크기 저장
	 * 2. 확인 시에는 부모가 같은지 확인.(다르면 확인 불가인것.)
	 * 3. 부모가 같은 경우 두 크기 빼기 진행
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			if (N==0 && M==0) break;

			Node[] arr = new Node[N + 1];
			for (int i = 1; i <= N; i++) {
				arr[i] = new Node(i);
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				String command = st.nextToken();
				if (command.equals("!")) {
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					int w = Integer.parseInt(st.nextToken());
					if (a<b) union(arr, a, b, w);
					else union(arr, b, a, -w);
				} else {
					int a = Integer.parseInt(st.nextToken());
					int b = Integer.parseInt(st.nextToken());
					Integer result = search(arr, a, b);
					if (result != null)
						sb.append(result).append('\n');
					else
						sb.append("UNKNOWN").append('\n');
				}
			}
		}
		System.out.println(sb);
	}

	static int find(Node[] arr, int x) {
		if (arr[x].parents[0] == x) return arr[x].parents[0];
		int parentX = find(arr, arr[x].parents[0]);
		arr[x].parents[1] = arr[arr[x].parents[0]].parents[1] + arr[x].parents[1]; //자기부터~바로위부모 + 바로위부모~최상위부모
		arr[x].parents[0] = arr[arr[x].parents[0]].parents[0];
		return parentX;
	}

	static void union(Node[] arr, int a, int b, int w) {
		int parentA = find(arr, a);
		int parentB = find(arr, b);

		if (parentA >= parentB) {
			arr[parentA].parents[0] = parentB;
			arr[parentA].parents[1] = arr[parentA].parents[1]+arr[b].parents[1]-arr[a].parents[1]-arr[parentB].parents[1]-w; // a to b
		} else {
			arr[parentB].parents[0] = parentA;
			arr[parentB].parents[1] = -(arr[parentA].parents[1]+arr[b].parents[1]-arr[a].parents[1]-arr[parentB].parents[1]-w);
		}
	}

	static Integer search(Node[] arr, int a, int b) {
		int parentA = find(arr, a);
		int parentB = find(arr, b);
		if (parentA != parentB) return null;
		else return arr[b].parents[1]-arr[a].parents[1];
	}

	static class Node {
		// Map<Integer, Integer> idjMap;
		int[] parents;
		Node(int idx) {
			// this.idjMap = new HashMap<>();
			this.parents = new int[]{idx, 0};
		}
	}
}
