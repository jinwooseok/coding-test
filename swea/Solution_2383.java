import java.io.*;
import java.util.*;
public class Solution_2383 {
    /*
     * 모든 인원이 계단까지 이동
     * 계단까지 이동하는 시간+계단 입구 도착 후 계단을 완전히 내려가는 시간
     * 계단 stair[k][3] : 최대 3명. K분 후 탈출.
     * dp : 같은 사람은 계단에 도착하는 시간이 같다. 각 사람이 각 계단으로 이동하는 정보를 기록해둔다.
     * 사람이 어떤 계단을 선택하는지 조합 추출. 비트마스크
     * 계단을 초과하는지 체크
     * 마지막에 계단으로 출발하는 사람+계단 길이가 시간
     *
     * 계단을 나가고 난 후 바로 들어오는 경우 누락, 미리 와있는 경우는 입구 도착 + 1 생략. => 이 2개 문제를 계속 ㅗㄶ침
     */
    static int N;
    static int minCnt;
    static List<int[]> people, stairs;
    public static void main(String[] args) throws IOException {
        // 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t=1;t<=T;t++) {
            minCnt = Integer.MAX_VALUE;
            people = new ArrayList<>(); // 사람 리스트
            stairs = new ArrayList<>(); // 계단 리스트
            N = Integer.parseInt(br.readLine());
            for (int i=0;i<N;i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0;j<N;j++) {
                    int num = Integer.parseInt(st.nextToken());
                    if (num>=2) {
                        stairs.add(new int[] {i,j,num}); // 행, 열, 깊이
                    } else if (num==1) {
                        people.add(new int[] {i,j}); // 행, 열
                    }
                }
            }

            subset(people.size(),0,0); // 부분집합 찾음. 경우는 계단 1과 2에 들어가는 경우
            sb.append("#").append(t).append(" ").append(minCnt).append("\n");
        }
        System.out.println(sb);
    }
    //부분집합 함수
    private static void subset(int total, int visited, int idx) {
        if (total == idx) {
            minCnt=Math.min(minCnt, setOnStairs(visited));//최소값을 스태틱 값에 저장
            return;
        }
        subset(total,visited|(1<<idx), idx+1);
        subset(total,visited,idx+1);
    }
    //계단에 들어가기 위해 대기열 큐 생성.
    private static int setOnStairs(int visited) {
        PriorityQueue<Integer> waitQueue1 = new PriorityQueue<>();
        PriorityQueue<Integer> waitQueue2 = new PriorityQueue<>();
        for (int i=0;i< people.size();i++) {
            if ((visited & (1 << i)) == 0) {
                waitQueue1.add(Math.abs(people.get(i)[0]-stairs.get(0)[0])+Math.abs(people.get(i)[1]-stairs.get(0)[1]));
            } else {
                waitQueue2.add(Math.abs(people.get(i)[0]-stairs.get(1)[0])+Math.abs(people.get(i)[1]-stairs.get(1)[1]));
            }
        }
        //계단 탈출 시뮬레이션 함수를 각각 돌려서 가장 늦게 탈출한 사람의 시간을 반환
        return Math.max(exitStairs(waitQueue1, stairs.get(0)[2]), exitStairs(waitQueue2,stairs.get(1)[2]));
    }
    //계단 탈출 시뮬레이션. 마지막으로 탈출한 사람의 시간을 반환
    private static int exitStairs(PriorityQueue<Integer> waitQueue, int stairLength) {
        Deque<Integer> stairQueue = new ArrayDeque<>();
        int curTime = 1;
        int last = 0;
        while (!waitQueue.isEmpty() || !stairQueue.isEmpty()) {
            //탈출 성공 시 last에 시간 기록. 주의! 시간 타이밍이 탈출하는 즉시 입장가능함
            while (!stairQueue.isEmpty() && stairQueue.peekFirst()+stairLength <= curTime) {
                last = stairQueue.pollFirst();
            }
            //3보다 작으면 계단에 입장
            while (stairQueue.size() < 3 && !waitQueue.isEmpty()) {
                int next = waitQueue.poll();
                //주의! 미리 도착해서 대기중이라면 입구 도착 후 1분 제약 없음
                if (next<curTime) stairQueue.addLast(curTime);
                else stairQueue.addLast(next+1);
            }
            curTime++;
        }
        return last+stairLength;
    }
}
