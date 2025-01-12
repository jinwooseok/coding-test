import java.util.Arrays;
import java.util.Scanner;

public class Main_15824 {
  static long[] arr;
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = Integer.parseInt(sc.nextLine());
    arr = new long[N];
    for (int i=0;i<N;i++) {
      arr[i] = sc.nextLong();
    }
    //정렬
    Arrays.sort(arr);
    long result = 0;
    for (int i=0;i<N;i++) {
      result+=arr[i]*((pow(i,2)-1)%1_000_000_007)%1_000_000_007;
      result%=1_000_000_007;
      result-=arr[i]*((pow(N-i-1, 2)-1)%1_000_000_007)%1_000_000_007;
      result%=1_000_000_007;
    }
    System.out.println((result+1_000_000_007)%1_000_000_007);
  }
  //분할정복을 이용한 거듭제곱
  static long pow(int square, long num) {
    long result = 1;
    while (square > 0) {
      if (square % 2 == 1) {
        result = (result * num) % 1_000_000_007;
      }
      num = (num * num) % 1_000_000_007;
      square /= 2;
    }

    return result % 1_000_000_007;
  }
}
