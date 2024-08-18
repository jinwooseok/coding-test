import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_14502 {
    //전체 arr와 area리스트들을 각각 채워간다.
    //조합을 돌려 empty공간 중 벽을 세울 공간을 찾는다.
    //조합 종료 시에 바이러스 시뮬레이션 돌리고. 백트래킹으로 되돌려 놓는다.
    //바이러스 시뮬레이션 : 2의 위치를 기준으로 dfs진행. 막히면 끝
    //마지막에 안전구역 search. 이때도  백트래킹 준비
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        int rowSize = Integer.parseInt(st.nextToken());
        int colSize = Integer.parseInt(st.nextToken());

        int[][] arr = new int[rowSize][colSize];
        //각각 얼마나 있을지 모르므로 리스트로 만들어두자
        List<int[]> emptyAreas = new ArrayList<>();
        List<int[]> virusAreas = new ArrayList<>();
        //전체 arr와 area리스트들을 각각 채워간다.
        for (int i=0;i<rowSize;i++){
            st = new StringTokenizer(br.readLine().trim());
            for (int j=0;j<colSize;j++){
                int area = Integer.parseInt(st.nextToken());
                arr[i][j] = area;
                if (area == 0) {
                    emptyAreas.add(new int[]{i,j});
                } else if (area == 2){
                    virusAreas.add(new int[]{i,j});
                }
            }
        }
        System.out.println(installWalls(arr, emptyAreas, virusAreas, 0, 0));

    }

    private static int installWalls(int[][] areas, List<int[]> emptyAreas, List<int[]> virusAreas, int startIndex, int depth){
        //3개의 벽을 구했다면 바이러스 시뮬레이션 돌리기
        if (depth == 3) {
            return virusSimulation(areas, virusAreas);
        } else if (startIndex>=emptyAreas.size()) {
            //empty에서 3개 추출에 실패할 경우
            return -1;
        } else {
            //백트래킹으로 배열 요소 조절
            areas[emptyAreas.get(startIndex)[0]][emptyAreas.get(startIndex)[1]] = 1;
            int safeCnt1 = installWalls(areas, emptyAreas, virusAreas, startIndex+1, depth+1);
            areas[emptyAreas.get(startIndex)[0]][emptyAreas.get(startIndex)[1]] = 0;
            int safeCnt2 = installWalls(areas, emptyAreas, virusAreas, startIndex+1, depth);
            return Math.max(safeCnt1,safeCnt2);
        }
    }
    private static int virusSimulation(int[][] areas, List<int[]> virusAreas){
        int[][] copyAreas = new int[areas.length][areas[0].length];
        for (int i=0;i<areas.length;i++) {
            copyAreas[i] = Arrays.copyOf(areas[i], areas[0].length);
        }
        for (int[] virusArea:virusAreas) {
            dfs(copyAreas, virusArea[0], virusArea[1]-1);
            dfs(copyAreas, virusArea[0]-1, virusArea[1]);
            dfs(copyAreas, virusArea[0]+1, virusArea[1]);
            dfs(copyAreas, virusArea[0], virusArea[1]+1);
        }
        return searchSafeAreas(copyAreas);
    }
    private static void dfs(int[][] areas, int virusR, int virusC) {
        if (virusR<0 || virusR>=areas.length || virusC<0 || virusC>=areas[0].length || areas[virusR][virusC]==1 || areas[virusR][virusC]==2) {
            return;
        }
        areas[virusR][virusC] = 2;
        dfs(areas, virusR, virusC+1);
        dfs(areas, virusR+1, virusC);
        dfs(areas, virusR, virusC-1);
        dfs(areas, virusR-1, virusC);
    }
    private static int searchSafeAreas(int[][] areas){
        //안전구역의 개수를 세는 함수
        int safeCnt = 0;
        for (int i=0;i<areas.length;i++) {
            for (int j=0;j<areas[0].length;j++){
                if (areas[i][j]==0) {
                    safeCnt++;
                }
            }
        }
        return safeCnt;
    }
}
