import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_15654 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] input = new int[n];
        boolean[] visited = new boolean[n];
        for (int i=0;i<n;i++){
            input[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(input);
        combination(input, new int[m],visited,n, m, 0, 0);
    }

    public static void combination(int[] input, int[] output, boolean[] visited, int n, int m, int startIndex, int depth){
        //숫자 m개 추출
        if (depth==m){
            StringBuilder sb;
            sb = new StringBuilder();
            for (int j : output) {
                sb.append(j);
                sb.append(" ");
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb);
            return;
        } else {
            for (int i=0;i<n;i++){
                if (!visited[i]) {
                    output[depth] = input[i];
                    visited[i] = true;
                    combination(input, output, visited, n, m, i+1, depth+1);
                    visited[i] = false;
                }
            }
        }
    }
}
