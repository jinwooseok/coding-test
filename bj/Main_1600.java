import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_1600 {
    /*
    체스에서 말의 움직임을 따라한다.
    k번 8방향 말의 움직임 사용 가능
    그외엔 상하좌우
    시작지점 : 0,0
    끝지점 : N-1,M-1

    입력값
    1. 가로길이 width, 세로길이 height
    2. 배열을 띄어쓰기로 입력받음

    로직설계
    bfs로 가능할 듯 하다
    1. State(int curR, int curC, int horseForm)
    2. dist:int[가로width][세로height][horseForm 총 0~8번] (horseForm에 따라 상황이 바뀔 수 있기 때문)
    3. bfs(arr)
        - 1. deque의 시작 state삽입
        - 2. bfs 시작
        - 3. poll
        - 4. for문 시작
        - 5. 다음 위치가 벗어날 경우 continue
        - 6. 다음 위치와 dist를 비교해서 방문한적 있는 상태라면 continue
        - 7. 만약 horseForm횟수가 K번 아래라면 horseForm과 그냥 가는 경우 모두 큐에 담음
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int horseFormMax = Integer.parseInt(br.readLine().trim());
        //1. 가로길이 width, 세로길이 height
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        //2. 배열을 띄어쓰기로 입력받음
        int[][] arr = new int[height][width];
        for (int i=0;i<height;i++){
            st = new StringTokenizer(br.readLine().trim());
            for (int j=0;j<width;j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs(arr, width, height, horseFormMax));
    }
    //3. bfs(arr)
    private static int bfs(int[][] arr, int width, int height, int horseFormMax) {
        //1. dist:int[가로width][세로height][horseForm 총 0~8번] (horseForm에 따라 상황이 바뀔 수 있기 때문)
        int[][][] dist = new int[height][width][horseFormMax+1];
        int[][] DEFAULT_MOVE = {{0,1},{1,0},{0,-1},{-1,0}};
        int[][] HORSE_MOVE = {{-1,-2},{-1,2},{1,-2},{1,2},{-2,-1},{-2,1},{2,-1},{2,1}};
        //2. deque의 시작 state삽입
        Deque<State> deque = new ArrayDeque<>();
        deque.addLast(new State(0,0,0));

        //3. bfs 시작
        while (!deque.isEmpty()){
            //4. poll
            State curState = deque.pollFirst();
            //도착했을 경우 return;
            if (curState.curR == height-1 && curState.curC == width-1) {
                return dist[curState.curR][curState.curC][curState.horseFormCnt];
            }
            //for문 시작
            for (int[] move:DEFAULT_MOVE) {
                int nextR = curState.curR+move[0];
                int nextC = curState.curC+move[1];
                //5. 다음 위치가 벗어날 경우 continue;
                if (nextR<0 || nextR>=height ||nextC<0 || nextC>=width || arr[nextR][nextC] == 1) continue;
                //6. 일반 이동. 0이 아니면 방문 전적 있음.
                if (dist[nextR][nextC][curState.horseFormCnt]!=0) {
                    continue;
                }
                dist[nextR][nextC][curState.horseFormCnt] = dist[curState.curR][curState.curC][curState.horseFormCnt]+1;
                deque.addLast(new State(nextR, nextC, curState.horseFormCnt));
            }
            //for문 시작 - horse move를 n번이상 사용했으면 하지마라
            if (curState.horseFormCnt>=horseFormMax){
                continue;
            }
            for (int[] move:HORSE_MOVE) {
                int nextR = curState.curR+move[0];
                int nextC = curState.curC+move[1];
                //5. 다음 위치가 벗어날 경우 continue;
                if (nextR<0 || nextR>=height ||nextC<0 || nextC>=width || arr[nextR][nextC] == 1) continue;
                //6. 말 이동. 0이 아니면 방문 전적 있음.
                if (dist[nextR][nextC][curState.horseFormCnt+1]!=0) {
                    continue;
                }
                dist[nextR][nextC][curState.horseFormCnt+1] = dist[curState.curR][curState.curC][curState.horseFormCnt]+1;
                deque.addLast(new State(nextR, nextC, curState.horseFormCnt+1));
            }
        }
        return -1;
    }
    static class State {
        int curR;
        int curC;
        int horseFormCnt;
        State(int curR, int curC, int horseFormCnt) {
            this.curR = curR;
            this.curC = curC;
            this.horseFormCnt = horseFormCnt;
        }
        @Override
        public String toString() {
            return this.curR + " " + this.curC + " " + this.horseFormCnt;
        }
    }
}
