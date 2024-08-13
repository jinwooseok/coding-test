import java.io.*;
import java.util.*;

public class Solution_2112 {
    static int height, width, passCnt;
    static int[] arr;
    static int minCnt;
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        /*
         * 설계
         * 1. 비트마스크 생성
         * 2. dfs를 통해 탐색한다.bfs는 배열을 복사해야한다는 단점이 너무 컸다! 아무리 비트마스크로 줄이고 줄여도 복사하는건 너무 큰 리스크인듯하다
         * 3. 열마다 연속된 것이 있는지 검사
         * 4. 통과 개수가 합격기준을 넘는가?
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            height = Integer.parseInt(st.nextToken());
            width = Integer.parseInt(st.nextToken());
            passCnt = Integer.parseInt(st.nextToken());
            minCnt = Integer.MAX_VALUE;
            //비트마스크 생성
            arr = new int[height];
            for (int i = 0; i < height; i++) {
                st = new StringTokenizer(br.readLine());
                int bits = 0;
                for (int j = 0; j < width; j++) {
                    if (st.nextToken().equals("1")) {
                        bits += (1 << ((width - j - 1)));
                    }
                }
                arr[i] = bits;
            }
            if (!isComplete()) {
                dfs(0, 0);
                System.out.println("#"+(t+1)+" "+minCnt);
            } else {
                System.out.println("#"+(t+1)+" "+0);
            }
        }
    }
    static void dfs(int depth, int cnt) {
        if (depth > height) {
            return;
        }
        if (isComplete()) {
            minCnt = Math.min(minCnt, cnt);
            return;
        }
        if (cnt >= minCnt) {
            return; // 이미 찾은 최소값보다 큰 경우 탐색 중지
        }

        // 현재 막을 변경하지 않고 넘어감
        dfs(depth + 1, cnt);

        // 현재 막을 A로 변경 (모든 비트를 0으로)
        int backup = arr[depth];
        arr[depth] = 0;
        dfs(depth + 1, cnt + 1);

        // 현재 막을 B로 변경 (모든 비트를 1로)
        arr[depth] = (1 << width) - 1; // B (모든 비트를 1로)
        dfs(depth + 1, cnt + 1);

        // 원래 상태로 복구
        arr[depth] = backup;
    }
    //완성됐는지 확인
    public static boolean isComplete() {
        for (int col = 0; col < width; col++) {
            int cnt = 1; // 연속된 동일한 특성의 개수를 셀 카운터
            boolean valid = false;

            for (int row = 1; row < height; row++) {
                // 이전 행과 같은 특성인 경우 cnt 증가
                if ((arr[row] & (1 << (width - col - 1))) == (arr[row - 1] & (1 << (width - col - 1)))) {
                    cnt++;
                } else {
                    cnt = 1; // 연속되지 않으면 cnt 초기화
                }

                // K개 이상의 연속된 동일한 특성을 찾으면 valid 체크
                if (cnt >= passCnt) {
                    valid = true;
                    break;
                }
            }

            // 현재 열에서 valid하지 않으면 성능검사 실패
            if (!valid) {
                return false;
            }
        }
        return true; // 모든 열이 valid하면 성능검사 통과
    }
}