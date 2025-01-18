import java.io.*;
import java.util.*;

public class Main_1967 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    int N = Integer.parseInt(br.readLine());
    Node[] nodes = new Node[N + 1];
    for (int i = 1; i <= N; i++) {
      nodes[i] = new Node(i, 0);
    }

    for (int i = 0; i < N - 1; i++) {
      st = new StringTokenizer(br.readLine());
      int parent = Integer.parseInt(st.nextToken());
      int child = Integer.parseInt(st.nextToken());
      int weight = Integer.parseInt(st.nextToken());
      nodes[parent].children.add(new Node(child, weight));
      nodes[child].parent = parent;
    }

    // DP 배열 및 초기화
    Queue<Integer> q = new LinkedList<>();
    int[][] dp = new int[N + 1][2];
    for (int i = 1; i <= N; i++) {
      Node node = nodes[i];
      if (node.children.isEmpty()) { // 리프 노드
        q.add(node.idx);
        dp[node.idx][0] = 0;
        dp[node.idx][1] = 0;
      }
    }

    int maxCnt = 0;
    while (!q.isEmpty()) {
      int current = q.poll();
      int parentIdx = nodes[current].parent;

      if (parentIdx == current) continue; // 루트 노드

      int max1 = 0, max2 = 0;
      for (Node child : nodes[parentIdx].children) {
        int weightWithChild = dp[child.idx][0] + child.weight;

        if (weightWithChild > max1) {
          max2 = max1;
          max1 = weightWithChild;
        } else if (weightWithChild > max2) {
          max2 = weightWithChild;
        }
      }

      dp[parentIdx][0] = max1;
      dp[parentIdx][1] = max2;

      // 트리의 지름 갱신
      maxCnt = Math.max(maxCnt, dp[parentIdx][0] + dp[parentIdx][1]);

      q.add(parentIdx);
    }

    System.out.println(maxCnt);
  }

  static class Node {
    int parent;
    int idx;
    int weight;
    List<Node> children;

    Node(int idx, int weight) {
      this.parent = idx;
      this.idx = idx;
      this.weight = weight;
      this.children = new ArrayList<>();
    }
  }
}
