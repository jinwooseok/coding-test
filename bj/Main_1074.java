import java.io.*;
import java.util.StringTokenizer;
public class Main_1074 {

    /*
     * 1. 입력받기
     * 2. 몇번째 사각형인지 찾기 ( 4분면 중 어느 위치인지 계속해서 탐색함.
     * 3. 그 사각형의
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int arrSize = (int) Math.pow(2, N);
        System.out.println(search(arrSize, 0, 0, 0, arrSize*arrSize/2, arrSize*arrSize, row, col));
    }
    private static int search(int arrSize, int startX, int startY, int left, int mid, int right, int row, int col) {
        if (left==mid || right==mid) {
            return mid;
        }
        int result = 0;
        if (startX+arrSize/2>row && startY+arrSize/2>col) {//1사분면
            result = search(arrSize/2, startX, startY, left, (left+(left+mid)/2)/2, (left+mid)/2, row, col); //0~
        } else if (startX+arrSize/2>row && startY+arrSize/2<=col) {//2사분면
            result = search(arrSize/2, startX, startY+arrSize/2, (left+mid)/2, ((left+mid)/2+mid)/2, mid, row, col);
        } else if (startX+arrSize/2<=row && startY+arrSize/2>col) {//3사분면
            result = search(arrSize/2, startX+arrSize/2, startY, mid, (mid+(right+mid)/2)/2, (right+mid)/2, row, col);
        } else if (startX+arrSize/2<=row && startY+arrSize/2<=col) {//4사분면
            result = search(arrSize/2, startX+arrSize/2, startY+arrSize/2, (right+mid)/2, (right+(right+mid)/2)/2, right, row, col);
        }
        return result;
    }
}
