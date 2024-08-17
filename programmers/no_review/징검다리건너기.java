import java.util.*;
public class 징검다리건너기 {
    public int solution(int[] stones, int k) {
        //이분탐색
        int left = 0;
        int right = Arrays.stream(stones).max().getAsInt();
        while (left<=right){
            int mid = (left+right)/2;
            Boolean result = execute(stones, mid, k);
            if (result==true){
                left=mid+1;
            }
            else{
                right=mid-1;
            }
        }


        return left;
    }
    Boolean execute(int[] stones, int mid, int k){
        int loc = 0;
        int i = 1;
        while (true){
            if (stones.length+1-loc<=k) return true;
            if (i-loc>k) return false;
            if (stones[i-1]-mid>0) loc=i;
            i+=1;
        }
    }
}