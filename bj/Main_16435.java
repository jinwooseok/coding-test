import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main_16435 {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i=0;i<N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr); //작은 것부터 먹는 것을 가정하기 위해 정렬을 해두는 게 좋을 듯 싶다.
        //하다가 길이가 부족하면 출력 후 브레잌
        for (int i=0;i<arr.length;i++) {
            if (arr[i]<=M) {
                M++;
            } else {
                System.out.println(M);
                break;
            }
            //끝까지 갔으면 출력은 하고가~
            if (i==arr.length-1) {
                System.out.println(M);
            }
        }

    }
}
