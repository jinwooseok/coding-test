import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution_10966 {
    /*
        물 : W, 땅 : L
        상하좌우로 이동.
        땅에서 물인 칸으로 이동하기 위한 최소 이동 횟수

        1. 땅을 DP로 해놓는다.
        2. 물에서 모든 땅을 탐색한다.
        3. 갱신.
        BFS로 풀까 생각했는데 더 오래걸릴 것 같다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t=1;t<=T;t++) {
            st = new StringTokenizer(br.readLine()," ");
            int N=Integer.parseInt(st.nextToken()); int M=Integer.parseInt(st.nextToken());
            char[][] arr = new char[N][M];
            Deque<int[]> deque = new ArrayDeque<>();
            for (int i=0;i<N;i++){
                String str = br.readLine().trim();
                for (int j=0;j<M;j++){
                    arr[i][j] = str.charAt(j);
                    if (arr[i][j]=='W') {
                        deque.add(new int[]{i,j});
                    }
                }
            }
            int[][] dist = new int[N][M];
            int[][] MOVE = {{0,1},{0,-1},{1,0},{-1,0}};
            while (!deque.isEmpty()) {
                int[] rc=deque.pollFirst();
                for (int[] move: MOVE) {
                    int next_r = move[0]+rc[0]; int next_c = move[1]+rc[1];
                    if (next_r<0||next_c<0||next_r>=arr.length||next_c>=arr[0].length) continue;
                    if (dist[next_r][next_c]!=0 || arr[next_r][next_c]=='W') continue;
                    else dist[next_r][next_c] = dist[rc[0]][rc[1]]+1;
                    deque.addLast(new int[]{next_r,next_c});
                }
            }

//            //dp 생성
//            int[][] dp = new int[N][M];
//            for (int i=0;i<N;i++){
//                Arrays.fill(dp[i],Integer.MAX_VALUE);
//            }
//            //개수 세기
//            for (int[] water:waters){
//                for (int i=0;i<N;i++){
//                    for (int j=0;j<M;j++) {
//                        if (arr[i][j]=='W') {
//                            dp[i][j] = 0;
//                            continue;
//                        }
//
//                        dp[i][j] = Math.min(dp[i][j], Math.abs(i-water[0])+Math.abs(j-water[1]));
//                    }
//                }
//            }
            //최종개수세기
            int sum = 0;
            for (int i=0;i<N;i++){
                for (int j=0;j<M;j++) {
                    sum+=dist[i][j];
                }
            }
            sb.append("#").append(t).append(" ").append(sum).append("\n");
        }
        System.out.println(sb);
    }
}
