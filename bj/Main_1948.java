import java.io.*;
import java.util.*;

public class Main_1948 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<Node>[] graph = new ArrayList[N + 1];
        List<Node>[] reverseGraph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[start].add(new Node(end, weight));
            reverseGraph[end].add(new Node(start, weight));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // 1. 다익스트라로 최장 거리 계산
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MIN_VALUE);
        dist[start] = 0;

        dijkstra(graph, dist, start);

        // 2. 역추적을 통해 사용된 도로의 수 계산
        boolean[] visited = new boolean[N + 1];
        int count = backtrack(reverseGraph, visited, end, dist);

        System.out.println(dist[end]); // 최장 거리
        System.out.println(count);    // 사용된 도로의 수
    }

    static void dijkstra(List<Node>[] graph, int[] dist, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int curNode = cur.num;
            int curDist = cur.weight;

            if (curDist < dist[curNode]) continue;

            for (Node next : graph[curNode]) {
                if (dist[next.num] < dist[curNode] + next.weight) {
                    dist[next.num] = dist[curNode] + next.weight;
                    pq.add(new Node(next.num, dist[next.num]));
                }
            }
        }
    }

    static int backtrack(List<Node>[] reverseGraph, boolean[] visited, int curNode, int[] dist) {
        visited[curNode] = true;
        int count = 0;

        for (Node prev : reverseGraph[curNode]) {
            // 최장 경로에 포함된 도로만 역추적
            if (dist[curNode] == dist[prev.num] + prev.weight) {
                count++;
                if (!visited[prev.num]) {
                    count += backtrack(reverseGraph, visited, prev.num, dist);
                }
            }
        }

        return count;
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
            return Integer.compare(o.weight, this.weight); // Max-Heap
        }
    }
}
