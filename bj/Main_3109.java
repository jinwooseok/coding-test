import java.io.*;
import java.util.*;
public class Main_3109 {
    /*
     * 아무튼 한번 지나가면 끝이잖아..
     * 최대한 위쪽으로 지나가도록 파이프를 설계
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[][] arr = new int[R][C];
        for (int i=0;i<R;i++) {
            String str = br.readLine();
            for (int j=0;j<C;j++) {
                if (str.charAt(j)=='.') {
                    arr[i][j] = 0;
                } else {
                    arr[i][j] = 1;
                }
            }
            //System.out.println(Arrays.toString(arr[i]));
        }
        int cnt=0;
        for (int i=0;i<R;i++) {
            if (connectPipe(arr, i, 0)){
                cnt++;
            };
        }
        System.out.println(cnt);
    }

    private static boolean connectPipe(int[][] arr, int r, int c) {
        arr[r][c]=1;
        if (c==arr[0].length-1) {
            return true;
        }
        if (r-1>=0 && arr[r-1][c+1]==0) {
            if (connectPipe(arr, r-1, c+1)) return true;
        }
        if (arr[r][c+1]==0) {
            if (connectPipe(arr, r, c+1)) return true;
        }
        if (r+1<arr.length && arr[r+1][c+1]==0) {
            if (connectPipe(arr, r+1, c+1)) return true;
        }
        return false; //다 아닌 경우 false
    }
}
