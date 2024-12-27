import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1753 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        Map<Integer, List<Node>> graph = new HashMap<>();
        for (int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            graph.putIfAbsent(key, new ArrayList<>());
            graph.get(key).add(new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dp = new int[V+1];
        boolean[] visited = new boolean[V+1];
        pq.add(new Node(K,0));
        for (int i=0;i<V+1;i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        dp[K] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (visited[node.num]) {
                continue;
            }
            visited[node.num] = true;
            //System.out.println(node.num);
            if (graph.containsKey(node.num)) {
                for (Node next:graph.get(node.num)) {
                    if (dp[next.num] > dp[node.num]+next.weight) {
                        dp[next.num] = Math.min(dp[next.num], dp[node.num]+next.weight);
                        pq.add(new Node(next.num, dp[next.num]));
                    }
                }
            }
        }
        //System.out.println(Arrays.toString(dp));
        for (int i=1;i<V+1;i++) {
            if (dp[i] < Integer.MAX_VALUE) {
                System.out.println(dp[i]);
            } else {
                System.out.println("INF");
            }
        }
    }
    static class Node implements Comparable<Node> {
        int num;
        int weight;

        Node(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight-o.weight;
        }
    }
}
