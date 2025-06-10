import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_11724 {
	/**
	 * 연결요소가 뭐지?
	 * 1,2,5,1
	 * 3,4,6
	 *
	 * 1,2,5,4
	 * 3,4,6
	 *
	 * 연결된 집합의 개수를 의미하는 듯? union-find 이후 머리의 개수세면 될듯
	 * @param args
	 */
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		parents = new int[n+1];
		for (int i=1;i<=n;i++) {
			parents[i] = i;
		}

		for (int i=1;i<=m;i++) {
			st = new StringTokenizer(br.readLine());
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}

		Set<Integer> set = new HashSet<>();

		for (int i=1;i<=n;i++) {
			find(i);
			set.add(parents[i]);
		}

		System.out.println(set.size());
	}

	public static void union(int a, int b) {
		int aParent = find(a);
		int bParent = find(b);

		if (aParent != bParent) {
			parents[bParent] = aParent;
		}
	}

	public static int find(int x) {
		if (x == parents[x]) {
			return parents[x];
		}
		return parents[x] = find(parents[x]);
	}
}
