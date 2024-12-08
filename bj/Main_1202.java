import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        PriorityQueue<Jem> jems = new PriorityQueue<>();
        int[] bags = new int[K];

        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            Jem jem = new Jem(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            jems.add(jem);
        }
        for (int i=0;i<K;i++) {
            bags[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(bags);
        Queue<Integer> pq = new PriorityQueue<>((o1, o2) -> (o2-o1));
        long answer = 0;
        for (int i=0;i<K;i++) {
            //System.out.println(pq);
            while (!jems.isEmpty()) {
                Jem jem = jems.peek();
                if (bags[i] < jem.weight) break;
                pq.add(jems.poll().value);
            }
            if(!pq.isEmpty())
                answer += pq.poll();
        }
        System.out.println(answer);
    }

    public static class Jem implements Comparable<Jem> {
        int weight;
        int value;

        Jem(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }

        @Override
        public int compareTo(Jem o) {
            if(this.weight == o.weight)		//무게가 같을 때 가치 내림차순
                return o.value - this.value;
            return this.weight - o.weight;
        }
    }
}
