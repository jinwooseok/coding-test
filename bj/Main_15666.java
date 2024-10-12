import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_15666 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        Map<Integer, Integer> map = new TreeMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            map.putIfAbsent(arr[i],0);
            map.put(arr[i],map.get(arr[i])+1);
        }

        perm(map, new int[M], 0, M);
    }
    static void perm(Map<Integer,Integer> map, int[] result, int depth, int M) {
        if (depth == M) {
            StringBuilder sb = new StringBuilder();
            for (int num:result) {
                sb.append(num).append(" ");
            }
            System.out.println(sb);
            return;
        }
        for (int num:map.keySet()) {
            if (depth==0 ||  result[depth-1]<=num) {
                result[depth] = num;
                perm(map, result, depth+1, M);
            }
        }
    }
}
