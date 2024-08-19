import java.io.*;

public class Main_2023 {

    public static void main(String[] args) throws IOException{
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        //자리수에 맞게 소수를 찾음
        int maxNum = (int)Math.pow(10, N);
        for (int i=maxNum/10;i<maxNum;i++) {
            if (isSpecialNum(i, N)) sb.append(i).append('\n');
        }
        //소수를 찾고 소수별로 검증을 진행한다.
        System.out.println(sb);
    }

    //소수점별로 10의 자리수를 나눠서 소수인지 아닌지 검증함으로서 증명한다.
    private static boolean isSpecialNum(int num, int N) {
        for (int i=N-1;i>=0;i--) {
            int curNum = (int) (num/Math.pow(10,i));
            if (curNum == 1) {
                return false;
            } else if (curNum == 2) {
                continue;
            } else if (curNum == 3) {
                continue;
            } else if (curNum == 5) {
                continue;
            } else if (curNum == 7) {
                continue;
            } else if (curNum%2 == 0) {
                return false;
            } else if (curNum%3 == 0) {
                return false;
            } else if (curNum%5 == 0) {
                return false;
            } else if (curNum%7 == 0) {
                return false;
            } else if (curNum%11 == 0) {
                return false;
            } else if (curNum%13 == 0) {
                return false;
            } else if (curNum%17 == 0) {
                return false;
            } else if (curNum%19 == 0) {
                return false;
            }
            if (!isPrime(curNum)) {
                return false;
            }
        }
        return true;
    }
    private static boolean isPrime(int num) {
        for (int i=2;i*i<=num;i++) {
            if (num%i==0) return false;
        }
        return true;
    }
}
