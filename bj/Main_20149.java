import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main_20149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] arr = new int[2][4];
        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] d1 = {arr[0][0], arr[0][1]};
        int[] d2 = {arr[0][2], arr[0][3]};
        int[] d3 = {arr[1][0], arr[1][1]};
        int[] d4 = {arr[1][2], arr[1][3]};

        long ccw1 = ccw(d1[0], d1[1], d2[0], d2[1], d3[0], d3[1]);
        long ccw2 = ccw(d1[0], d1[1], d2[0], d2[1], d4[0], d4[1]);
        long ccw3 = ccw(d3[0], d3[1], d4[0], d4[1], d1[0], d1[1]);
        long ccw4 = ccw(d3[0], d3[1], d4[0], d4[1], d2[0], d2[1]);

        boolean isIntersect = ccw1 * ccw2 < 0 && ccw3 * ccw4 < 0;

        if (ccw1 == 0 && isOnSegment(d1, d2, d3) || ccw2 == 0 && isOnSegment(d1, d2, d4) || ccw3 == 0 && isOnSegment(d3, d4, d1) || ccw4 == 0 && isOnSegment(d3, d4, d2)) {
            isIntersect = true;
        }

        StringBuilder sb = new StringBuilder();
        if (isIntersect) {
            // 교차점 계산 및 출력
            System.out.println(1);
            if (ccw1 * ccw2 < 0 && ccw3 * ccw4 < 0) {
                double[] intersection = getIntersection(d1, d2, d3, d4);
                sb.append(formatResult(intersection[0])).append(" ").append(formatResult(intersection[1]));
            } else {
                // 끝점이 겹치거나 완전히 겹치는 경우 처리
                Set<String> set = new HashSet<>();
                if (ccw1 == 0 && isOnSegment(d1, d2, d3)) {
                    set.add(d3[0] + " " + d3[1]);
                }
                if (ccw2 == 0 && isOnSegment(d1, d2, d4)) {
                    set.add(d4[0] + " " + d4[1]);
                }
                if (ccw3 == 0 && isOnSegment(d3, d4, d1)) {
                    set.add(d1[0] + " " + d1[1]);
                }
                if (ccw4 == 0 && isOnSegment(d3, d4, d2)) {
                    set.add(d2[0] + " " + d2[1]);
                }

                if (set.size() == 1) {
                    for (String nums : set) {
                        sb.append(nums);
                    }
                }
            }
        } else {
            System.out.println(0);
        }

        if (sb.length() > 0) {
            System.out.println(sb);
        }
    }

    public static long ccw(long x1, long y1, long x2, long y2, long x3, long y3) {
        return Long.compare((x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1), 0);
    }


    public static boolean isOnSegment(int[] d1, int[] d2, int[] point) {
        int x1 = d1[0], y1 = d1[1], x2 = d2[0], y2 = d2[1];
        int px = point[0], py = point[1];

        return Math.min(x1, x2) <= px && px <= Math.max(x1, x2) &&
                Math.min(y1, y2) <= py && py <= Math.max(y1, y2);
    }

    public static double[] getIntersection(int[] d1, int[] d2, int[] d3, int[] d4) {
        double a1 = d2[1] - d1[1];
        double b1 = d1[0] - d2[0];
        double c1 = a1 * d1[0] + b1 * d1[1];

        double a2 = d4[1] - d3[1];
        double b2 = d3[0] - d4[0];
        double c2 = a2 * d3[0] + b2 * d3[1];

        double determinant = a1 * b2 - a2 * b1;

        if (determinant == 0) {
            return new double[]{};
        } else {
            double x = (b2 * c1 - b1 * c2) / determinant;
            double y = (a1 * c2 - a2 * c1) / determinant;
            return new double[]{x, y};
        }
    }

    public static String formatResult(double value) {
        if (Math.abs(value - Math.round(value)) < 1e-9) {
            return String.valueOf((int) Math.round(value)); // 정수로 변환
        } else {
            return String.valueOf(value);
        }
    }
}
