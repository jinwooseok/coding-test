import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14428 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    int N = Integer.parseInt(br.readLine());

    int[] arr = new int[N];
    int[][] tree = new int[N*4][2];
    st = new StringTokenizer(br.readLine());
    StringBuilder sb = new StringBuilder();
    for (int i=0;i<N;i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    init(arr, tree, 0, N-1, 1);

    int M = Integer.parseInt(br.readLine());

    for (int i=0;i<M;i++) {
      st = new StringTokenizer(br.readLine());
      int command = Integer.parseInt(st.nextToken());
//            for (int[] num:tree) {
//                System.out.println(Arrays.toString(num));
//            }
      if (command == 1) {
        int idx = Integer.parseInt(st.nextToken());
        int value = Integer.parseInt(st.nextToken());
        update(tree, 0, N-1, idx-1, value, 1);
      } else {
        int left = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());
        sb.append(getMinIdx(tree, 0, N-1, left-1, right-1, 1)[0]+1).append("\n");
        //System.out.println(getMinIdx(tree, 0, N-1, left-1, right-1, 1)[1]);
      }
    }
    System.out.println(sb);
  }

  static void init(int[] arr, int[][] tree, int start, int end, int node) {
    if (start == end) {
      tree[node][0] = start;
      tree[node][1] = arr[start];
      return;
    }
    int mid = (start+end)/2;
    init(arr, tree, start, mid, node*2);
    init(arr, tree, mid+1, end, node*2+1);

    if (tree[node*2][1] <= tree[node*2+1][1]) {
      tree[node][0] = tree[node*2][0];
      tree[node][1] = tree[node*2][1];
    } else {
      tree[node][0] = tree[node*2+1][0];
      tree[node][1] = tree[node*2+1][1];
    }
  }

  static void update(int[][] tree, int start, int end, int idx, int value, int node) {
    if (idx < start || idx > end) {
      return;
    }
    if (start == end) {
      tree[node][0] = idx;
      tree[node][1] = value;
      return;
    }

    int mid = (start+end)/2;

    update(tree, start, mid, idx, value, node*2);
    update(tree, mid+1, end, idx, value, node*2+1);

    if (tree[node*2][1] < tree[node*2+1][1] ||
        (tree[node*2][1] == tree[node*2+1][1] && tree[node*2][0] < tree[node*2+1][0])) {
      tree[node][0] = tree[node*2][0];
      tree[node][1] = tree[node*2][1];
    } else {
      tree[node][0] = tree[node*2+1][0];
      tree[node][1] = tree[node*2+1][1];
    }
  }

  static int[] getMinIdx(int[][] tree, int start, int end, int left, int right, int node) {
    if (start > right || end < left) {
      return new int[] {-1, Integer.MAX_VALUE};
    }

    if (left <= start && right >= end) {
      return tree[node];
    }
    int mid = (start+end)/2;
    int[] leftNode = getMinIdx(tree, start, mid, left, right, node*2);
    int[] rightNode = getMinIdx(tree, mid+1, end, left, right, node*2+1);
    if (leftNode[1] < rightNode[1] ||
        (leftNode[1] == rightNode[1] && leftNode[0] < rightNode[0])) {
      return leftNode;
    } else {
      return rightNode;
    }
  }
}
