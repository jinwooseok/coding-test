import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t=1;t<=T;t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[] buildings = new int[N+1];
            int[] results = new int[N+1];
            int[] indegrees = new int[N+1];
            st = new StringTokenizer(br.readLine());
            Map<Integer,List<Integer>> prevs = new HashMap<>();
            Map<Integer,List<Integer>> nexts = new HashMap<>();
            for (int i=1;i<=N;i++) {
                buildings[i] = Integer.parseInt(st.nextToken());
                results[i] = buildings[i];
            }
            for (int i=0;i<K;i++) {
                st = new StringTokenizer(br.readLine());
                int prev = Integer.parseInt(st.nextToken());
                int next = Integer.parseInt(st.nextToken());
                prevs.putIfAbsent(next, new ArrayList<>());
                prevs.get(next).add(prev);
                nexts.putIfAbsent(prev, new ArrayList<>());
                nexts.get(prev).add(next);
                indegrees[next] += 1;
            }
            int target = Integer.parseInt(br.readLine());
            Queue<Integer> prevq = new LinkedList<>();
            Queue<Integer> nextq = new LinkedList<>();
            prevq.add(target);
            boolean[] visitedPrev = new boolean[N+1];
            while (!prevq.isEmpty()) {
                int cur = prevq.poll();
                if (prevs.get(cur)==null) {
                    nextq.add(cur);
                    continue;
                }
                if (!visitedPrev[cur]) {
                    visitedPrev[cur] = true;
                    prevq.addAll(prevs.get(cur));
                }
            }
            boolean[] visitedNext = new boolean[N+1];
            while (!nextq.isEmpty()) {
                int cur = nextq.poll();
                if (cur == target) {
                    break;
                }
                if (nexts.get(cur)==null) {
                    continue;
                }
                if (!visitedNext[cur]) {
                    visitedNext[cur] = true;
                } else {
                    continue;
                }
                for (int next:nexts.get(cur)) {
                    results[next] = Math.max(results[next],results[cur]+buildings[next]);
                    indegrees[next]--;
                    if (indegrees[next] == 0) {
                        nextq.add(next);
                    }
                }
            }
            System.out.println(results[target]);
        }
    }
}
