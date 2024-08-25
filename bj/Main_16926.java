import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16926 {
    /*
     * 회전 알고리즘
     * n번 돌렸을 때 외부 - 내부 는 제자리
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<M;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int startR = 0; //시작점 세팅
        int startC = 0;
        while (N*M != 0) {
            int cycleCnt = N*M-(N-2)*(M-2); // 가장 외곽 사이클 횟수. 즉, 가장 외곽을 한바퀴 돌리려면 몇번 돌려야하는가?
            int rotateCnt = R%cycleCnt; //1000번이더라도 cycleCnt로 나눠서 조금만 돌리게 함
            rotate(arr, cycleCnt+1, startR++, startC++, rotateCnt);
            N-=2; //다음사이클 준비
            M-=2;
        }
        for (int i=0;i<arr.length;i++) {
            for (int j=0;j<arr[0].length;j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
    //대충 배열회전 알고리즘임
    private static void rotate(int[][] arr, int cycleCnt, int startR, int startC, int R) {
        int[][] MOVE = {{1,0},{0,1},{-1,0},{0,-1}};
        int moveNum = 0;
        int curR = startR;
        int curC = startC;
        int tmp = arr[startR][startC];
        for (int j=0;j<R;j++) {	// 한바퀴를 몇번 돌릴 것인지?
            for (int i=0;i<cycleCnt;i++) { //한바퀴를 돌림?
                if (curR+MOVE[moveNum][0]<startR || curC+MOVE[moveNum][1]<startC || curR+MOVE[moveNum][0]>=arr.length-startR || curC+MOVE[moveNum][1]>=arr[0].length-startR) {
                    moveNum = (moveNum+1)%4;
                }
                curR+=MOVE[moveNum][0];
                curC+=MOVE[moveNum][1];
                int cur = arr[curR][curC];
                arr[curR][curC] = tmp;
                tmp = cur;
            }
        }
    }
}
