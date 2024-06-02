import java.util.*;
class Solution {
    //bfs. 1열 혹은 1행을 전부 거꾸로 바꾼다. 변경사항은 가져가야한다.
    public int solution(int[][] beginning, int[][] target) {
        int[] answer = new int[4];
        int[][] tmpArray = new int[beginning.length][beginning[0].length];

        deepCopy(tmpArray, beginning);
        for (int i=0;i<tmpArray.length;i++){
            if(tmpArray[i][0]!=target[i][0]) {
                rotate(tmpArray, 0, i);
                answer[0]++;
            }
        }
        for (int i=0;i<tmpArray[0].length;i++){
            if(tmpArray[0][i]!=target[0][i]) {
                rotate(tmpArray, 1, i);
                answer[0]++;
            }
        }
        if (!isTrue(tmpArray, target)) answer[0] = 999999;

        deepCopy(tmpArray, beginning);
        for (int i=0;i<tmpArray[0].length;i++){
            if(tmpArray[0][i]!=target[0][i]) {
                rotate(tmpArray, 1, i);
                answer[1]++;
            }
        }
        for (int i=0;i<tmpArray.length;i++){
            if(tmpArray[i][0]!=target[i][0]) {
                rotate(tmpArray, 0, i);
                answer[1]++;
            }
        }
        if (!isTrue(tmpArray, target)) answer[1] = 999999;

        deepCopy(tmpArray, beginning);
        for (int i=0;i<tmpArray[0].length;i++){
            if(tmpArray[0][i]==target[0][i]) {
                rotate(tmpArray, 1, i);
                answer[2]++;
            }
        }
        for (int i=0;i<tmpArray.length;i++){
            if(tmpArray[i][0]!=target[i][0]) {
                rotate(tmpArray, 0, i);
                answer[2]++;
            }
        }
        if (!isTrue(tmpArray, target)) answer[2] = 999999;

        deepCopy(tmpArray, beginning);
        for (int i=0;i<tmpArray.length;i++){
            if(tmpArray[i][0]!=target[i][0]) {
                rotate(tmpArray, 0, i);
                answer[3]++;
            }
        }
        for (int i=0;i<tmpArray[0].length;i++){
            if(tmpArray[0][i]==target[0][i]) {
                rotate(tmpArray, 1, i);
                answer[3]++;
            }
        }
        if (!isTrue(tmpArray, target)) answer[3] = 999999;


        //System.out.println(answer1+" "+answer2);
        int minCount = 999999;
        for (int ans: answer){ if(ans<minCount) minCount=ans; }
        if (minCount == 999999) return -1;
        else return minCount;
    }

    void deepCopy(int[][] array, int[][] target){
        for (int i=0;i<array.length;i++){
            for (int j=0;j<array[0].length;j++){
                array[i][j]=target[i][j];
            }
        }
    }

    void rotate(int[][] array, int axis, int num){
        if (axis==0){
            for (int i=0; i<array[0].length;i++){
                array[num][i]=1-array[num][i];
            }
        }
        else{
            for (int i=0; i<array.length;i++){
                array[i][num]=1-array[i][num];
            }
        }
    }

    boolean isTrue(int[][] array, int[][] target){
        for (int i=0;i<array.length;i++){
            for (int j=0;j<array[0].length;j++){
                if (array[i][j]!=target[i][j]) return false;
            }
        }
        return true;
    }
}