import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.*;

public class Main_1197 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>();
        Map<Integer, List<Node>> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();
        for (int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            map.putIfAbsent(a, new ArrayList<>());
            map.get(a).add(new Node(b,weight));
            map.putIfAbsent(b, new ArrayList<>());
            map.get(b).add(new Node(a,weight));
        }
        int total = 0;
        pq.add(new Node(1,0));
        while (!pq.isEmpty() && set.size() < V) {
            Node node = pq.poll();
            int num = node.num;
            if (set.contains(num)) {
                continue;
            }
            set.add(num);
            total+=node.weight;
            for (Node next:map.get(num)) {
                if (set.contains(next.num)) {
                    continue;
                }
                pq.add(next);
            };
        }
        System.out.println(total);
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
