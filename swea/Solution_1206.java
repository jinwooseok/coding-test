import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1206 {
    static int[] buildingHeight;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int t=1;t<11;t++) {
            int cnt = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            buildingHeight = new int[cnt];
            for (int i=0;i<cnt;i++) {
                buildingHeight[i]=Integer.parseInt(st.nextToken());
            }
            int sum = 0;
            for (int i=0;i<cnt;i++) {
                sum+=check(i);
            }
            System.out.println("#"+t+" "+sum);

        }
    }
    public static int check(int cur) {
        //양옆 2칸 사이의 빌딩의 최대 높이보다 높은 층 수 구하기
        int max_h = 0;
        for (int j=0;j<2;j++) {
            for (int i=-2;i<0;i++) {
                if (cur+i<0 || cur+i>buildingHeight.length-1) continue;
                if ( max_h < buildingHeight[cur+i]) max_h = buildingHeight[cur+i];
            }
            for (int i=1;i<3;i++) {
                if (cur+i<0 || cur+i>buildingHeight.length-1) continue;
                if ( max_h < buildingHeight[cur+i]) max_h = buildingHeight[cur+i];
            }
        }
        if (buildingHeight[cur]-max_h>0) {
            return buildingHeight[cur]-max_h;
        }
        else return 0;
    }
}