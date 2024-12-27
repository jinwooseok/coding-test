import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_12100 {
    /*
     * 다 실행하고 나면 결과가 어떻게 될것인지 구해라
     * 밀면? 1.빈공간을 채우는 연산, 미는 방향의 벽부터 먼쪽으로 합치는 동작
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        String command = st.nextToken();
        int [][] arr = new int[N][N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] newArr=move(arr,command);

        for (int i=0;i<N;i++) {
            for (int j=0;j<N;j++) {
                sb.append(newArr[i][j]).append(' ');
            }
            sb.append('\n');
        }
    }

    private static int[][] move(int[][] arr, String command) {
        int[][] newArr = new int[arr.length][arr.length];
        Deque<Integer> deque = new ArrayDeque<>();
        if (command.equals("left")) {
            for (int i=0;i<arr.length;i++) {
                insertLeftDeque(arr, newArr, deque, i);
            }
        } else if (command.equals("right")) {
            for (int i=0;i<arr.length;i++) {
                insertRightDeque(arr, newArr, deque, i);

            }
        } else if (command.equals("up")) {
            for (int i=0;i<arr.length;i++) {
                insertUpDeque(arr, newArr, deque, i);

            }
        } else if (command.equals("down")) {
            for (int i=0;i<arr.length;i++) {
                insertDownDeque(arr, newArr, deque, i);
            }
        }
        return newArr;
    }
    private static void insertLeftDeque(int[][] arr, int[][] newArr, Deque<Integer> deque, int row) {
        int idx=0;
        for (int j=0;j<arr.length;j++) {
            if (arr[row][j]!=0) {
                if (!deque.isEmpty()) {
                    int forwardNum = deque.pollLast();
                    if (arr[row][j] == forwardNum) {
                        while (!deque.isEmpty()) {
                            newArr[row][idx++]=deque.pollFirst();

                        }
                        newArr[row][idx++]=forwardNum+arr[row][j];
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
                newArr[row][j] = 0;
            } else {
                newArr[row][j]=deque.pollFirst();
            }
        }
    }
    private static void insertRightDeque(int[][] arr, int[][] newArr, Deque<Integer> deque, int row) {
        int idx=arr.length-1;
        for (int j=arr.length-1;j>=0;j--) {
            if (arr[row][j]!=0) {
                if (!deque.isEmpty()) {
                    int forwardNum = deque.pollLast();
                    if (arr[row][j] == forwardNum) {
                        while (!deque.isEmpty()) {
                            newArr[row][idx--]=deque.pollFirst();

                        }
                        newArr[row][idx--]=forwardNum+arr[row][j];
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
                newArr[row][j] = 0;
            } else {
                newArr[row][j]=deque.pollFirst();
            }
        }
    }
    private static void insertUpDeque(int[][] arr, int[][] newArr, Deque<Integer> deque, int col) {
        int idx=0;
        for (int j=0;j<arr.length;j++) {
            //System.out.println(col+" "+deque);
            if (arr[j][col]!=0) {
                if (!deque.isEmpty()) {
                    int forwardNum = deque.pollLast();
                    if (arr[j][col] == forwardNum) {
                        while (!deque.isEmpty()) {
                            newArr[idx++][col]=deque.pollFirst();

                        }
                        newArr[idx++][col]=forwardNum+arr[j][col];
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
                newArr[j][col] = 0;
            } else {
                newArr[j][col]=deque.pollFirst();
            }
        }
    }
    private static void insertDownDeque(int[][] arr, int[][] newArr, Deque<Integer> deque, int col) {
        int idx=arr.length-1;
        for (int j=arr.length-1;j>=0;j--) {
            if (arr[j][col]!=0) {
                if (!deque.isEmpty()) {
                    int forwardNum = deque.pollLast();
                    if (arr[j][col] == forwardNum) {
                        while (!deque.isEmpty()) {
                            newArr[idx--][col]=deque.pollFirst();

                        }
                        newArr[idx--][col]=forwardNum+arr[j][col];
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
                newArr[j][col] = 0;
            } else {
                newArr[j][col]=deque.pollFirst();
            }
        }
    }

}