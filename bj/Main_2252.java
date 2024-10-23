import java.io.*;
public class Main_2252 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] indegree = new int[N+1]; 
		Map<Integer, HashSet<Integer>> parents = new HashMap<>();
		for (int i=1;i<N+1;i++) {
			parents.put(i,new HashSet<>());
		}
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			indegree[b] += 1;
			parents.get(a).add(b);
		}
		List<Integer> list = new ArrayList<>();
		Queue<Integer> q = new ArrayDeque<>();
		for (int i=1;i<N+1;i++) {
			if (indegree[i] == 0) {
				q.add(i);
				list.add(i);
			}
		}
		StringBuilder sb = new StringBuilder();
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i=0;i<size;i++) {
				int curnum = q.poll();
				sb.append(curnum).append(" ");
				for (int num:parents.get(curnum)) {
					if (--indegree[num] == 0) {
						q.add(num);
					}
				}
			}
		}
		
		System.out.println(sb);
	}

}
