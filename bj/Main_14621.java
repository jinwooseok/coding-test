import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_14621 {
    /*
    1. 남초 대학과 여초 대학을 연결하는 도로로만 이뤄짐
    2. 모든 대학교로 이동이 가능
    3. 경로의 길이가 최단 거리가 되어야 함
    즉, 남여남여남 이런식으로 되어야 함
     */
    static int N, M, parents[], info[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        //노드 정보 받기
        info = new int[N+1];
        for (int i=1;i<N+1;i++) {
            if (st.nextToken().equals("M")) {
                info[i] = 0;
            } else {
                info[i] = 1;
            }
        }
        //간선 정보 받기
        PriorityQueue<Edge> edges = new PriorityQueue<>();
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            Edge edge = new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));

            if (info[edge.school1]!=info[edge.school2]) {
                edges.add(edge);
            }
        }
        //union-find 준비
        parents = new int[N+1];
        for (int i=1;i<N+1;i++) {
            parents[i]=i;
        }
        //최소 간선부터 찾기 시작
        int edgeCnt = 0;
        int edgeWeight = 0;
        while (!edges.isEmpty()) {
            if (edgeCnt==N-1) {
                break;
            }
            Edge edge = edges.poll();
            if (union(edge.school1,edge.school2)) {
                edgeWeight+=edge.dist;
                edgeCnt++;
            }
        }
        if (edgeCnt!=N-1) {
            System.out.println(-1);
        } else {
            System.out.println(edgeWeight);
        }
    }
    private static class Edge implements Comparable<Edge> {
        int school1;
        int school2;
        int dist;
        Edge(int school1, int school2, int dist) {
            this.school1 = school1;
            this.school2 = school2;
            this.dist = dist;
        }

        @Override
        public int compareTo(Edge e) {
            return this.dist-e.dist;
        }
    }

    private static boolean union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if (parentA == parentB) {
            return false;
        }
        parents[parentA] = parentB;
        return true;
    }
    private static int find(int x) {
        if (parents[x]==x) {
            return parents[x];
        }
        return parents[x]=find(parents[x]);
    }
}
