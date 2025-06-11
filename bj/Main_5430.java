import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_5430 {
	/**
	 * AC? 정수배열에 연산을 하기위해 만든 언어. R : 배열 내 수의 순서 뒤집기, D : 첫번째 수를 버리기
	 * 1. 배열이 비어있을 때 D를 사용하면 에러 발생
	 * T(테스트 개수), p(수행할 함수목록), n(배열의 숫자 개수), arr(문자열)
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		char[] commands;
		String[] stringArr;
		Deque<Integer> nums;
		int n;
		StringBuilder sb = new StringBuilder();
		for (int t=0;t<T;t++) {
			// 명령 배열
			commands = br.readLine().toCharArray();
			// 배열의 수
			n = Integer.parseInt(br.readLine());

			String inputArr = br.readLine();
			inputArr = inputArr.substring(1, inputArr.length()-1);
			stringArr = inputArr.split(",");
			nums = new LinkedList<>();
			for (int i=0;i<n;i++) {
				nums.addLast(Integer.parseInt(stringArr[i]));
			}
			sb.append(execute(nums, commands)).append("\n");
		}
		System.out.println(sb);
	}
	private static String execute(Deque<Integer> nums, char[] commands) {
		boolean isReversed = false;
		for (char command : commands) {
			switch (command) {
				case 'R':
					isReversed = !isReversed;
					break;
				case 'D':
					if (nums.isEmpty()) return "error";
					delete(nums, isReversed);
					break;
			}
		}
		StringBuilder sb = new StringBuilder();
		if (nums.isEmpty()) {
			return "[]";
		}
		sb.append("[");
		if (!isReversed) {
			while (!nums.isEmpty()) {
				sb.append(nums.removeFirst()).append(",");
			}
		} else {
			while (!nums.isEmpty()) {
				sb.append(nums.removeLast()).append(",");
			}
		}
		sb.replace(sb.length()-1,sb.length(), "]");
		return sb.toString();
	}

	private static void delete(Deque<Integer> nums, boolean isReversed) {
		if (isReversed) nums.removeLast();
		else nums.removeFirst();
	}
}
