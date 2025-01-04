import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2042 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    //입력
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int K = Integer.parseInt(st.nextToken());
    long[] arr = new long[N];
    long[] tree = new long[N*4];

    for (int i=0;i<N;i++) {
      arr[i] = Long.parseLong(br.readLine());
    }
    init(tree, arr, 0, N-1, 1);

    for (int i=0;i<M+K;i++) {
      st = new StringTokenizer(br.readLine());
      int type = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      long c = Long.parseLong(st.nextToken());
      if (type == 1) {
        update(tree, 0, N-1, b-1, c-arr[b-1], 1);
        arr[b-1] = c;
      } else {
        int right = (int) c-1;
        System.out.println(search(tree, 0, N-1, b-1, right, 1));
      }
    }
  }

  //초기 부분합 트리 설정
  static long init(long[] tree, long[] arr, int start, int end, int node) {
    if (start == end) {
      tree[node] = arr[start];
      return tree[node];
    }
    int mid = (start+end)/2;
    return tree[node] = init(tree, arr, start, mid, node*2) + init(tree, arr,mid+1, end, node*2+1);
  }
  //부분합 트리 사용
  static long search(long[] tree, int start, int end, int left, int right, int node) {
    if (end < left || right < start) return 0;

    if (left <= start && end <= right) return tree[node];

    int mid = (start+end)/2;

    return search(tree, start, mid, left, right, node*2) + search(tree, mid+1, end, left, right, node*2+1);
  }
  //부분합 트리 업데이트
  static void update(long[] tree, int start, int end, int idx, long diff, int node) {
    if (idx < start || end < idx) return;
    int mid = (start+end)/2;
    tree[node]+=diff;
    if (start == end) return;
    update(tree, start, mid, idx, diff, node*2);
    update(tree, mid+1, end, idx, diff, node*2+1);
  }
}
