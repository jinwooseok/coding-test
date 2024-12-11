import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17387 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 선분 정보 입력
        long[][] line = new long[2][4];
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                line[i][j] = Long.parseLong(st.nextToken());
            }
        }

        // 두 선분의 CCW 계산
        long d1 = ccw(line[0][0], line[0][1], line[0][2], line[0][3], line[1][0], line[1][1]);
        long d2 = ccw(line[0][0], line[0][1], line[0][2], line[0][3], line[1][2], line[1][3]);
        long d3 = ccw(line[1][0], line[1][1], line[1][2], line[1][3], line[0][0], line[0][1]);
        long d4 = ccw(line[1][0], line[1][1], line[1][2], line[1][3], line[0][2], line[0][3]);
        // 교차 여부 판단
        if (d1 * d2 < 0 && d3 * d4 < 0) {
            System.out.println(1); // 교차
        } else if ((d1 == 0 && isOnSegment(line[0][0], line[0][1], line[0][2], line[0][3], line[1][0], line[1][1]))  ||
                (d2 == 0 && isOnSegment(line[0][0], line[0][1], line[0][2], line[0][3], line[1][2], line[1][3]))||
                (d3 == 0 && isOnSegment(line[1][0], line[1][1], line[1][2], line[1][3], line[0][0], line[0][1])) ||
                (d4 == 0 && isOnSegment(line[1][0], line[1][1], line[1][2], line[1][3], line[0][2], line[0][3]))) {
            System.out.println(1); // 선분 위에 있는 경우 (끝점 제외)
        } else {
            System.out.println(0); // 교차하지 않음
        }
    }

    // CCW 계산 함수
    public static long ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        return Long.compare((x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1),0);
    }

    // 점이 선분 위에 있는지 확인하는 함수
    public static boolean isOnSegment(long x1, long y1, long x2, long y2, long px, long py) {
        return Math.min(x1, x2) <= px && px <= Math.max(x1, x2) &&
                Math.min(y1, y2) <= py && py <= Math.max(y1, y2);
    }
}
//9 3 4 3
//1 3 3 3