import java.io.*;
import java.util.*;
public class Solution_7465 {
    /*
     * n명의 사람
     * 서로 아는 관계이거나 몇사람을 거쳐서 알수있는 관계 == 연결됨
     * find작업을 모두 거치고나서 다ㅏ른게 몇개가 있는지 알면 좋을듯
     */
    static int[] parents;
    static StringTokenizer st;
    static int N, M;
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t=1;t<T+1;t++) {
            sb.append("#"+t+" ");
            initTask(br);
            sb.append(mainTask(br)).append("\n");
        }
        System.out.println(sb);
    }

    private static void initTask(BufferedReader br) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //집합 초기화
        parents = new int[N+1];
        for (int i=1;i<N+1;i++) {
            parents[i] = i;
        }
    }

    private static int mainTask(BufferedReader br) throws IOException {
        //st로 받아오면서 바로 union작업하기
        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine()," ");
            union(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
        return countSet();
    }

    private static int find(int x) {
        if (parents[x]==x) return x;
        else return parents[x] = find(parents[x]);
    }
    private static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return;
        else parents[rootX] = rootY; return;
    }
    private static int countSet() {
        Set<Integer> set = new HashSet<>();
        for (int num:parents) {
            set.add(find(num));
        }
        //System.out.println(set);
        return set.size()-1;
    }
}