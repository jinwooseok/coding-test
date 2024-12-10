import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10775 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        int P = Integer.parseInt(br.readLine());
        int[] gates = new int[G];
        boolean[] visited = new boolean[G];
        for (int i=0;i<G;i++) {
            gates[i] = i;
        }
        int cnt = 0;
        for (int i=0;i<P;i++) {
            int p = Integer.parseInt(br.readLine())-1;
            int parent = find(gates, p);
            if (visited[parent]) {
                break;
            }

            visited[parent] = true;
            cnt++;
            if (p != 0) {
                if (parent != 0) {
                    union(gates, parent, parent-1);
                }
            }

        }
        System.out.println(cnt);
    }
    public static int find(int[] parents, int x) {
        if (parents[x] == x) {
            return x;
        } else {
            return parents[x] = find(parents, parents[x]);
        }
    }

    public static void union(int[] parents, int a, int b) {
        int aParent = find(parents, a);
        int bParent = find(parents, b);
        if (aParent < bParent) {
            parents[bParent] = aParent;
            find(parents, b);
        } else {
            parents[aParent] = bParent;
            find(parents, a);
        }
    }
}
