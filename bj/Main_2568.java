import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2568 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Line[] lines = new Line[N];
        int[] arr = new int[N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            lines[i] = new Line(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(lines);
        for (int i=0;i<N;i++) {
            arr[i] = lines[i].start;
        }
        //System.out.println(Arrays.toString(arr));
        int[] dp = new int[N];
        int[] result = new int[N];
        dp[0] = arr[0];
        result[0] = 0;
        int idx = 1;
        for (int i=1;i<N;i++) {
            if (dp[idx-1]>=arr[i]) {
                //이분탐색.
                int left = 0; int right = idx-1; int mid = (right+left)/2;
                while (left<=right) {
                    if (dp[mid]<arr[i]) {
                        left = mid+1;
                    } else if (dp[mid]>arr[i]) {
                        right = mid-1;
                    } else {
                        break;
                    }
                    mid = (left+right)/2;
                }

                //System.out.println(left+" "+mid+" "+right);
                if (dp[mid]>=arr[i]) {
                    dp[mid] = arr[i];
                    result[i] = mid;
                } else {
                    dp[mid+1] = arr[i];
                    result[i] = mid+1;
                }
            } else {
                dp[idx] = arr[i];
                result[i] = idx;
                idx++;
            }
        }
        int cnt = idx;
        PriorityQueue<Integer> removedLines = new PriorityQueue<>();
        for (int i=N-1;i>=0;i--) {
            if (result[i]!=idx-1) {
                removedLines.add(arr[i]);
            } else {
                idx--;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(N-cnt).append("\n");
        while (!removedLines.isEmpty()) {
            sb.append(removedLines.poll()).append("\n");
        }
        System.out.println(sb);
    }

    static class Line implements Comparable<Line> {
        int idx;
        int start;

        public Line(int start, int idx) {
            this.idx = idx;
            this.start = start;
        }

        @Override
        public int compareTo(Line o) {
            return this.idx-o.idx;
        }
    }

}
