import java.util.Arrays;
import java.util.StringTokenizer;
import java.io.*;

public class Main_2529 {
    static String[] condition;
    static int[] minArray;
    static int[] maxArray;
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        condition = new String[N];
        for (int i=0;i<N;i++) {
            condition[i] = st.nextToken();
        }
        //최소 array와 최대 array를 미리 잡고 꾸준히 업데이트할 예정
        minArray = new int[N+1];
        for (int i=0;i<N+1;i++) {
            minArray[i] = 9;
        }
        maxArray = new int[N+1];
        //순열을 통해 모든 가능한 조합을 탐색
        permutation(new int[N+1], new boolean[10], 0, N+1);
        StringBuilder sb = new StringBuilder();
        for (int num:maxArray) {
            sb.append(num);
        }
        sb.append("\n");
        for (int num:minArray) {
            sb.append(num);
        }
        System.out.println(sb);
    }

    public static void permutation(int[] result, boolean[] visited, int depth, int r) {
        if (depth==r) {
            //isTrue함수는 부등호 배열을 만족하는지를 확인하는 함수이다.
            if (isTrue(result)) {
                //만약 부등호 배열을 만족한다면 maxArray, minArray와 index를 비교해 최대, 최소값을 찾는다.
                for (int i=0;i<result.length;i++) {
                    if (minArray[i]>result[i]) {
                        minArray = Arrays.copyOf(result, result.length);
                        break;
                    } else if (minArray[i]<result[i]) {
                        break;
                    }
                }
                for (int i=0;i<result.length;i++) {
                    if (maxArray[i]<result[i]) {
                        maxArray = Arrays.copyOf(result, result.length);
                        break;
                    } else if (maxArray[i]>result[i]) {
                        break;
                    }
                }
            }
            return;
        } else {
            for (int i=0;i<10;i++) {
                if (!visited[i]) {
                    visited[i]=true;
                    result[depth] = i;
                    permutation(result, visited, depth+1, r);
                    visited[i]=false;
                }
            }
        }
    }

    /*
     * 옮은 배열인지 확인하는 메서드
     * <와 >를 기준으로 i번째 수와 i+1번째수가 조건을 만족하지 못하는 경우 바로 false를 리턴
     * 만족하는 경우 함수의 끝까지 가서 true를 리턴한다.
     */
    public static boolean isTrue(int[] arr) {
        for (int i=0;i<condition.length;i++) {
            if (condition[i].equals("<") && !(arr[i]<arr[i+1])) {
                return false;
            } else if (condition[i].equals(">") && !(arr[i]>arr[i+1])) {
                return false;
            }
        }
        return true;
    }
}

