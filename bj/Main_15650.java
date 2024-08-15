import java.util.*;
import java.io.*;
//public class Main_15650 {
//    public static void main(String[] args) throws IOException{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st;
//        st = new StringTokenizer(br.readLine());
//        int n = Integer.parseInt(st.nextToken());
//        int m = Integer.parseInt(st.nextToken());
//        boolean[] visited = new boolean[n];
//        List<boolean[]> result = new ArrayList<>();
//        combination(result, visited, 0, n, m);
//    }

//    public static void combination(List<boolean[]> result, boolean[] visited, int startIndex, int n, int m){
//        //숫자 m개 추출
//        if (m==0){
//            StringBuilder sb;
//            sb = new StringBuilder();
//            for (int i=0;i<visited.length;i++){
//                if (visited[i]){
//                    sb.append(i+1);
//                    sb.append(" ");
//                }
//            }
//            sb.deleteCharAt(sb.length()-1);
//            System.out.println(sb);
//            return;
//        }
//        if(startIndex == n) {
//            return;
//        } else {
//            visited[startIndex] = true;
//            combination(result, visited, startIndex+1, n, m-1);
//
//            visited[startIndex] = false;
//            combination(result, visited, startIndex+1, n, m);
//        }
//    }
//}
public class Main_15650 {
    /*
    위쪽은 visited를 활용했던 옛날 공식이다.
    for문을 사용하지 않는 조합 생성을 연습하기 위해 생성했다.

    로직
    - 단순 조합

    설계
    - combination(n: 숫자목록, result: 결과로 사용할 배열, startIndex:뽑기시작지점, depth: 뽑은 숫자의 개수, r:뽑고싶은 갯수)
     */

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        combination(n, new int[m], 1, 0, m);
    }
    public static void combination(int n, int[] result, int startIndex, int depth, int r) {
        //기저 조건
        if (depth == r) {
            for (int i=0;i<r;i++){
                sb.append(result[i]);
                sb.append(' ');
            }
            System.out.println(sb);
            sb.setLength(0);
        } else {
            if (n>=startIndex) { //n을 초과했다면 depth를 채우지 못하고 넘어간 것이므로 리턴
                result[depth] = startIndex;
                combination(n, result, startIndex + 1, depth + 1, r);
                combination(n, result, startIndex + 1, depth, r);
            }
        }
    }
}