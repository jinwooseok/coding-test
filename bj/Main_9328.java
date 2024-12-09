import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_9328 {
    static int[][] MOVE = {{0,1},{1,0},{0,-1},{-1,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t=1;t<=T;t++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            char[][] arr = new char[h+2][w+2];
            boolean[][] visited = new boolean[h+2][w+2];
            for (int i=1;i<=h;i++) {
                char[] temp = br.readLine().toCharArray();
                for (int j=1;j<=w;j++) {
                    arr[i][j] = temp[j-1];
                    if (temp[j-1] == '*') {
                        visited[i][j] = true;
                    }
                }
            }
            char[] temp = br.readLine().toCharArray();
            boolean[] keys = new boolean[26];
            if (temp[0] != '0') {
                for (char key:temp) {
                    keys[key-'a'] = true;
                }
            }
            //아무데서나 시작,못가는 장소이면 queue에 담아두기
            Queue<Integer> q = new LinkedList<>();
            Queue<Integer> preventedq = new LinkedList<>();
            int total = 0;
            total+=bfs(0, w+2, h+2, q, preventedq, keys, visited, arr);

            while (!preventedq.isEmpty()) {
                int size = preventedq.size();
                boolean progressMade = false;
//                for (int i=0;i<h+2;i++) {
//                    System.out.println(Arrays.toString(visited[i]));
//                }
                for (int i = 0; i < size; i++) {
                    int start = preventedq.poll();
                    if (keys[arr[start / (w+2)][start % (w+2)] - 'A']) {
                        total += bfs(start, w+2, h+2, q, preventedq, keys, visited, arr);
                        progressMade = true; // 진행이 있었음을 표시
                    } else {
                        preventedq.add(start);
                    }
                }

                if (!progressMade) {
                    // 더 이상 열쇠를 얻거나 진행할 수 없는 경우 루프 종료
                    break;
                }
            }
            System.out.println(total);
        }
    }
    static int bfs(int start, int w, int h, Queue<Integer> q, Queue<Integer> preventedq, boolean[] keys, boolean[][] visited, char[][] arr) {
        q.add(start);
        int cnt = 0;
        while (!q.isEmpty()) {
            int n = q.poll();
            int r = n/w;
            int c = n%w;
            for (int[] move:MOVE) {
                int nr = r+move[0];
                int nc = c+move[1];
                if (nr<0 || nr>=h || nc<0 || nc>=w || visited[nr][nc]) {
                    continue;
                } else if (arr[nr][nc] == '$') {
                    cnt++;
                    visited[nr][nc] = true;
                    q.add(nr*w+nc);
                } else if (arr[nr][nc]>='A' && arr[nr][nc]<='Z') {
                    if (keys[arr[nr][nc]-'A']) {
                        visited[nr][nc] = true;
                        q.add(nr*w+nc);
                    } else {
                        //System.out.println(arr[nr][nc]);
                        preventedq.add(nr*w+nc);
                        continue;
                    }
                } else if (arr[nr][nc]>='a' && arr[nr][nc]<='z') {
                    keys[arr[nr][nc]-'a'] = true;
                    visited[nr][nc] = true;
                    q.add(nr*w+nc);
                }
                else {
                    visited[nr][nc] = true;
                    q.add(nr*w+nc);
                }
            }
        }
        return cnt;
    }
}
