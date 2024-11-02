import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_4153 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> edges = new PriorityQueue<>();
        while (true) {
            st = new StringTokenizer(br.readLine());
            for (int i=0;i<3;i++) {
                edges.add(Integer.parseInt(st.nextToken()));
            }
            if (edges.peek() == 0) {
                break;
            }
            int a = edges.poll();
            int b = edges.poll();
            int c = edges.poll();
            if (Math.pow(a,2)+Math.pow(b,2) == Math.pow(c,2)) {
                sb.append("right");
            } else {
                sb.append("wrong");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
