import java.io.*;
import java.util.*;
public class Solution_2105 {

    /*
     * 디저트 다이아몬드 만드는데 경로에 겹치는 디저트가 있어서는 안된다.
     * 필요로직
     * 1. 다이아를 그리며 이동하는 로직
     *  다이아를 선택하는 방법은 그냥 단순 이동
     *  1단계, 2단계, 3단계, 4단계로 방향을 설정.
     * 3단계와 4단계는 width나 height가 0이어야 한다는 설정이 있다.
     * 마름모의 길이들은 1단계 2단계로 이미 결정나기 때문에 3단계, 4단계에서 안되는 길이로 탐색하는 것을 없앰
     *
     * 2. 같은디저트가 있는지 검사하는 로직
     *  100개까지의 visited로 평가
     * 가장 많이 먹을 때의 디저트 개수 출력
     *
     */
    static int maxDessert;
    static int[][] MOVE = {{-1,1},{1,1},{1,-1},{-1,-1}};
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t=1;t<T+1;t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][N];
            for (int i=0;i<N;i++) {
                st = new StringTokenizer(br.readLine());
                for (int j=0;j<N;j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }

            }
            maxDessert = 0;
            boolean[] visited = new boolean[101];
            for (int i=0;i<N;i++) {
                //로직 시작
                for (int j=0;j<N;j++) {
                    findMaxDessert(arr, i, j, 0, 0, 0, visited, 0);
                }
            }
            //findMaxDessert(arr, 5, 3, 0, 0, 0, visited, 0);
            //출력
            if (maxDessert == 0) {
                System.out.println("#"+t+" "+(-1));
            } else {
                System.out.println("#"+t+" "+maxDessert);
            }
        }
    }
    // 방향 정해짐. 

    private static void findMaxDessert(int[][] arr, int r, int c, int width, int height, int dessertCnt, boolean[] visited, int level) {
        // 범위 체크
        if (r < 0 || c < 0 || r >= arr.length || c >= arr[0].length || width < 0 || height < 0) {
            return;
        }

        // 종료 조건
        if (width == 0 && height == 0 && dessertCnt > 0) {
            maxDessert = Math.max(maxDessert, dessertCnt);
            return;
        }

        // 디저트 종류 체크
        if (visited[arr[r][c]]) {
            return;
        }

        // 디저트 종류 처리
        visited[arr[r][c]] = true;

        // 재귀 호출. 가던가 틀던가.
        if (level == 0) {
            findMaxDessert(arr, r - 1, c + 1, width + 1, height, dessertCnt + 1, visited, level + 1);
        } else if (level == 1) {
            findMaxDessert(arr, r - 1, c + 1, width + 1, height, dessertCnt + 1, visited, level);
            findMaxDessert(arr, r + 1, c + 1, width, height + 1, dessertCnt + 1, visited, level + 1);
        } else if (level == 2) {
            findMaxDessert(arr, r + 1, c + 1, width, height + 1, dessertCnt + 1, visited, level);
            findMaxDessert(arr, r + 1, c - 1, width - 1, height, dessertCnt + 1, visited, level + 1);
        } else if (level == 3) {
            if (width == 0) {
                findMaxDessert(arr, r - 1, c - 1, width, height - 1, dessertCnt + 1, visited, level + 1);
            } else {
                findMaxDessert(arr, r + 1, c - 1, width - 1, height, dessertCnt + 1, visited, level);
            }
        } else if (level == 4 && width == 0) {
            findMaxDessert(arr, r - 1, c - 1, width, height - 1, dessertCnt + 1, visited, level);
        }

        // 방문 처리 취소
        visited[arr[r][c]] = false;
    }
}