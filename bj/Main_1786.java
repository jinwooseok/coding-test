import java.io.*;
import java.util.*;
public class Main_1786 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String T = br.readLine();
        String P = br.readLine();
        //table 만들기 - 0, 0, 0, 0, 1, 2, 0
        int[] table = new int[P.length()];
        table[0] = 0;
        int i = 0; int j = 1;
        while (j!=table.length) {
            if (P.charAt(i)==P.charAt(j)) {
                i++;
                table[j] = i;
                j++;
            } else {
                if (i!=0) {
                    i = table[i-1];
                } else {
                    table[j]=0;
                    j++;
                }
            }
        }


        //System.out.println(Arrays.toString(table));
        i=0; j=0;
        int pCnt = 0;
        StringBuilder sb = new StringBuilder();
        while (i<T.length()) {
            //일치했으면 올린다.
            //System.out.println(T.charAt(i) + " "+P.charAt(j)+" "+j);
            if (T.charAt(i)==P.charAt(j)) {
                //System.out.println(T.charAt(i) + " "+P.charAt(j));
                i++;
                j++;
                if (j==P.length()) {
                    pCnt++;
                    sb.append(i-j+1).append(" ");
                    j = table[j-1];
                }
            } else { //틀렸으면 몇개까지 셌는지 확인
                if (j>0) {
                    j = table[j-1];
                } else {
                    i++;
                }

            }
        }
        System.out.println(pCnt);
        System.out.println(sb.toString());
    }
}
