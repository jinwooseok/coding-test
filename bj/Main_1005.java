import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[] buildings, results;
        int N,K;
        int T = Integer.parseInt(br.readLine());
        Queue<Integer> q = new LinkedList<>();;
        Map<Integer,List<Integer>> prevs;
        for (int t=1;t<=T;t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            buildings = new int[N];
            results = new int[N];
            st = new StringTokenizer(br.readLine());
            prevs = new HashMap<>();
            for (int i=0;i<N;i++) {
                buildings[i] = Integer.parseInt(st.nextToken());
                results[i] = buildings[i];
            }
            for (int i=0;i<K;i++) {
                st = new StringTokenizer(br.readLine());
                int prev = Integer.parseInt(st.nextToken());
                int next = Integer.parseInt(st.nextToken());
                prevs.putIfAbsent(next, new ArrayList<>());
                prevs.get(next).add(prev);
            }
            int target = Integer.parseInt(br.readLine());
            q.add(target);
            int max = 0;
            boolean[] visited = new boolean[N + 1];
            while (!q.isEmpty()) {
                int current = q.poll();
                if (prevs.get(current) == null) {
                    max = Math.max(max, results[current-1]);
                    continue;
                }
                for (int prev:prevs.get(current)) {
                    results[prev-1] = Math.max(results[prev-1] ,buildings[prev-1]+results[current-1]);
                    if (!visited[prev]) {
                        visited[prev] = true;
                        q.add(prev);
                    }
                }
            }
            System.out.println(max);
        }
    }
}
