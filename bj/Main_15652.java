import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15652 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] visited = new int[m];
        combination(visited, 0, n, 0, m);
    }

    public static void combination(int[] visited, int startIndex, int n, int cnt, int r){
        //숫자 m개 추출
        if (cnt==r){
            StringBuilder sb;
            sb = new StringBuilder();
            for (int j : visited) {
                sb.append(j);
                sb.append(" ");
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(sb);
            return;
        }

        for (int i=startIndex;i<n;i++){
            visited[cnt] = i+1;
            combination(visited, i, n,cnt+1,r);
        }
    }
}
