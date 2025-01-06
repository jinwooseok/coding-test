import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11505 {
  static int CONST = 1000000007;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    long[] arr = new long[N];
    long[] tree = new long[N*4];

    for (int i=0;i<N;i++) {
      arr[i] = Long.parseLong(br.readLine());
    }
    init(arr, tree, 0, N-1, 1);
    for (int i=0;i<M+K;i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      if (a==1) {
        long diff = c % CONST;
        arr[b-1] = c;
        update(tree, 0, N - 1, b - 1, diff, 1);
      } else {
        System.out.println(getMul(tree, 0, N - 1, b - 1, c - 1, 1)%CONST);
      }
    }
  }

  static long init(long[] arr, long[] tree, int start, int end, int node) {
    if (start == end) {
      tree[node] = arr[start];
      return tree[node];
    }
    int mid = (start+end)/2;
    return tree[node] = ((init(arr, tree, start, mid, node*2) % CONST)
        * (init(arr, tree, mid+1, end, node*2+1) % CONST)) % CONST;

  }
  static void update(long[] tree, int start, int end, int idx, long diff, int node) {
    if (start>idx || end<idx) {
      return;
    }
    tree[node] = diff;
    int mid = (start+end)/2;
    if (start == end) return;
    update(tree, start, mid, idx, diff, node*2);
    update(tree, mid+1, end, idx, diff, node*2+1);
    tree[node] = (tree[node*2] * tree[node*2+1]) % CONST;
  }

  static long getMul(long[] tree, int start, int end, int left, int right, int node) {
    if (end < left || right < start) {
      return 1;
    }
    if (left <= start && end <= right) {
      return tree[node];
    }
    int mid = (start+end)/2;
    return (getMul(tree, start, mid, left, right, node*2) % CONST
        * getMul(tree, mid+1, end, left, right, node*2+1) % CONST) % CONST;

  }
}
