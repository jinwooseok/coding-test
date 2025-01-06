import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2357 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int[] arr = new int[N];
    int[][] tree = new int[4*N][2];
    for (int i=0;i<N;i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }
    init(tree, arr, 0, N-1, 1);

    for (int i=0;i<M;i++) {
      st = new StringTokenizer(br.readLine());
      int left = Integer.parseInt(st.nextToken())-1;
      int right = Integer.parseInt(st.nextToken())-1;
      System.out.println(getMin(tree, 0, N-1, left, right, 1) + " " + getMax(tree, 0, N-1, left, right, 1));
    }
  }

  static void init(int[][] tree, int[] arr, int start, int end, int node) {
    if (start == end) {
      tree[node][0] = arr[start];
      tree[node][1] = arr[start];
      return;
    }
    int mid = (start+end)/2;
    init(tree, arr, start, mid, node*2);
    init(tree, arr, mid+1, end, node*2+1);
    tree[node][0] = Math.min(tree[node*2][0], tree[node*2+1][0]);
    tree[node][1] = Math.max(tree[node*2][1], tree[node*2+1][1]);
  }

  static int getMax(int[][] tree, int start, int end, int left, int right, int node) {
    if (right < start || left > end) return -1;
    if (left <= start && end <= right) return tree[node][1];
    int mid = (start+end)/2;
    return Math.max(getMax(tree, start, mid, left, right, node*2),getMax(tree, mid+1, end, left, right, node*2+1));
  }

  static int getMin(int[][] tree, int start, int end, int left, int right, int node) {
    if (right < start || left > end) return Integer.MAX_VALUE;
    if (left <= start && end <= right) return tree[node][0];
    int mid = (start+end)/2;
    return Math.min(getMin(tree, start, mid, left, right, node*2),getMin(tree, mid+1, end, left, right, node*2+1));
  }
}
