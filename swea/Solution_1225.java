import java.io.*;
import java.util.StringTokenizer;
public class Solution_1225 {
 
    /*로직
     * 1. for문으로 돌리면서 숫자를 -하는 로직을 수행한다.
     * 2. 횟수에 따라 자리를 이동해준다.
     * 
     * 자리 : n%8
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for (int t=0;t<10;t++) {
            //입력 받기
            int testNum = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[8];
            for (int i=0;i<8;i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
             
            int idx = 0;
            int subCnt=0;
            while (true) {
                //배열 크기 줄이기. 선택된 idx마다 1, 2, 3, 4, 5를 한 사이클로 감소시킴
                arr[idx%8]-=subCnt%5+1;
                subCnt += 1;
                //방금 막 수행한 값이 0보다 작거나 같아지면 0으로 변경 후 break
                if (arr[idx%8] <= 0) {
                    arr[idx%8] = 0;
                    idx++;
                    break;
                }
                idx++;
            }
             
            //출력준비
            sb.append("#"+testNum+" ");
            for (int i=0;i<8;i++) {
                sb.append(arr[(i+idx%8)%8]+" ");
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}