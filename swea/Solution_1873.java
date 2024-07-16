import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1873 {
    static char[][] map;
    static StringTokenizer st;
    static int H;
    static int W;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t=1;t<T+1;t++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new char[H][W];
            for (int i=0;i<H;i++) {
                map[i] = br.readLine().toCharArray();
            }
            st = new StringTokenizer(br.readLine());
            char[] commands = br.readLine().toCharArray();
            int curR = -1;
            int curC = -1;
            for (int i=0;i<H;i++) {
                for (int j=0;j<W;j++) {
                    if (map[i][j]=='>'||map[i][j]=='<'||map[i][j]=='^'||map[i][j]=='v') {
                        curR = i;
                        curC = j;
                    }
                }
            }
            performCommand(commands, curR,curC);
            System.out.print("#"+t+" ");
            for (int i=0;i<H;i++) {
                System.out.println(String.valueOf(map[i]));
            }
        }
    }

    public static void performCommand(char[] commands, int r, int c) {
        for (char command : commands) {
            if (command == 'U') {
                map[r][c] = '^';
                if (r - 1 < 0 || map[r - 1][c] == '*' || map[r - 1][c] == '#' || map[r - 1][c] == '-') continue;
                map[r][c] = '.';
                map[r - 1][c] = '^';
                r--;
            } else if (command == 'D') {
                map[r][c] = 'v';
                if (r + 1 >= H || map[r + 1][c] == '*' || map[r + 1][c] == '#' || map[r + 1][c] == '-') continue;
                map[r][c] = '.';
                map[r + 1][c] = 'v';
                r++;
            } else if (command == 'L') {
                map[r][c] = '<';
                if (c - 1 < 0 || map[r][c - 1] == '*' || map[r][c - 1] == '#' || map[r][c - 1] == '-') continue;
                map[r][c] = '.';
                map[r][c - 1] = '<';
                c--;
            } else if (command == 'R') {
                map[r][c] = '>';
                if (c + 1 >= W || map[r][c + 1] == '*' || map[r][c + 1] == '#' || map[r][c + 1] == '-') continue;
                map[r][c] = '.';
                map[r][c + 1] = '>';
                c++;
            } else if (command == 'S') {
                int axis = 0;
                int direction = 0;
                if (map[r][c] == '<') {
                    axis = 1;
                    direction = -1;
                } else if (map[r][c] == '>') {
                    axis = 1;
                    direction = 1;
                } else if (map[r][c] == '^') {
                    axis = 0;
                    direction = -1;
                } else if (map[r][c] == 'v') {
                    axis = 0;
                    direction = 1;
                }
                shooting(axis, direction, r, c);
            }
        }
    }

    public static void shooting(int axis, int direction, int r, int c) {
        if (axis == 0) {
            while (r>=0 && r<=H-1) {
                if (map[r][c]=='*') {
                    map[r][c] = '.';
                    break;
                }
				else if (map[r][c]=='#') break;
                r+=direction;
            }
        }
        else if (axis == 1) {
            while (c>=0 && c<=W-1) {
                if (map[r][c]=='*') {
                    map[r][c] = '.';
                    break;
                }
				else if (map[r][c]=='#') break;
                c+=direction;
            }
        }
    }
}