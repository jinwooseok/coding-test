import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_12094 {
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
        System.out.println(dfs(arr, new int[10], 0));
    }
    // dfs로 move 명령을 여러번 실행
    private static int dfs(int[][] arr, int[] selected, int depth) {
        if (depth == 10) {
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
            for (int[] ints : arr) {
                for (int j = 0; j < arr.length; j++) {
                    maxNum = Math.max(maxNum, ints[j]);
                }
            }
            return maxNum;
        }
        int result = 0;
        for (int i=0;i<4;i++) {
            selected[depth] = i;
            result = Math.max(result,dfs(arr, selected, depth+1));
        }
        return result;
    }

    private static void move(int[][] arr, int command) {
        Deque<Integer> deque = new ArrayDeque<>();
        if (command==0) {
            boolean isRowMove = false;
            boolean isReverse = false;
            for (int i=0;i<arr.length;i++) {
                insertDeque(arr, deque, i, isRowMove, isReverse);
            }
        } else if (command==1) {
            boolean isRowMove = false;
            boolean isReverse = true;
            for (int i=0;i<arr.length;i++) {
                insertDeque(arr, deque, i, isRowMove, isReverse);

            }
        } else if (command==2) {
            boolean isRowMove = true;
            boolean isReverse = false;
            for (int i=0;i<arr.length;i++) {
                insertDeque(arr, deque, i, isRowMove, isReverse);

            }
        } else if (command==3) {
            boolean isRowMove = true;
            boolean isReverse = true;
            for (int i=0;i<arr.length;i++) {
                insertDeque(arr, deque, i, isRowMove, isReverse);
            }
        }
    }
    private static void insertDeque(int[][] arr, Deque<Integer> deque, int fixed, boolean isRowMove, boolean isReverse) {
        int idx;
        int increment;
        if (isReverse) {
            idx = arr.length-1;
            increment = -1;
        } else {
            idx = 0;
            increment = 1;
        }
        int row; int col;
        for (int j=(isReverse)?arr.length-1:0 ; (isReverse)? j>=0:j<arr.length;j+=increment) {
            if (isRowMove) {
                row = j;
                col = fixed;
            } else {
                row = fixed;
                col = j;
            }
            if (arr[row][col]!=0) {
                if (!deque.isEmpty()) {
                    int forwardNum = deque.pollLast();
                    if (arr[row][col] == forwardNum) {
                        while (!deque.isEmpty()) {
                            arr[row][idx]=deque.pollFirst();
                            idx += increment;
                        }
                        arr[row][idx]=forwardNum+arr[row][col];
                        idx += increment;
                    } else {
                        deque.addLast(forwardNum);
                        deque.addLast(arr[row][col]);
                    }
                } else {
                    deque.addLast(arr[row][col]);
                }
            }
        }

        for (int j=(isReverse)?arr.length-1:0 ; (isReverse)? j>=0:j<arr.length;j+=increment) {
            if (isRowMove) {
                row = j;
                col = fixed;
            } else {
                row = fixed;
                col = j;
            }
            if (deque.isEmpty()) {
                arr[row][col]=0;
            } else {
                arr[row][col]=deque.pollFirst();
            }
        }
    }
}
