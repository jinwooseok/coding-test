import java.io.*;
import java.util.*;
 
public class Main_1939 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    static int FROM, TO;
    static int N,M;
    static int[] weights;
    static int INF = 1_000_000_001;
    static List<Bridge>[] bridges;

    public static void main(String[] args) throws Exception {
        input();
        run();
        print();
    }
    
    static void run() throws Exception {
        PriorityQueue<Bridge> queue = new PriorityQueue<>();
        queue.offer(new Bridge(FROM, INF));
        weights[FROM] = INF;

        while(!queue.isEmpty()){
            Bridge now = queue.poll();
            if(now.weight < weights[now.idx])
                continue;

            for(Bridge to : bridges[now.idx]){
                int nWeight = Math.min(now.weight, to.weight);

                if(weights[to.idx] < nWeight){
                    weights[to.idx] = nWeight;
                    queue.offer(new Bridge(to.idx, nWeight));
                }
            }
        }
        // System.out.println(Arrays.toString(weights));
    }

    static void input() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        weights = new int[N+1];
        bridges = new List[N+1];

        for(int n = 1; n <= N; n++){
            bridges[n] = new ArrayList<>();
        }

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            bridges[from].add(new Bridge(to, weight));
            bridges[to].add(new Bridge(from, weight));
        }

        st = new StringTokenizer(br.readLine());
        FROM = Integer.parseInt(st.nextToken());
        TO = Integer.parseInt(st.nextToken());

    }
    
    static void print() throws Exception {
        System.out.println(weights[TO]);
    }

    static class Bridge implements Comparable<Bridge>{
        int idx, weight;

        public Bridge(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Bridge [idx=" + idx + ", weight=" + weight + "]";
        }

        @Override
        public int compareTo(Main.Bridge o) {
            return o.weight - this.weight;
        }

    }
}
