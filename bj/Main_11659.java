import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11659 {
    /*
    부분합을 계속해서 구하는 문제
    입력값
    1. 배열의 크기 numCnt, 합 구하기를 진행하는 횟수 sumCnt
    2. 배열의 요소 arr
    3. start, end. start와 end까지의 합을 구해라

    추가
    - StringBuilder를 사용했을 때 시간을 많이 아낄 수 있었음
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine().trim());
        int numCnt = Integer.parseInt(st.nextToken());
        int sumCnt = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());
        int[] prefixSum = new int[numCnt+1]; //누적합배열의 사이즈를 1높이면 더 쉽게 생성 및 조회할 수 있다.
        //배열을 입력받으면서 동시에 누적합 배열을 생성할 수 있다.
        for (int i=0;i<numCnt;i++) {
            prefixSum[i+1] = Integer.parseInt(st.nextToken())+prefixSum[i];
        }
        //미리 저장해놓은 누적합배열을 통해 누적합을 출력한다.
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<sumCnt;i++){
            st = new StringTokenizer(br.readLine().trim());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(prefixSum[end]-prefixSum[start-1]);
            sb.append('\n');
        }
        System.out.println(sb);
    }
}