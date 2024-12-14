import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_16566 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 1이상 N이하
        int M = Integer.parseInt(st.nextToken()); // M개의 자연수
        int K = Integer.parseInt(st.nextToken()); // K개의 자연수

        int[] arr = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i=0;i<M;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //부모배열
        int[] parents = new int[M+1];
        for (int i=0;i<=M;i++) {
            parents[i] = i;
        }
        //시행횟수
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        //이진 탐색을 위한 정렬
        Arrays.sort(arr);
        for (int i=0;i<K;i++) {
            //이진 탐색을 통해 정렬된 전체숫자에서 최소가 해당숫자인 부분을 찾음.
            int minIdx = binarySearch(arr, Integer.parseInt(st.nextToken()));
            //분리집합으로 이미 낸 카드를 그 다음 카드로 연결짓도록 한다. 즉, 부모가 더 큰쪽을 향하도록 한다.
            sb.append(arr[parents[minIdx]]).append("\n");
            //System.out.println(Arrays.toString(parents));
            union(parents, minIdx, parents[minIdx]+1);
        }
        System.out.println(sb);
    }
    public static int find(int[] parents, int x) {
        if (parents[x] == x) {
            return x;
        } else {
            return parents[x] = find(parents, parents[x]);
        }
    }
    public static void union(int[] parents, int a, int b) {
        int aParent = find(parents,a);
        int bParent = find(parents,b);

        if (aParent > bParent) {
            parents[bParent] = aParent;
            find(parents, b);
        } else {
            parents[aParent] = bParent;
            find(parents, a);
        }

    }
    //lower bound
    public static int binarySearch(int[] arr, int target) {
        int left = 0; int right = arr.length-1;
        while (left<=right) {
            int mid = (left+right)/2;
            if (arr[mid] < target) {
                left = mid+1;
            } else if (arr[mid] > target) {
                right = mid-1;
            } else {
                return mid+1;
            }
        }
        return left;
    }
}
// 2 3 4 5 7 8 9