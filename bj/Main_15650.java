import java.util.*;
import java.io.*;
public class Main_15650 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[n];
        List<boolean[]> result = new ArrayList<>();
        combination(result, visited, 0, n, m);
    }

    public static void combination(List<boolean[]> result, boolean[] visited, int startIndex, int n, int m){
        //숫자 m개 추출
        if (m==0){
            StringBuilder sb;
            sb = new StringBuilder();
            for (int i=0;i<visited.length;i++){
                if (visited[i]){
                    sb.append(i+1);
                    sb.append(" ");
                }
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb);
            return;
        }
        if(startIndex == n) {
            return;
        } else {
            visited[startIndex] = true;
            combination(result, visited, startIndex+1, n, m-1);

            visited[startIndex] = false;
            combination(result, visited, startIndex+1, n, m);
        }
    }
}
