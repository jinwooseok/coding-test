import java.io.*;
import java.util.*;
/*

대기 큐랑 계단 내부 큐 2개로 진행
시간은 while 관리 >> 현재 층에 사람도 없고 계단 내부도 모두 비어 있으면 종료
처음에 사람 위치 + 2개의 계단 까지의 소요시간 계산
사람 마다 어떤 계단을 이용할지는 순열 배열로 설정(최대 2^10)
*/
public class Solution_duckjin {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int T, N, arr[], human, min;
    static List<int[]> humanPositon, stairPosition;
    static Queue<Integer> wait1, wait2, stair1, stair2;

    public static void main(String[] args) throws Exception {
        T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine());

            humanPositon = new ArrayList<>();    //사람 위치 배열
            stairPosition = new ArrayList<>();    //계단 위치 + 값 배열
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int tmp = Integer.parseInt(st.nextToken());

                    //[2]: 1번 계단까지의 거리 [3]: 2번 계단까지의 거리
                    if(tmp == 1) humanPositon.add(new int[] {i,j,0,0});
                        //[2]: 계단의 깊이
                    else if(tmp > 1)stairPosition.add(new int[] {i,j,tmp});
                }
            }

            human = humanPositon.size();    //사람 명수

            for (int i = 0; i < human; i++) {
                for (int j = 0; j < 2; j++) {
                    int hx = humanPositon.get(i)[0];
                    int hy = humanPositon.get(i)[1];

                    int sx = stairPosition.get(j)[0];
                    int sy = stairPosition.get(j)[1];

                    int d = Math.abs(hx - sx) + Math.abs(hy - sy);    //거리(계단까지의 시간) 계산

                    humanPositon.get(i)[j+2] = d;
                }
            }

            wait1 = new LinkedList<>();        //1번 계단 대기인 관리 큐
            wait2 = new LinkedList<>();        //2번 계단 대기인 관리 큐
            stair1 = new LinkedList<>();    //1번 계단 내부 큐
            stair2 = new LinkedList<>();    //2번 계단 내부 큐
            arr = new int[human];
            min = Integer.MAX_VALUE;    //최소 시간 변수
            perm(0);
            System.out.println("#" + tc + " " + min);

        }

    }

    private static void perm(int idx) {
        if(idx == human) {    //사람별 계단 이용 배열 완성
            simulation(human);
            return;
        }

        for (int i = 0; i < 2; i++) {
            arr[idx] = i;    //중복이 가능하니 방문체크 안함
            perm(idx+1);
        }

    }

    private static void simulation(int remain) {
        int time = 0;

        //남아 있는 사람도 없어야 하고 계단에 사람도 없어야 하고 대기중인 사람도 없어야 함
        while(remain != 0 || stair1.size() != 0 || stair2.size() != 0 || wait1.size() != 0 || wait2.size() != 0) {
            time++;

            for (int i = 0; i < human; i++) {
                int[] h = humanPositon.get(i);
                //arr[i]에 i번째 사람이 어떤 계단을 이용하는지 저장되어 있고
                // +2 인덱스에 그 계단까지의 시간이 기록되어 있음
                int stairNum = arr[i];

                if(time != h[stairNum+2]) continue;    //이용 계단까지 안 왔거나 넘어갔으면 pass

                remain--;    //계단까지 갔으니까 남은 인원 -1 처리

                //계단 까지 왔으면 일단 대기 처리
                if(stairNum == 0) {
                    wait1.offer(time);
                }else {
                    wait2.offer(time);
                }
            }

            //계단에서 사람 나오는게 먼저
            while(!stair1.isEmpty()) {
                if(stair1.peek() == time) {    //계단 내부에 있는 사람이 빠져나올 시간이라면
                    stair1.poll();    //계단에서 빼내주기
                    continue;
                }else {    //제일 앞에 있는 사람이 아직 나올 때가 아니면 뒤에 있는 사람도 마찬가지
                    break;
                }
            }

            while(!stair2.isEmpty()) {
                if(stair2.peek() == time) {
                    stair2.poll();
                    continue;
                }else {
                    break;
                }
            }

            if(wait1.size() > 0) {    //대기 큐에 사람이 있다면
                while(stair1.size() < 3 && wait1.size() > 0) {    //계단 큐에 3명까지만 삽입
                    if(wait1.peek() < time) {    //계단 입구에 도착하면, 1분 후 아래칸으로 내려 갈 수 있다.
                        wait1.poll();
                        stair1.offer(time + stairPosition.get(0)[2]);    //현재 들어가는 시간 + 계단 소요시간
                        continue;
                    }else{    //대기중인 사람이 아직 계단에 들어갈 시간이 안됐다면 뒤도 마찬가지
                        break;
                    }
                }
            }

            if(wait2.size() > 0) {
                while(stair2.size() < 3 && wait2.size() > 0) {
                    if(wait2.peek() < time) {
                        wait2.poll();
                        stair2.offer(time + stairPosition.get(1)[2]);
                        continue;
                    }else {
                        break;
                    }
                }
            }
//            System.out.println("time:"+time);
//            System.out.println("wait1:"+wait1);
//            System.out.println("wait2:"+wait2);
//            System.out.println("stair1:"+stair1);
//            System.out.println("stair2:"+stair2);

        }
        //상황 종료 후 시간 비교
        min = Math.min(min, time);

    }
}