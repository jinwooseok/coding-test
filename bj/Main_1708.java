import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1708 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        Point root = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        Point[] points = new Point[N];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            points[i] = new Point(Long.parseLong(st.nextToken()),Long.parseLong(st.nextToken()));
            if (points[i].y < root.y) {
                root = points[i];
            } else if (points[i].y == root.y) {
                if (points[i].x < root.x) {
                    root = points[i];
                }
            }
        }
        //첫 점은 y축이 가장낮고 x축이 가장 낮은 것

        //x축이 가장 작은거부터 시작. x축과 첫 점을 기준으로 각도가 작은 순서.
        //atan2순서대로 정렬
        for (Point point: points) {
            point.setAtan2(root);
        }
        Arrays.sort(points);
        //stack에 넣으면서 ccw점검.
        Stack<Point> stack = new Stack<>();
        stack.push(root); // 첫 점 추가
//        System.out.println(root);
        for (Point point : points) {
            // 중복된 root를 처리하지 않도록 조건 추가
            if (point == root) continue;

            while (stack.size() > 1 && ccw(stack.get(stack.size() - 2), stack.peek(), point) <= 0) {
                stack.pop();
            }
            stack.push(point);
        }
        System.out.println(stack.size());
    }
    static long ccw(Point p1, Point p2, Point p3) {
        return (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x);
    }
    static class Point implements Comparable<Point> {
        long x;
        long y;
        double atan2;

        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
        void setAtan2(Point root) {
            this.atan2 = Math.atan2(this.y-root.y,this.x-root.x);
        }

        @Override
        public int compareTo(Point o) {
            if (this.atan2 != o.atan2) {
                return Double.compare(this.atan2, o.atan2);
            } else {
                return Long.compare(distanceFromOrigin(), o.distanceFromOrigin());
            }
        }
        long distanceFromOrigin() {
            return x * x + y * y;
        }

        @Override
        public String toString() {
            return x+" "+y+" "+atan2;
        }
    }
}
