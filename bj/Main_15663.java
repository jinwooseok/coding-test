import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_15663 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Set<Integer> set = new TreeSet<>();
        for (int i=0;i<n;i++){
            set.add(Integer.parseInt(st.nextToken()));
        }
        Integer[] integerArray = set.toArray(new Integer[set.size()]);
        int[] result = new int[m];
        permutation(integerArray, result, integerArray.length, m, 0, 0);
    }

    static void permutation(Integer[] arr, int[] result, int n, int m, int start, int depth) {
        if (depth == m) {
            StringBuilder sb = new StringBuilder();
            for (int i=0;i<m;i++){
                sb.append(result[i]);
                sb.append(" ");
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb);
            return;
        }
        for (int i=start;i<n;i++){
            result[depth] = arr[i];
            permutation(arr, result, n, m, start, depth+1);
        }
    }
}
