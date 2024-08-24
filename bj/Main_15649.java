import java.io.*;
import java.util.StringTokenizer;

public class Main_15649 {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        //N으로 받은 수를 순열을 통해 조건을 만족시킨다.
        permutation(N, new int[R], new boolean[N], 0, R);
    }

    public static void permutation(int num, int[] result, boolean[] visited, int depth, int r) {
        if (depth == r) {
            StringBuilder sb = new StringBuilder();
            for (int n:result) {
                sb.append(n);
                sb.append(" ");
            }
            System.out.println(sb);
        } else {
            for (int i=1;i<num+1;i++) {
                if (!visited[i-1]) {
                    result[depth] = i; // 배열 대신 i를 입력하여 만족하는 배열을 찾는다.
                    visited[i-1] = true;
                    permutation(num, result, visited, depth+1, r);
                    visited[i-1] = false;
                }
            }
        }
    }

}
