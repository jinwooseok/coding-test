import java.io.*;
import java.util.*;
public class Main_16953 {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        int cnt = 0;
        Deque<long[]> deque = new LinkedList<>();
        deque.addLast(new long[] {a, cnt});

        while (!deque.isEmpty()) {
            long[] arr = deque.pollFirst();
            if (arr[0]>Math.pow(10,9)) {
                continue;
            }
            if (arr[0]>b) {
                continue;
            }
            if (arr[0]==b) {
                System.out.println((int)arr[1]+1);
                return;
            }
            if (arr[0] < b) {
                deque.addLast(new long[] {addOne(arr[0]), arr[1]+1});
            }

            if (arr[0] < b) {
                deque.addLast(new long[] {mulTwo(arr[0]), arr[1]+1});
            }
        }
        System.out.println(-1);
        return;

    }

    public static long addOne(long num) {
        return Long.parseLong(String.valueOf(num)+"1");
    }

    public static long mulTwo(long num) {
        return num*2;
    }
}