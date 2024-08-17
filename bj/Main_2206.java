import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;
import java.util.Deque;
public class Main_2206 {
    /*
    문제
    n,m의 행렬이 주어짐
    0 : 이동할 수 있는 곳
    1 : 이동할 수 없는 벽
    (1,1) => (N,M)으로 이동할 때 최단경로로 이동
    단! 시작과 끝을 포함해 센다.
    벽을 부술 수 있는데 최대 한개까지 부술 수 있다.
    1,1은 0,0 임.

    입력값
    1. N(끝행+1), M(끝열+1)
    2. 띄어쓰기 없이 행렬을 한줄씩 입력

    설계
    1. 이동
    2. 끝지점인지 판단. 벗어났으면 RETURN
        - move(arr)
        - bfs로 해결할 예정 dfs는 최대 모든 요소를 돌아야하므로 생략
        - queue
        - State (r,c,isBreak)
        - return 최단거리
    */
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        //입력
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] arr = new char[N][M];

        for (int i=0;i<N;i++){
            String rowString = br.readLine();
            for (int j=0;j<M;j++){
                arr[i][j] = rowString.charAt(j);
            }
        }

        System.out.println(move(arr, N, M));
    }

    private static int move(char[][] arr, int arriveR, int arriveC) {
        int[][] MOVE = {{0,1},{1,0},{0,-1},{-1,0}};
        int arriveRIndex = arriveR-1;
        int arriveCIndex = arriveC-1;
        //deque생성
        Deque<State> deque = new ArrayDeque<>();
        //deque의 시작지점 삽입
        deque.addLast(new State(0, 0, 0, 0));
        //visited 생성. 이미 도착한 적 있다면 그뒤로는 같으므로 제외. 단 벽 뿌 전과 후는 다를 수 있음
        boolean[][][] visited = new boolean[arriveR][arriveC][2];
        //bfs 시작
        while (!deque.isEmpty()){
            //deque에서 꺼내기
            State curState = deque.pollFirst();
            //현재 위치가 도착인 경우 return
            if (curState.r==arriveRIndex && curState.c==arriveCIndex){
                return curState.moveCnt+1;
            }
            //방문한적 있다면 continue
            if (visited[curState.r][curState.c][curState.isBreak]) {
                continue;
            } else {
                visited[curState.r][curState.c][curState.isBreak] = true;
            }
            //이동경로 탐색
            for (int[] moveRC:MOVE){
                int nextR = curState.r+moveRC[0];
                int nextC = curState.c+moveRC[1];
                //다음위치가 맵을 벗어난 경우 continue
                if (nextR<0 || nextR>arriveRIndex || nextC<0 || nextC>arriveCIndex) {
                    continue;
                }
                //벽이 있나?
                if (arr[nextR][nextC] == '1') {
                    //벽이라면 isBreak를 체크해봄
                    //이미 true이면 continue
                    //true가 아니라면 벽을 부수고 이동
                    if (curState.isBreak == 0) {
                        deque.addLast(new State(nextR, nextC, curState.moveCnt + 1, 1));
                    }
                }
                //벽이 없으면 진행
                else {
                    deque.addLast(new State(nextR, nextC, curState.moveCnt + 1, curState.isBreak));
                }
            }
        }
        return -1; //불가능한 경우
    }

    static class State {
        int r;
        int c;
        int moveCnt;
        int isBreak;
        public State(int r, int c, int moveCnt, int isBreak) {
            this.r = r;
            this.c = c;
            this.moveCnt = moveCnt;
            this.isBreak = isBreak;
        }
    }
}