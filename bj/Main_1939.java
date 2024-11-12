import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1939 {
    /*
     * 몇개의 섬에만 다리가 설치되어 있음. 섬 최대 10000개
     * 공장은 두개의 섬에만 세워둠. 공장끼리 물품 수송
     * 다리마다 중량제한이 있음.
     * 공장에서 공장까지 가는데 최소가 최대가 되어야함?
     * 가중치가 큰것부터 나열해야 함.
     * M은 최대 100000. 다리 중량도 함께 주어짐.
     * 다익스트라
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<Integer, List<Edge>> adjList = new HashMap<>();
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            adjList.putIfAbsent(start, new ArrayList<>());
            adjList.putIfAbsent(end, new ArrayList<>());
            adjList.get(start).add(new Edge(end, dist));
            adjList.get(end).add(new Edge(start, dist));
        }
        st = new StringTokenizer(br.readLine());
        int factory1 = Integer.parseInt(st.nextToken());
        int factory2 = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[N+1];
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        edges.add(new Edge(factory1,0));
        int minWeight = Integer.MAX_VALUE;
        while (true) {
            Edge edge = edges.poll();
            if (edge.dist != 0) {
                minWeight = Math.min(edge.dist, minWeight);
            }
            if (!visited[edge.v]) {
                visited[edge.v] = true;
            } else {
                continue;
            }
            if (visited[factory2]) {
                break;
            }

            for (Edge e:adjList.get(edge.v)) {
                edges.add(e);
            }
        }

        System.out.println(minWeight);
    }
    static class Edge implements Comparable<Edge> {
        int v;
        int dist;

        public Edge(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge o) {
            return o.dist-this.dist;
        }
    }

}