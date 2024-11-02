import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_1288 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t=1;t<=T;t++) {
            int visited = 0;
            int N = Integer.parseInt(br.readLine());
            int i = 1;
            while (true) {
                int n = N*i;
                char[] nchar = String.valueOf(n).toCharArray();
                for (char ch : nchar) {
                    //System.out.println(ch);
                    visited |= 1<<ch-'0';
                }
                System.out.println(Integer.toBinaryString(visited));
                if (visited == (1<<10)-1) {
                    System.out.println("#"+t+" "+N*i);
                    break;
                }
                i++;
            }
        }
    }
}
