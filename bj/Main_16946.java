import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16946 {
    static int[][] MOVE1 = {{0,-1},{-1,0}};
    static int[][] MOVE2 = {{0,-1},{-1,0},{0,1},{1,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] arr = new char[N][M];
        int[][] result = new int[N][M];
        for (int i=0;i<N;i++) {
            arr[i] = br.readLine().toCharArray();
        }
        int[] jointSet = new int[N*M];
        for (int i=0;i<N*M;i++) {
            jointSet[i] = i;
        }
        for (int i=0;i<N;i++) {
            for (int j=0;j<M;j++) {
                if (arr[i][j]-'0'==0) {
                    for (int[] move:MOVE1) {
                        int nr = i+move[0];
                        int nc = j+move[1];
                        if (nr<0 || nr>=N || nc<0 || nc>=M || arr[nr][nc]=='1') {
                            continue;
                        }
                        union(jointSet, i*M+j, nr*M+nc);
                    }
                }
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0;i<N*M;i++) {
            int parent = find(jointSet, jointSet[i]);
            if (map.containsKey(parent)) {
                map.put(parent,map.get(parent)+1);
            } else {
                map.put(parent, 1);
            }

        }
        Set<Integer> set;
        for (int i=0;i<N;i++) {
            for (int j=0;j<M;j++) {
                if (arr[i][j]-'0'==1) {
                    set = new HashSet<>();
                    for (int[] move:MOVE2) {
                        int nr = i+move[0];
                        int nc = j+move[1];
                        if (nr<0 || nr>=N || nc<0 || nc>=M || arr[nr][nc]=='1') {
                            continue;
                        }
                        set.add(find(jointSet, nr*M+nc));
                    }
                    for (int num:set) {
                        result[i][j]+=map.get(num)%10;
                    }
                    result[i][j]++;
                    result[i][j]%=10;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0;i<N;i++){
            for (int j=0;j<M;j++) {
                sb.append(result[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    public static int find(int[] parents, int x) {
        if (parents[x] == x) {
            return parents[x];
        }
        return parents[x] = find(parents, parents[x]);
    }
    public static void union(int[] parents, int a, int b) {
        int aParent = find(parents, a);
        int bParent = find(parents, b);
        if (aParent != bParent) {
            parents[bParent] = aParent;
        }
    }
}
