import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_14725 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    StringTokenizer st;
    Node root = new Node("", -1);
    for (int i=0;i<N;i++) {
      st = new StringTokenizer(br.readLine());
      int m = Integer.parseInt(st.nextToken());
      Node node = new Node(st.nextToken(), 0);
      root.children.putIfAbsent(node.word, node);
      node = root.children.get(node.word);
      for (int j=1;j<m;j++) {
        Node newNode = new Node(st.nextToken(), node.depth+1);
        node.children.putIfAbsent(newNode.word, newNode);
        node = node.children.get(newNode.word);
      }
    }
    StringBuilder sb = new StringBuilder();
    Stack<Node> q = new Stack<>();
    q.add(root);
    while (!q.isEmpty()) {
      Node curNode = q.pop();
      if (curNode.depth != -1) {
        sb.append("--".repeat(curNode.depth)).append(curNode.word).append("\n");
      }
      List<Node> children = new ArrayList<>(curNode.children.values());
      Collections.sort(children);
      q.addAll(children);
    }
    System.out.println(sb);
  }

  static class Node implements Comparable<Node> {
    String word;
    int depth;
    Map<String, Node> children;

    Node(String word, int depth) {
      this.word = word;
      this.depth = depth;
      this.children = new HashMap<>();
    }


    @Override
    public int compareTo(Node o) {
      return o.word.compareTo(this.word);
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (o == null || getClass() != o.getClass()) {
        return false;
      }
      Node node = (Node) o;
      return depth == node.depth && Objects.equals(word, node.word);
    }
  }
}
