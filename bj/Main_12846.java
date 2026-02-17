import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main_12846 {
	/*
	일마다 급여가 정해져 있음
	퇴직할 때 한번에 줌
	가장 일급이 작을 때를 기준으로 급여 지급
	한번이라도 퇴직한 자는 재취직 x

	준수 n+1일 이후 월세. 벌 수 있는 최대 이익
	퇴직자들 급여를 통해
	최소가 가장 큰 구간 구하기
	증가하는 부분 수열
	숫자는 우선 long
	최소가 k인 가장 긴 구간을 구한다.



	20: [1, 3]
	10: [0, 2, 4]
	pq에 다 넣기. (크기, 인덱스 순서)
	작은거부터 했을 때,
	10: 0,1,2,3,4
	20: 인덱스 순으로 세는데 안채워진 부분은 20으로 채우기
	매번 빈공간이 있는지 찾아야 하는가?

	2차원배열 - 일급 수 (최대 100000개) *
	가장 긴 증가하는 부분수열인데 매번 증가가 처음값 기준


	   1
	  2 3
	4 5 6 7
	10:
	20: 2~4
	30: 3

	 */
	static long max = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		Stack<Integer> stack = new Stack<>();
		for (int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		stack.add(0);
		for (int i=1;i<N;i++) {
			while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
				int height = arr[stack.pop()];
				int width = stack.isEmpty() ? i : i - stack.peek() - 1;
				max = Math.max(max, (long) height * width);
			}
			stack.push(i);
		}

		// 남은 스택 처리
		while (!stack.isEmpty()) {
			int height = arr[stack.pop()];
			int width = stack.isEmpty() ? N : N - stack.peek() - 1;
			max = Math.max(max, (long) height * width);
		}

		System.out.println(max);
		// int[] tree = new int[4*N];
		// Arrays.fill(tree, Integer.MAX_VALUE);
		// Set<Integer> set = new TreeSet<>();
		// for (int i=0;i<N;i++) {
		// 	arr[i] = Integer.parseInt(st.nextToken());
		// 	insert(tree, i, 1, 0, N-1, arr[i]);
		// 	set.add(arr[i]);
		// }
		// for (int num: set) {
		// 	max = Math.max(max, num* (long) search(tree, 0, N-1, 1, num));
		// }
		// // System.out.println(Arrays.toString(tree));
		// System.out.println(max);
	}

	public static void insert(int[] tree, int idx, int node, int left, int right, int num) {
		if (left>idx || right<idx) return;
		if (left == right) {
			tree[node] = num;
			return;
		}
		int mid = (left+right)/2;
		insert(tree, idx, node*2, left, mid,  num);
		insert(tree, idx, node*2+1, mid+1, right, num);
		tree[node] = Math.min(tree[node*2], tree[node*2+1]);
	}

	public static int search(int[] tree, int left, int right, int node, int num) {
		int count = 0;
		if (left == right) return 0;
		int mid = (left+right)/2;
		if (tree[node*2]==num) {
			count += mid-left+1;
			return count;
		} else {
			count+=search(tree, left, mid, node*2, num);
		}
		if (tree[node*2+1]==num) {
			count += right-mid+1;
			return count;
		} else {
			count+=search(tree, mid+1, right, node*2+1, num);
		}
		return count;
	}
}
