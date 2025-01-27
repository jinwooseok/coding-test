import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9019 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    for (int i = 0; i < N; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      System.out.println(bfs(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
    }
  }

  public static StringBuilder bfs(int start, int end) {
    char[] COMMANDS = {'D', 'S', 'L', 'R'};
    Queue<SequenceSet> q = new LinkedList<>();
    q.add(new SequenceSet(new StringBuilder(), start));
    String str = "";
    boolean[] visited = new boolean[10001];
    while (!q.isEmpty()) {
      SequenceSet sequenceSet = q.poll();
      str = sequenceSet.commands.toString();
      int value = sequenceSet.n;
      int result = -1;
      for (char command : COMMANDS) {
        switch (command) {
          case 'D':
            result = (value * 2) % 10000;
            break;
          case 'S':
            result = (value == 0) ? 9999 : value - 1;
            break;
          case 'L':
            result = (value % 1000) * 10 + value / 1000;
            break;
          case 'R':
            result = (value % 10) * 1000 + value / 10;
            break;
        }
        StringBuilder next = new StringBuilder();
        next.append(str).append(command);
        if (result == end) {
          return next;
        }

        if (visited[result]) {
          continue;
        }
        visited[result] = true;
        q.add(new SequenceSet(next, result));
      }
    }
    return new StringBuilder(str);
  }

  public static class SequenceSet {

    StringBuilder commands;
    int n;

    SequenceSet(StringBuilder commands, int n) {
      this.commands = commands;
      this.n = n;
    }
  }
}
