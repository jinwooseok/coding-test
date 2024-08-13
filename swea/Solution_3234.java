import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3234 {
    /*
    추를 양팔저울의 왼쪽에 올릴 것인지 오른쪽에 올릴 것인지 정해야 함.
    오른쪽 <= 왼쪽 이어야함. 단, 올리는 순서가 있어서 매 순서마다 항상 왼쪽이 커야함
    왼쪽에 올라갈 조합을 구함. 단 이미 했던 계산을 또 할 수 있어서 dp처럼 해야함.
    항상 total - left <= left
    결국 모든 경우를 확인해야 하므로 dfs가 적당하겠다.
    1. dfs를 통해 right에 올리는 경우, left에 올리는 경우로 나눔.
    2. 매번 검사를 통해 안될 경우 return. 될 경우 계속 진행함.
    3. 끝까지 진행한 경우 ++
    필요한 요소 : 추 arr (static), 성공한 경우의 수 (static)

    추가) 순서는 다르지만 진행상황이 똑같은 경우가 있음
    예)
    오 :   1
    왼 : 2   4
    ----
    오 :     1
    왼 : 2 4

    위의 두 경우는 그 이후에 같은 경우의 수를 얻을 수 있음. 그러므로 메모이제이션을 통해 시간을 아낄 수 있다.

     */
    static int[][] dp; //[모든상태][왼쪽의 무게] => 순서를 배제하고 어떤 수를 사용했는지 + 한쪽의 무게 를 이용해 끝까지 확인하지 않고 경우의 수를 구할 수 있다.
    static boolean[][] visited; //방문을 dp에 맞춰 [모든 상태][왼쪽의 무게] => 방문의 기준을 상태에 방문했는지로 변경하면 dp와 연계하기 쉬움
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine().trim());
        for (int t=0;t<T;t++) {
            int size = Integer.parseInt(br.readLine().trim());
            int[] arr = new int[size];
            st = new StringTokenizer(br.readLine().trim());
            int arrSum = 0;
            //배열 입력
            for (int i=0;i<size;i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                arrSum += arr[i];
            }
            dp = new int[1<<size][arrSum+1]; //dp배열 - 각 순서별, 상태를 저장
            visited = new boolean[1 << size][arrSum + 1];

            System.out.println("#"+(t+1)+" "+dfs(arr, 0, 0, 0));
        }
    }

    public static int dfs(int[] arr, int idx, int leftWeight, int rightWeight) {
        if (leftWeight < rightWeight) return 0; // 오른쪽이 더 무거워지면 실패
        if (idx == (1 << arr.length) - 1) return 1; // 모든 추를 배치했다면 성공

        if (visited[idx][leftWeight]) return dp[idx][leftWeight];

        visited[idx][leftWeight] = true;

        int cnt = 0;
        for (int i = 0; i < arr.length; i++) {
            if ((idx & (1 << i)) == 0) { // i번째 추가 아직 사용되지 않았다면
                // 왼쪽에 추를 올리는 경우
                cnt += dfs(arr, idx | (1 << i), leftWeight + arr[i], rightWeight);
                // 오른쪽에 추를 올리는 경우
                cnt += dfs(arr, idx | (1 << i), leftWeight, rightWeight + arr[i]);
            }
        }
        return dp[idx][leftWeight] = cnt;
    }
}
