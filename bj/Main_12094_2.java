import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_12094_2 {

    /*
     * 다 실행하고 나면 결과가 어떻게 될것인지 구해라
     * 밀면? 1.빈공간을 채우는 연산, 미는 방향의 벽부터 먼쪽으로 합치는 동작
     */
    public static void main(String[] args) throws IOException {
        // 입력부
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int [][] arr = new int[N][N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //dfs로 로직 돌리기
        System.out.println(dfs(arr, new int[10],0, 0));
    }
    // dfs로 move 명령을 여러번 실행
    private static int dfs(int[][] arr, int[] selected, int startIndex, int depth) {
        //1.만약 최대값이면 더이상 할거없음
        //2.조합으로 해도 될 듯?
        if (depth == 10) {
            //System.out.println(Arrays.toString(selected));
            move(arr, selected[0]);
            int beforeSelected = selected[0];
            for (int i=1;i<10;i++) {
                if (beforeSelected == selected[i]) {
                    continue;
                }
                move(arr, selected[i]);
                beforeSelected = selected[i];
            }
            int maxNum = 0;
            for (int i=0;i<arr.length;i++) {
                for (int j = 0; j < arr.length; j++) {
                    maxNum=Math.max(maxNum, arr[i][j]);
                }
            }
            return maxNum;
        }
        int result = 0;
        for (int i=startIndex;i<4;i++){
            selected[depth] = i;
            result = Math.max(result,dfs(arr, selected, i,depth+1));
        }
        return result;
    }

    private static void move(int[][] arr, int command) {
        Deque<Integer> deque = new ArrayDeque<>();
        if (command==0) {
            for (int i=0;i<arr.length;i++) {
                insertLeftDeque(arr, deque, i);
            }
        } else if (command==1) {
            for (int i=0;i<arr.length;i++) {
                insertRightDeque(arr, deque, i);

            }
        } else if (command==2) {
            for (int i=0;i<arr.length;i++) {
                insertUpDeque(arr, deque, i);

            }
        } else if (command==3) {
            for (int i=0;i<arr.length;i++) {
                insertDownDeque(arr, deque, i);
            }
        }
    }
    private static void insertLeftDeque(int[][] arr, Deque<Integer> deque, int row) {
        int idx=0;
        for (int j=0;j<arr.length;j++) {
            if (arr[row][j]!=0) {
                if (!deque.isEmpty()) {
                    int forwardNum = deque.pollLast();
                    if (arr[row][j] == forwardNum) {
                        while (!deque.isEmpty()) {
                            arr[row][idx++]=deque.pollFirst();

                        }
                        arr[row][idx++]=forwardNum+arr[row][j];
                    } else {
                        deque.addLast(forwardNum);
                        deque.addLast(arr[row][j]);
                    }
                } else {
                    deque.addLast(arr[row][j]);
                }
            }
        }
        for (int j=idx;j<arr.length;j++) {
            if (deque.isEmpty()) {
                arr[row][j]=0;
            } else {
                arr[row][j]=deque.pollFirst();
            }
        }
    }
    private static void insertRightDeque(int[][] arr, Deque<Integer> deque, int row) {
        int idx=arr.length-1;
        for (int j=arr.length-1;j>=0;j--) {
            if (arr[row][j]!=0) {
                if (!deque.isEmpty()) {
                    int forwardNum = deque.pollLast();
                    if (arr[row][j] == forwardNum) {
                        while (!deque.isEmpty()) {
                            arr[row][idx--]=deque.pollFirst();

                        }
                        arr[row][idx--]=forwardNum+arr[row][j];
                    } else {
                        deque.addLast(forwardNum);
                        deque.addLast(arr[row][j]);
                    }
                } else {
                    deque.addLast(arr[row][j]);
                }
            }
        }
        for (int j=idx;j>=0;j--) {
            if (deque.isEmpty()) {
                arr[row][j]=0;
            } else {
                arr[row][j]=deque.pollFirst();
            }
        }
    }
    private static void insertUpDeque(int[][] arr, Deque<Integer> deque, int col) {
        int idx=0;
        for (int j=0;j<arr.length;j++) {
            //System.out.println(col+" "+deque);
            if (arr[j][col]!=0) {
                if (!deque.isEmpty()) {
                    int forwardNum = deque.pollLast();
                    if (arr[j][col] == forwardNum) {
                        while (!deque.isEmpty()) {
                            arr[idx++][col]=deque.pollFirst();

                        }
                        arr[idx++][col]=forwardNum+arr[j][col];
                    } else {
                        deque.addLast(forwardNum);
                        deque.addLast(arr[j][col]);
                    }
                } else {
                    deque.addLast(arr[j][col]);
                }
            }
        }
        for (int j=idx;j<arr.length;j++) {
            if (deque.isEmpty()) {
                arr[j][col]=0;
            } else {
                arr[j][col]=deque.pollFirst();
            }
        }
    }
    private static void insertDownDeque(int[][] arr, Deque<Integer> deque, int col) {
        int idx=arr.length-1;
        for (int j=arr.length-1;j>=0;j--) {
            if (arr[j][col]!=0) {
                if (!deque.isEmpty()) {
                    int forwardNum = deque.pollLast();
                    if (arr[j][col] == forwardNum) {
                        while (!deque.isEmpty()) {
                            arr[idx--][col]=deque.pollFirst();

                        }
                        arr[idx--][col]=forwardNum+arr[j][col];
                    } else {
                        deque.addLast(forwardNum);
                        deque.addLast(arr[j][col]);
                    }
                } else {
                    deque.addLast(arr[j][col]);
                }
            }
        }
        for (int j=idx;j>=0;j--) {
            if (deque.isEmpty()) {
                arr[j][col]=0;
            } else {
                arr[j][col]=deque.pollFirst();
            }
        }
    }
}
