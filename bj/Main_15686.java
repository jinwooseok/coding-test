import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15686 {
    public static void main(String[] args) throws IOException {
        /*
        각 치킨집은 치킨거리가 있다. 이때 치킨거리의 합이 가장작게 해야함

        설계
        1. 치킨집배열과 집배열을 나열한다.
        2. 치킨집의 조합을 구해야할 것 같다.
        3. 치킨거리의 최소를 찾는다.
        - n은 50*50의 배열이다.
        - 치킨집의 개수는 최대 M개.. 이 부분집합 중 치킨거리가 최소가 되도록해야한다.
        -
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int arrSize = Integer.parseInt(st.nextToken());
        int chickenCnt = Integer.parseInt(st.nextToken());

        List<int[]> chickens = new ArrayList<>();
        List<int[]> homes = new ArrayList<>();
        int idx = 0;
        for (int i=0;i<arrSize;i++){
            st = new StringTokenizer(br.readLine().trim());
            for (int j=0;j<arrSize;j++) {
                //만약 넣다가 치킨집이 나왔을 경우 치킨집 배열에 위치정보와 넣는다.
                String cur = st.nextToken();
                if (cur.equals("1")) {
                    homes.add(new int[]{i,j});
                } else if (cur.equals("2")) {
                    chickens.add(new int[]{i,j});
                }
            }
        }

        System.out.println(findMinDist(homes, chickens, new int[chickenCnt][2], 0, 0, chickenCnt));
    }
    //searchMinDist(치킨집개수, 뽑은 갯수, 시작인덱스)
    private static int findMinDist(List<int[]> homes, List<int[]> chickens, int[][] selected, int startIdx, int depth, int r){
        if (depth>=r) {
            return findChikenDist(homes, selected);
        }
        if (startIdx>=chickens.size()) {
            return Integer.MAX_VALUE;
        }
        selected[depth] = chickens.get(startIdx);
        int minCnt1 = findMinDist(homes, chickens, selected, startIdx+1, depth+1, r);
        int minCnt2 = findMinDist(homes, chickens, selected, startIdx+1, depth, r);
        return Math.min(minCnt1, minCnt2);
    }

    //치킨거리를 구하는 함수
    private static int findChikenDist(List<int[]> homes, int[][] selected) {
        int totalMinDist = 0;
        for (int[] home:homes) {
            int minDist = Integer.MAX_VALUE;
            for (int[] loc: selected) {
                minDist = Math.min(minDist, Math.abs(loc[0]-home[0])+Math.abs(loc[1]-home[1]));
            }
            totalMinDist += minDist;
        }
        return totalMinDist;
    }
}
