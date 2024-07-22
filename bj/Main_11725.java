import java.io.*;
import java.util.*;
public class Main_11725 {
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i=0;i<n-1;i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map.putIfAbsent(a, new ArrayList<Integer>());
            map.get(a).add(b);
            map.putIfAbsent(b, new ArrayList<Integer>());
            map.get(b).add(a);
        }
        int cur = 1;
        Deque<Integer> deque = new LinkedList<>();
        deque.addFirst(cur);
        boolean[] visited = new boolean[n+1];
        visited[1] = true;
        while (!deque.isEmpty()) {
            cur = deque.pollFirst();
            List<Integer> li = map.get(cur);
            for (Integer num:li) {
                if (visited[num]==true) {
                    continue;
                }
                else {
                    visited[num]=true;
                    arr[num] = cur;
                }
                deque.addLast(num);
            }
        }
        for (int i=2;i<arr.length;i++) {
            System.out.println(arr[i]);
        }
    }
}
