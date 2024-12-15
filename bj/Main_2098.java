import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2098 {
    static int total = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] adjArr = new int[N][N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++) {
                adjArr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //각 도시를 어떤 상태에서 도달했는지 memo
        int[][] dp = new int[N][1<<N];
        for (int i=0;i<N;i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        //0번부터 시작.
        dp[0][1] = 0;
        dfs(adjArr, dp, 0, 0, 1, N);
        System.out.println(total);
    }
    static void dfs(int[][] adjArr, int[][] dp, int cur, int curWeight, int visited, int N) {
        //다방문한 경우+처음위치로 돌아오는 경우의 최소를 저장 후 리턴
        if (visited == (1<<N)-1 && adjArr[cur][0] != 0) {
            total = Math.min(total,curWeight+adjArr[cur][0]);
            return;
        }
        for (int i=0;i<N;i++) {
            //이전번째 방문한적이 있거나 dp배열에서 더 크거나 같은 weight를 갖고있는 경우는 패스. dfs 특성상 이전에 진행했던 정보를 갖고있기 때문

            if ((visited&(1<<i))!= 0 || adjArr[cur][i] == 0 || dp[i][visited|(1<<i)] <= curWeight+adjArr[cur][i]) {
                continue;
            } else {
                dp[i][visited] = curWeight+adjArr[cur][i];
                dfs(adjArr, dp, i, curWeight+adjArr[cur][i], visited|(1<<i), N);
            }
        }
    }
}
