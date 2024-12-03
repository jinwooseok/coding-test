import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11404 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int[][] adjarr = new int[N][N];

        for (int i=0;i<N;i++) {
            Arrays.fill(adjarr[i],Integer.MAX_VALUE);
            adjarr[i][i] = 0;
        }

        for (int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            adjarr[r][c]=Math.min(Integer.parseInt(st.nextToken()), adjarr[r][c]);
        }

        for (int i=0;i<N;i++) { //경유
            for (int j=0;j<N;j++) { //시작
                if (i==j) continue;
                for (int k=0;k<N;k++) { //끝
                    if (i==k || j==k) continue;
                    if (adjarr[j][i]==Integer.MAX_VALUE || adjarr[i][k]==Integer.MAX_VALUE) {
                        continue;
                    }
                    adjarr[j][k] = Math.min(adjarr[j][k], adjarr[j][i]+adjarr[i][k]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<N;i++) {
            for (int j = 0; j < N; j++) {
                if (adjarr[i][j]==Integer.MAX_VALUE) {
                    sb.append(0).append(" ");
                } else {
                    sb.append(adjarr[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
