import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17471 {
    /*
    N개의 선거구로 나뉘어 있음
    두 선거구의 인구차이를 최소화해야함
    그래프의 노드를 둘로 나누는 것

    설계
    1. 조합으로 구역을 나눔
    2. 연결되었는지 검사함
    3. 인구차이의 최소값을 찾음

    3시 45분 시작

    입력값
    1. 구역의 개수 areaCnt
    2. 각 구역과 인접한 구역의 정보 N개의 줄
        - 각 정보의 첫번째는 인접한 구역의 수. 이후 인접 구역 정보가 주어짐
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //첫째줄
        int areaCnt = Integer.parseInt(br.readLine().trim());
        //인구수배열
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int[] areas = new int[areaCnt];
        for (int i=0;i<areaCnt;i++) {
            areas[i] = Integer.parseInt(st.nextToken());
        }
        //인접 정보 입력받기
        int[][] idjArr = new int[areaCnt][areaCnt];
        for (int i=0;i<areaCnt;i++) {
            st = new StringTokenizer(br.readLine().trim());
            int relationCnt = Integer.parseInt(st.nextToken());
            for (int j=0;j<relationCnt;j++) {
                idjArr[i][Integer.parseInt(st.nextToken())-1] = 1;
            }
        }
//        for (int i=0;i<areaCnt;i++){
//            System.out.println(Arrays.toString(idjArr[i]));
//        }
        //부분집합으로 같은 구로 선정할 곳 구하기
        int result = subset(areas, idjArr, 0, 0, areaCnt);
        if (result == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(result);
        }
    }
    //부분집합 구하기 (int[] areas, int[][] idjArr, int selected, int startIndex, int areaCnt)
    private static int subset(int[] areas, int[][] idjArr, int selected, int startIndex, int areaCnt) {
        //경우의수를 추출했다면 연결된지 검사 후 차이 구하기
        if (startIndex>=areaCnt){
            int result = Integer.MAX_VALUE;
            if (isConnected(idjArr, selected, areaCnt)){
                return findResult(areas, selected);
            }
            return result;
        }
        int minCnt1 = subset(areas, idjArr, selected | (1<<startIndex), startIndex+1, areaCnt);
        int minCnt2 = subset(areas, idjArr, selected, startIndex+1, areaCnt);
        return Math.min(minCnt1, minCnt2);
    }
    private static boolean isConnected(int[][] idjArr, int selected, int areaCnt) {
        int visited1 = getVisited(idjArr, selected, areaCnt);
        int visited2 = getVisited(idjArr, ~selected & ((1 << areaCnt) - 1), areaCnt);
        // 전체 방문한 구역이 전체 구역과 동일한지 확인
        return (visited1 | visited2) == (1 << areaCnt) - 1;
    }

    private static int getVisited(int[][] idjArr, int selected, int areaCnt) {
        boolean[] visited = new boolean[areaCnt];
        Queue<Integer> queue = new LinkedList<>();
        int start = -1;

        // selected된 첫 번째 노드를 찾아서 방문 시작
        for (int i = 0; i < areaCnt; i++) {
            if ((selected & (1 << i)) != 0) {
                start = i;
                break;
            }
        }
        if (start == -1) return 0; // selected에 선택된 노드가 없을 경우

        queue.offer(start);
        visited[start] = true;
        int visitedMask = 1 << start;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int i = 0; i < areaCnt; i++) {
                if (idjArr[node][i] == 1 && !visited[i] && (selected & (1 << i)) != 0) {
                    visited[i] = true;
                    queue.offer(i);
                    visitedMask |= (1 << i);
                }
            }
        }
        return visitedMask;
    }
    private static int findResult(int[] areas, int selected) {
        int firstGroupSize = 0;
        int secondGroupSize = 0;
        for (int i=0;i<areas.length;i++){
            if ((selected&(1<<i))!=0){
                firstGroupSize+=areas[i];
            } else {
                secondGroupSize+=areas[i];
            }
        }
//        System.out.println(firstGroupSize+" "+secondGroupSize);
        if (firstGroupSize==0 || secondGroupSize==0) {
            return Integer.MAX_VALUE;
        }

        return Math.abs(firstGroupSize-secondGroupSize);
    }
}
