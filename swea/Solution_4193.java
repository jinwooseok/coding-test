import java.io.*;
import java.util.*;

public class Solution_4193 {
    public static void main(String args[]) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String line = reader.readLine();
        if (line == null || line.isEmpty()) return;
        int T = Integer.parseInt(line);

        for (int k = 0; k < T; k++) {
            line = reader.readLine();
            if (line == null || line.isEmpty()) return;
            int N = Integer.parseInt(line);
            StringTokenizer token;
            int[][] arr = new int[N][N];
            for (int i = 0; i < N; i++) {
                line = reader.readLine();
                token = new StringTokenizer(line);
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(token.nextToken());
                }
            }

            line = reader.readLine();
            token = new StringTokenizer(line);
            int st_r = Integer.parseInt(token.nextToken());
            int st_c = Integer.parseInt(token.nextToken());

            line = reader.readLine();
            token = new StringTokenizer(line);
            int ar_r = Integer.parseInt(token.nextToken());
            int ar_c = Integer.parseInt(token.nextToken());

            System.out.println("#" + (k + 1) + " " + bfs(arr, N, st_r, st_c, ar_r, ar_c));
        }
    }

    public static int bfs(int[][] arr, int N, int st_r, int st_c, int ar_r, int ar_c) {
        int[][] MOVE = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        Deque<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        queue.addLast(new int[]{st_r, st_c, 0});

        while (!queue.isEmpty()) {
            int[] cur = queue.pollFirst();
            int r = cur[0];
            int c = cur[1];
            int t = cur[2];

            for (int[] move : MOVE) {
                int new_r = r + move[0];
                int new_c = c + move[1];
                if (new_r == ar_r && new_c == ar_c) {
                    return t+1;
                }
                if (new_r < 0 || new_r >= N || new_c < 0 || new_c >= N) continue;
                if (arr[new_r][new_c] == 1) continue;
                else if (arr[new_r][new_c] == 2){
                    if ((t+1)%3 != 0) {
                        visited[r][c] = true;
                        queue.addLast(new int[]{r, c, t + 1});
                    }
                    else {
                        visited[new_r][new_c] = true;
                        queue.addLast(new int[]{new_r, new_c, t + 1});
                    }
                }
                else {
                    if (!visited[new_r][new_c]) {
                        visited[new_r][new_c] = true;
                        queue.addLast(new int[]{new_r, new_c, t + 1});
                    }
                }
            }
        }
        return -1;
    }
}



