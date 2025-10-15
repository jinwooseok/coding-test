import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_1765 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		int[] parents = new int[n];
		List<Integer>[] list = new ArrayList[n];
		for (int i=0;i<n;i++) {
			list[i] = new ArrayList<>();
			parents[i] = i;
		}
		for (int i=0;i<m;i++) {
			st = new StringTokenizer(br.readLine());
			String relation = st.nextToken();
			int p = Integer.parseInt(st.nextToken())-1;
			int q = Integer.parseInt(st.nextToken())-1;

			if (relation.equals("F")) {
				union(parents, p, q);
			} else {
				list[p].add(q);
				list[q].add(p);
			}
		}

		Set<Integer> set = new HashSet<>();
		for (int i=0;i<n;i++) {
			find(parents, i);
		}

		for (int i=0;i<n;i++) {
			//System.out.println(Arrays.toString(parents));
			if (!list[i].isEmpty()) {
				int a = list[i].get(0);
				//System.out.println(list[i]);
				for (int j=1;j<list[i].size();j++) {
					union(parents, a, list[i].get(j));
				}
			}
		}
		for (int i=0;i<n;i++) {
			find(parents, i);
		}
		for (int i=0;i<n;i++) {
			set.add(parents[i]);
		}
		System.out.println(set.size());
	}

	public static int find(int[] parents, int x) {
		if (parents[x] == x) return x;
		return parents[x] = find(parents, parents[x]);
	}

	public static void union(int[] parents, int a, int b) {
		int ap = find(parents,a);
		int bp = find(parents,b);
		if (ap != bp) {
			if (ap < bp) {
				parents[bp] = ap;
			} else {
				parents[ap] = bp;
			}
		}
	}
}
