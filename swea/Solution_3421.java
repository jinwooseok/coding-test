import java.io.*;
import java.util.StringTokenizer;

public class Solution_3421 {
    /*
     * 입력값
     * T : 테스트 케이스 개수
     * N, M : 재료번호끝, 궁합이 맞지 않는 재료 M개쌍
     * a, b : 재료 a, b가 동시에 포함되면 안된다.
     *
     * 로직
     * 1. 입력을 받고 겹치면 안되는 재료 조합을 비트로 표현한다.
     * 2. 부분집합을 구하는 함수를 사용한다. 다만 매번 비트로 연산
     *
     * subset(재료 개수, 안되는조합배열, 시작인덱스, 깊이, 최대 깊이)
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t=0;t<T;t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int numCnt = Integer.parseInt(st.nextToken());
            int pairCnt = Integer.parseInt(st.nextToken());
            int[][] restrictArr = new int[pairCnt][2];
            for (int i=0;i<pairCnt;i++) {
                st = new StringTokenizer(br.readLine());
                restrictArr[i][0] = (1<<Integer.parseInt(st.nextToken())-1);
                restrictArr[i][1] = (1<<Integer.parseInt(st.nextToken())-1);
            }
            sb.append("#"+(t+1)+" "+subset(numCnt, 0, restrictArr, 0));
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static int subset(int num, int status, int[][] restrict, int startIndex) {
        for (int i=0; i<restrict.length;i++) {
            if ((status & restrict[i][0])!=0 && (status & restrict[i][1])!=0) {
                return 0;
            }
        }
        if (startIndex == num) {
            return 1;
        }
        int cnt = 0;
        //상태를 확인했을 때 a번과 b번과 상태가 겹치는 경우 확인 : &연산자와 0 이용
        //startIndex에 따라 상태를 업데이트
        cnt+=subset(num, status|(1<<startIndex), restrict, startIndex+1);
        cnt+=subset(num, status, restrict, startIndex+1);
        return cnt;
    }
}