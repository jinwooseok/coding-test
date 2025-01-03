import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_13334 {
  /*
  정해진 공간 안에 얼마나 많은 걸 넣을 수 있냐? 그리디?
   */
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int N = Integer.parseInt(br.readLine());
    PriorityQueue<Line> endpq = new PriorityQueue<>();
    int maxCnt = 0;
    int cnt = 0;
    for (int i=0;i<N;i++) {
      st = new StringTokenizer(br.readLine());
      int start = Integer.parseInt(st.nextToken());
      int end = Integer.parseInt(st.nextToken());
      endpq.add(new Line(Math.min(start, end),Math.max(start, end)));
    }
    int L = Integer.parseInt(br.readLine());
    PriorityQueue<Line> startpq = new PriorityQueue<>(
        Comparator.comparingInt(o -> o.start)
    );
    while (!endpq.isEmpty()) {
      Line line = endpq.poll();
      //애초에 못담는건 넘어가기
      if (line.dist>L) {
        continue;
      }
      while (!startpq.isEmpty()) {
        Line target = startpq.peek();
        if (target.start < line.end-L) {
          startpq.poll();
          cnt--;
        } else {
          break;
        }
      }
      cnt++;
      startpq.add(line);
      maxCnt = Math.max(cnt, maxCnt);
    }
    System.out.println(maxCnt);
  }
  static class Line implements Comparable<Line> {
    int start;
    int end;
    int dist;

    Line(int start, int end) {
      this.start = start;
      this.end = end;
      this.dist = end-start;
    }

    @Override
    public int compareTo(Line o) {
      int result =  Integer.compare(this.end, o.end);
      if (result == 0) {
        return Integer.compare(this.dist, o.dist);
      }
      return result;
    }
  }
}
