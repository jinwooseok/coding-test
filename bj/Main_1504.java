import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1504 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    //다익스트라 2번?
    int N = Integer.parseInt(st.nextToken());
    int E = Integer.parseInt(st.nextToken());
    Node[] nodes = new Node[N+1];
    for (int i=0;i<N+1;i++) {
      nodes[i] = new Node(i,0);
    }

    for (int i=0;i<E;i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());

      nodes[start].next.add(new Node(end, weight));
      nodes[end].next.add(new Node(start, weight));
    }

    st = new StringTokenizer(br.readLine());
    int mid1 = Integer.parseInt(st.nextToken());
    int mid2 = Integer.parseInt(st.nextToken());

    int result1 = Integer.MAX_VALUE;
    int result2 = Integer.MAX_VALUE;

    //시작지점 to 경유지 둘 중 하나
    int startToMid1 = dijkstra(1, mid1, nodes, N+1);
    int startToMid2 = dijkstra(1, mid2, nodes, N+1);
    int mid1ToMid2 = dijkstra(mid1, mid2, nodes, N+1);
    int mid1ToEnd = dijkstra(mid1, N, nodes, N+1);
    int mid2ToEnd = dijkstra(mid2, N, nodes, N+1);
    if (mid1ToMid2 == -1) {
      System.out.println(-1);
    } else {
      if (startToMid1 != -1 && mid2ToEnd != -1) {
        result1 = startToMid1+mid1ToMid2+mid2ToEnd;
      }
      if (startToMid2 != -1 && mid1ToEnd != -1) {
        result2 = startToMid2+mid1ToMid2+mid1ToEnd;
      }
      int result = Math.min(result1, result2);

      if (result == Integer.MAX_VALUE) {
        System.out.println(-1);
      } else {
        System.out.println(result);
      }
    }
  }

  public static int dijkstra(int start, int end, Node[] adjList, int N) {
    int[] visited = new int[N+1];
    PriorityQueue<Node> pq = new PriorityQueue<>();
    Arrays.fill(visited, Integer.MAX_VALUE);
    pq.add(adjList[start]);
    visited[start] = 0;
    while (!pq.isEmpty()) {
      //System.out.println(pq);
      Node curNode = pq.poll();
      if (curNode.idx == end) {
        return visited[end];
      }

      for (Node node:adjList[curNode.idx].next) {
        if (visited[node.idx] > visited[curNode.idx]+node.weight) {
          visited[node.idx] = visited[curNode.idx]+node.weight;
          pq.add(new Node(node.idx, visited[node.idx]));
        }
      }
    }
    return -1;
  }

  public static class Node implements Comparable<Node> {
    int idx;
    List<Node> next;
    int weight;

    Node(int idx, int weight) {
      this.idx = idx;
      this.weight = weight;
      this.next = new ArrayList<>();
    }

    @Override
    public int compareTo(Node o) {
      return Integer.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
      return this.weight+ " " + this.idx;
    }
  }
}
