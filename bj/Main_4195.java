import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_4195 {
	/**
	 * 유니온 파인드로 분리집합으로 묶음. 그리고, 그 분리 집합의 인원 수를 Map으로 기록
	 * @param args
	 * @throws IOException
	 */
	static int[] parents;
	static Map<Integer, Integer> setCountMap;
	static Map<String, Integer> nameIdxMap;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		for (int i=0;i<N;i++) {
			int friendIdx = 0;
			setCountMap = new HashMap<>();
			nameIdxMap = new HashMap<>();
			parents = new int[200000];
			for (int j=0;j<200000;j++) {
				parents[j] = j;
			}
			int M = Integer.parseInt(br.readLine());
			for (int rel=0;rel<M;rel++) {
				st = new StringTokenizer(br.readLine());
				String name1 = st.nextToken();
				String name2 = st.nextToken();
				if (!nameIdxMap.containsKey(name1)) {
					nameIdxMap.put(name1, friendIdx++);
					setCountMap.put(nameIdxMap.get(name1), 1);
				}
				if (!nameIdxMap.containsKey(name2)) {
					nameIdxMap.put(name2, friendIdx++);
					setCountMap.put(nameIdxMap.get(name2), 1);
				}

				union(nameIdxMap.get(name1), nameIdxMap.get(name2));
				sb.append(setCountMap.get(parents[nameIdxMap.get(name1)])).append("\n");

			}
		}
		System.out.println(sb);
	}

	public static int find(int x) {
		if (parents[x] == x) return x;
		setCountMap.put(parents[x], setCountMap.get(x)+setCountMap.get(parents[x]));
		setCountMap.put(x, 0);
		return parents[x] = find(parents[x]);
	}

	public static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);

		if (parentA != parentB) {
			if (parentA >= parentB) {
				parents[parentA] = parentB;
				setCountMap.put(parentB, setCountMap.get(parentB)+setCountMap.get(parentA));
				setCountMap.put(parentA, 0);
			} else {
				parents[parentB] = parentA;
				setCountMap.put(parentA, setCountMap.get(parentB)+setCountMap.get(parentA));
				setCountMap.put(parentB, 0);
				// System.out.println(parentA);
				// System.out.println(setCountMap);
			}
		}
		find(a);
	}
}
