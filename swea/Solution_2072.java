import java.io.*;
import java.util.*;
public class Solution_2072 {

    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int t=1;t<T+1;t++) {
            st = new StringTokenizer(br.readLine());
            int sum = 0;
            while (st.countTokens() != 0) {
                int num = Integer.parseInt(st.nextToken());
                if (num%2==1){
                    sum+=num;
                }
            }
            System.out.println("#"+t+" "+sum);
        }
    }
}