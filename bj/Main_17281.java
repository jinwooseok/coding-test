import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17281 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int inningCnt = Integer.parseInt(br.readLine().trim());
        int[][] arr = new int[inningCnt][9];
        for (int i=0;i<inningCnt;i++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int j=0;j<9;j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int[] playSeq = new int[]{1,2,3,4,5,6,7,8,9};
        //이닝들 동안 진행하면서 얻은 점수 계산
        int maxScore = 0;
        do {
            if (playSeq[3]!=1) {
                continue;
            }
            int score = 0;
            int startPoint = 0;
            for (int i=0;i<inningCnt;i++) {
                int[] result=getInningResult(arr[i], playSeq, startPoint);
                score += result[0];
                startPoint = result[1];
            }
            maxScore = Math.max(maxScore, score);
        } while(permutation(playSeq));
        System.out.println(maxScore);
    }

    private static boolean permutation(int[] playSeq) {
        int i = playSeq.length - 1;
        while (i>0 && playSeq[i-1]>=playSeq[i]) --i;
        if (i==0) {
            return false;
        }

        int j = playSeq.length - 1;
        while (playSeq[i-1]>=playSeq[j]) --j;

        swap(playSeq, i-1, j);

        int k = playSeq.length-1;
        while (i<k) {
            swap(playSeq,i++,k--);
        }
        return true;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private static int[] getInningResult(int[] arr, int[] playSeq, int startPoint) {
        int outCount = 0;
        int score = 0;
        int i=startPoint;
        int status = 0;

        while (outCount<3) {
            int playerResult = arr[playSeq[i++%9]-1];
            if (playerResult == 0) {
                outCount++;
            } else{
                status<<=playerResult;
                status |= 1<<(playerResult-1);
            }
            for (int j=3;j<8;j++) {
                if ((status&(1<<j))!=0) {
                    score++;
                    status^=(1<<j);
                }
            }
        }
        return new int[]{score, i%9};
    }
}
