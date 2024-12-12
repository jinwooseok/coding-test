import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2162 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] lines = new int[N][4];
        for (int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0;j<4;j++) {
                lines[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] groups = new int[N];
        for (int i=0;i<N;i++) {
            groups[i] = i;
        }
        for (int i=0;i<N;i++) {
            for (int j=i+1;j<N;j++) {
                if (isConnect(lines[i],lines[j])) {
                    union(groups, i, j);
                }
            }
        }
        int[] results = new int[N];
        int maxSize = 0;
        int groupCnt = 0;
        for (int i=0;i<N;i++) {
            find(groups,i);
        }
        for (int i=0;i<N;i++) {
            results[groups[i]]++;
            maxSize = Math.max(maxSize, results[groups[i]]);
        }
        for (int i=0;i<N;i++) {
            if (results[i]!=0) groupCnt++;
        }
        System.out.println(groupCnt);
        System.out.println(maxSize);
    }

    public static int find(int[] parents, int x) {
        if (parents[x] == x) {
            return x;
        } else {
            return parents[x] = find(parents, parents[x]);
        }
    }

    public static void union(int[] parents, int a, int b) {
        int parentA = find(parents, a);
        int parentB = find(parents, b);
        if (parentA != parentB) {
            parents[parentB] = parentA;
        }
    }

    public static int ccw(int x1, int y1, int x2, int y2, int px, int py) {
        return Integer.compare((px-x1)*(py-y2)-(px-x2)*(py-y1),0);
    }

    public static boolean isSegment(int x1, int y1, int x2, int y2, int px, int py) {
        return (Math.min(x1,x2)<=px) && (Math.max(x1,x2)>=px) && (Math.min(y1,y2)<=py) && (Math.max(y1,y2)>=py);
    }

    public static boolean isConnect(int[] line1, int[] line2) {
        int x1 = line1[0];
        int y1 = line1[1];
        int x2 = line1[2];
        int y2 = line1[3];
        int x3 = line2[0];
        int y3 = line2[1];
        int x4 = line2[2];
        int y4 = line2[3];

        int d1 = ccw(x3,y3,x4,y4,x1,y1);
        int d2 = ccw(x3,y3,x4,y4,x2,y2);
        int d3 = ccw(x1,y1,x2,y2,x3,y3);
        int d4 = ccw(x1,y1,x2,y2,x4,y4);

        if ((d1*d2)<0 && (d3*d4)<0) {
            return true;
        } else if (
                (d1==0 && isSegment(x3,y3,x4,y4,x1,y1)) ||
                (d2==0 && isSegment(x3,y3,x4,y4,x2,y2)) ||
                (d3==0 && isSegment(x1,y1,x2,y2,x3,y3)) ||
                (d4==0 && isSegment(x1,y1,x2,y2,x4,y4))
        ) {
            return true;
        } else {
            return false;
        }
    }
}
