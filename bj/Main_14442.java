import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_14442 {
    public static void main(String[] args) throws IOException {
        /*
        문제 : 벽부수고 이동하기
        단 벽을 부수는 것이 k개까지 가능함
         */
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int rowSize = Integer.parseInt(st.nextToken());
        int colSize = Integer.parseInt(st.nextToken());
        int maxBroken = Integer.parseInt(st.nextToken());
        char[][] arr = new char[rowSize][colSize];
        for (int i=0;i<rowSize;i++){
            String row = br.readLine();
            arr[i]=row.toCharArray();
        }
        //bfs 진행하기
        System.out.println(bfs(arr, rowSize, colSize, maxBroken));
    }

    private static int bfs(char[][] arr, int rowSize, int colSize, int maxBroken) {
        //deque 불러오기
        Deque<Status> deque = new ArrayDeque<>();
        //거리를 저장할 dist배열 생성 dist[row위치][col위치][broken의 크기]
        int[][][] dist = new int[rowSize][colSize][maxBroken+1];
        //delta 선택
        int[][] MOVE = {{0,1},{0,-1},{1,0},{-1,0}};
        //deque에 삽입
        deque.addLast(new Status(0,0,0));
        //bfs 진행
        while (!deque.isEmpty()) {
            //poll
            Status curStatus = deque.pollFirst();
            //만약 현재위치가 끝인 경우 return
            if (curStatus.r==rowSize-1 && curStatus.c==colSize-1) {
                return dist[rowSize - 1][colSize - 1][curStatus.broken];
            }
            //그렇지 않음 경우 다음 위치 찾기 진행
            for (int[] move:MOVE) {
                int nextR = curStatus.r+move[0];
                int nextC = curStatus.c+move[1];
                //만약 다음 위치가 배열 밖인 경우 continue
                if (nextR<0||nextR>=rowSize||nextC<0||nextC>=colSize) continue;

                //만약 다음 벽이 1인 경우
                if (arr[nextR][nextC]=='1'){
                    //만약 부순횟수가 최대치를 초과할 경우
                    if (curStatus.broken<maxBroken) {
                        //만약 다음 위치가 방문한 적 있을 경우
                        if (dist[nextR][nextC][curStatus.broken+1]==0){
                            dist[nextR][nextC][curStatus.broken+1] = dist[curStatus.r][curStatus.c][curStatus.broken]+1;
                            deque.addLast(new Status(nextR, nextC, curStatus.broken+1));
                        }
                    }
                } else {
                    if (dist[nextR][nextC][curStatus.broken]==0) {
                        dist[nextR][nextC][curStatus.broken] = dist[curStatus.r][curStatus.c][curStatus.broken]+1;
                        deque.addLast(new Status(nextR, nextC, curStatus.broken));
                    }
                }
            }
        }
        return -1; //불가능할 경우 return
    }

    static class Status{
        // int r, int c, int breaked
        int r;
        int c;
        int broken;
        Status(int r, int c, int broken) {
            this.r = r;
            this.c = c;
            this.broken = broken;
        }
    }
}
