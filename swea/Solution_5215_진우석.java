import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_5215_진우석 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine().trim());
        for (int t=1;t<T+1;t++) {
            st = new StringTokenizer(br.readLine().trim());
            int elementCnt = Integer.parseInt(st.nextToken());
            int limitKcal = Integer.parseInt(st.nextToken());
            int[][] arr = new int[elementCnt][2];
            for (int i=0;i<elementCnt;i++) {
                st = new StringTokenizer(br.readLine().trim());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }
            int[] visited = new int[arr.length];
            int scoreSumMax = 0;
            for (int i=0;i<arr.length;i++) {
                Arrays.sort(visited);
                visited[arr.length-1-i] = 1;
                do {
                    int scoreSum = 0;
                    int kcalSum = 0;
                    for (int j=0;j<visited.length;j++) {
                        if (visited[j]==1) {
                            scoreSum+=arr[j][0];
                            kcalSum+=arr[j][1];
                        }
                    }
                    if (kcalSum>limitKcal) continue;
                    scoreSumMax = Math.max(scoreSum, scoreSumMax);

                }
                while (combination(visited));
            }

            System.out.println("#"+t+" "+scoreSumMax);
        }
    }
    public static boolean combination(int[] visited) {
        //꼭대기 찾기
        int i=visited.length-1;
        while (i>0 && visited[i-1]>=visited[i]) --i;
        if (i==0) {
            return false;
        }
        //끝~꼭대기 큰수찾기
        int j=visited.length-1;

        while (visited[i-1]>=visited[j]) --j;
        swap(visited ,i-1, j);
        //꼭대기부터 맨뒤까지 스왑을 통해 오름차순 형태로 만듬
        int k = visited.length-1;
        while (i<k) {
            swap(visited,i++,k--);
        }
        //System.out.println(Arrays.toString(visited)+" ");
        return true;
    }
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}