import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_9466 {
  /*
  텀 프로젝트 팀원 선택하기
  1. 혼자하기 가능
  팀과 함께하고 싶다면 선택하기 + 선택받기가 이뤄져야함
  자신은 선택했는데 선택을 못받았다면 안됨
  무조건 원형이 되어야 한다는 뜻
   */
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int T = Integer.parseInt(br.readLine());
    StringBuilder sb = new StringBuilder();
    for (int t=0;t<T;t++) {
      int N = Integer.parseInt(br.readLine());
      int[] arr = new int[N+1];
      st = new StringTokenizer(br.readLine());

      for (int i=1;i<=N;i++) {
        arr[i] = Integer.parseInt(st.nextToken());
      }
      int result = 0;
      boolean[] visited = new boolean[N+1];
      Queue<Integer> q = new LinkedList<>();
      for (int i=1;i<=N;i++) {
        if (!visited[i]) {
          q.add(i);
          visited[i] = true;
          int current = arr[i];
          while (current != arr[current] && !visited[current]) {
            q.add(current);
            visited[current] = true;
            current = arr[current];
          }
          boolean cycleStartVisit = false;
          while (!q.isEmpty()) {
            int point= q.poll();
            if (current == point) {
              cycleStartVisit = true;
            }
            if (!cycleStartVisit) {
              result++;
            }
          }
        }
      }
      sb.append(result).append("\n");
    }
    System.out.println(sb);
  }

}
