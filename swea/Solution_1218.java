import java.io.*;
import java.util.*;
public class Solution_1218 {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int t=1;t<11;t++) {
            int len = Integer.parseInt(br.readLine());
            String[] inputString = br.readLine().split("");
            Deque<String> deque = new LinkedList<>();
            for (int i=0;i<inputString.length;i++) {
                String str = inputString[i];

                if (str.equals("{") || str.equals("[") ||str.equals("<") ||str.equals("(")) {
                    deque.addLast(str);
                } else {
                    String str2 = deque.pollLast();
                    if (str2.equals("{") && str.equals("}")) {
                        continue;
                    }
                    if (str2.equals("[") && str.equals("]")) {
                        continue;
                    }
                    if (str2.equals("<") && str.equals(">")) {
                        continue;
                    }
                    if (str2.equals("(") && str.equals(")")) {
                        continue;
                    }
                    break;
                }
            }
            if (deque.isEmpty()) {
                System.out.println("#"+t+" "+1);
            } else {
                System.out.println("#"+t+" "+0);
            }
        }
    }
}