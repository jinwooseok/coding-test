import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15732 {
	/*
	A~B까지 C개 간격으로 도토리를 하나씩 더 넣음. 이러한 규칙이 K개 있음
	상자에 도토리 제한은 없음. 마지막 도토리가 들어간 상자의 번호
	규칙을 PQ에 넣음. 규칙을 반복함. 그러기엔 총 도토리의 개수가 너무 많음. -> 하지만 상자 개수가 최대 100만개로 ㄱㅊ을수도있음
	이분탐색이라면?
	한군데 콕 집음. 그리고 각 규칙 10000번 시행. (되는가 체크)
	안되면 오른쪽, 되면 왼쪽
	되는지 안되는지는 어떻게 체크? 규칙대로 했을 때 모든 도토리가 넣어지는가 그 순간에

	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long D = Long.parseLong(st.nextToken());
		int[][] rules = new int[K][3]; // A,B,C
 		for (int i=0;i<K;i++) {
			 st = new StringTokenizer(br.readLine());
			 rules[i][0] = Integer.parseInt(st.nextToken());
			 rules[i][1] = Integer.parseInt(st.nextToken());
			 rules[i][2] = Integer.parseInt(st.nextToken());
		}
		// binary search
		int left = 1;
		int right = N;
		int mid = (left+right)/2;
		int result;
		while (left<=right) {
			result = check(rules, mid, D);
			if (result<0) {
				right = mid-1;
			} else if (result==0) {
				left = mid+1;
			}
			mid = (left+right)/2;
		}
		//System.out.println(left+" "+right);
		System.out.println(left);
	}

	static int check(int[][] rules, int idx, long total) {
		long curCnt = 0;
		int tmp;
		for (int[] rule:rules) {
			// 현재 idx가 B보다 크거나 같은 경우 모든 규칙을 적용
			if (idx>=rule[1]) {
				tmp = (rule[1]-rule[0])/rule[2]+1;
				curCnt += tmp;
			} else if (idx>=rule[0]) {
				// 중간이라면 그만큼만 적용
				tmp = (idx-rule[0])/rule[2]+1;
				curCnt += tmp;
			}
		}
		// 상자가 더 필요함
		if (curCnt<total) {
			return 0;
		} else {
			return -1;
		}
	}
}
/*
1000000 2 100000
5 1000000 5
100 200 10
 */