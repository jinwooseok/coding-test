import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_22866 {
	/*
	스택
	건물의 높이가 높아지면 스택을 쌓는다.
	건물의 높이가 낮아지면 스택에서 제거
	과정
	STACK: (8,7)->X
	STACK: (8,7),(7,1)->스택에 있던 1개 등록
	STACK: (8,7), (6,5) -> 스택에서 더 큰것이 남을 때까지 뺌. 6에 1개 등록
	STACK: (8,5), (6,7), (5,3) -> 스택에 있던 2개 등록
	STACK: (8,7), (4,6) -> 4에 1개 등록

	뒤에 더 작은게 있으면 안보여야 하는데 스택의 개수로는 처리 불가능
	아니다. 뒤에 더 작은게 있으면 이미 스택에 없는 상태.
	우선 arr에 결과를 저장하는데, 우선 개수 저장 및 가장 가까운 건물 번호를 저장
	건물 번호는 좌우로 하면서 스택에서 가장 처음 뽑힌 것 중 절대값 작은거.



	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<int[]> stack = new Stack<>();
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		int[][] results = new int[N+1][2];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=1;i<N+1;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i=N;i>0;i--) {
			if (stack.isEmpty()) {
				stack.push(new int[]{i, arr[i]});
				continue;
			}
			while (!stack.isEmpty()) {
				int[] next = stack.peek();
				// System.out.println((next[0])+" "+next[1]);
				// 스택 꼭대기에 있는게 더 큰지 확인
				// 더 크다면 그대로 두고, 업데이트.
				if (next[1]>arr[i]) {
					results[i][1]+=stack.size();
					// 거리가 가장 가까운지? 더 작은 번호인지?
					if (results[i][0] == 0) {
						results[i][0] = next[0];
					} else if (Math.abs(results[i][0]-i)>Math.abs(next[0]-i)) {
						results[i][0] = next[0];
					} else if (Math.abs(results[i][0]-i)==Math.abs(next[0]-i) && results[i][0]>next[0]) {
						results[i][0] = next[0];
					}
					break;
				} else {
					stack.pop();
				}
			}
			stack.push(new int[]{i, arr[i]});
			// System.out.println((i)+" "+(results[i][0])+" "+results[i][1]);
		}
		stack = new Stack<>();
		for (int i=1;i<=N;i++) {
			if (stack.isEmpty()) {
				stack.push(new int[]{i, arr[i]});
				continue;
			}
			while (!stack.isEmpty()) {
				int[] next = stack.peek();
				// System.out.println((next[0])+" "+next[1]);
				// 스택 꼭대기에 있는게 더 큰지 확인
				// 더 크다면 그대로 두고, 업데이트.
				if (next[1]>arr[i]) {
					results[i][1]+=stack.size();
					// 거리가 가장 가까운지? 더 작은 번호인지?
					if (results[i][0] == 0) {
						results[i][0] = next[0];
					} else if (Math.abs(results[i][0]-i)>Math.abs(next[0]-i)) {
						results[i][0] = next[0];
					} else if (Math.abs(results[i][0]-i)==Math.abs(next[0]-i) && results[i][0]>next[0]) {
						results[i][0] = next[0];
					}
					break;
				} else {
					stack.pop();
				}
			}
			stack.push(new int[]{i, arr[i]});
			// System.out.println((i)+" "+(results[i][0])+" "+results[i][1]);
		}

		StringBuilder sb = new StringBuilder();
		for (int i=1;i<N+1;i++) {
			if (results[i][1]>0) {
				sb.append(results[i][1]).append(" ").append(results[i][0]).append("\n");
			} else {
				sb.append(0).append("\n");
			}
		}
		System.out.println(sb);
	}
}
