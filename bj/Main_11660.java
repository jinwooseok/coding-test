import java.io.*;
import java.util.StringTokenizer;
public class Main_11660 {
    /*
    부분합 구하기
    2차원 누적합 배열을 만들어 수를 구한다.

    입력값
    1. 표의크기 arrSize, 합을 구하는 횟수 sumCnt
    2. arr의 값들이 나옴
    3. x1,y1,x2,y2가 반복해서 나옴

    설계
    1. 입력받으면서 누적합배열 생성
        - prefixSum[i+1][j+1] = prefixSum[i+1][j]+prefixSum[i][j+1]-prefixSum[i][j]+arr[i][j]
    2. 누적합 계산
        - sum = prefixSum[x2][y2] - prefixSum[x1-1][y2] - prefixSum[x2][y1-1] + prefixSum[x1-1][y1-1]
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int arrSize = Integer.parseInt(st.nextToken());
        int sumCnt = Integer.parseInt(st.nextToken());
        int[][] prefixSum = new int[arrSize+1][arrSize+1];

        for (int i=0;i<arrSize;i++){
            st = new StringTokenizer(br.readLine().trim());
            for (int j=0;j<arrSize;j++){
                //누적합배열 생성 공식. 까먹기 쉬우니 잘 기억해두자
                prefixSum[i+1][j+1] = prefixSum[i+1][j]+prefixSum[i][j+1]-prefixSum[i][j]+Integer.parseInt(st.nextToken());
            }
        }
        StringBuilder sb = new StringBuilder(); //stringBuilder
        //부분합 계산 시작
        for (int i=0;i<sumCnt;i++){
            st = new StringTokenizer(br.readLine().trim());
            int x1= Integer.parseInt(st.nextToken());
            int y1= Integer.parseInt(st.nextToken());
            int x2= Integer.parseInt(st.nextToken());
            int y2= Integer.parseInt(st.nextToken());
            //2차원 부분합 공식. 까먹지 않기 위해 식의 모양과 배열의 모양을 잘 기억해두자.
            sb.append(prefixSum[x2][y2]-prefixSum[x1-1][y2]-prefixSum[x2][y1-1]+prefixSum[x1-1][y1-1]).append('\n');
        }
        System.out.println(sb);
    }
}
