import java.io.*;
import java.util.*;

public class Main_16638 {
	static ArrayList<Integer> nums;
	static ArrayList<Character> ops;
	static ArrayList<Integer> preCalcByOps;
	static int maxNum = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		String expr = br.readLine();

		nums = new ArrayList<>();
		ops = new ArrayList<>();
		preCalcByOps = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			if (i % 2 == 0) {
				nums.add(expr.charAt(i) - '0');
			} else {
				ops.add(expr.charAt(i));
			}
		}
		for (int i=0;i<ops.size();i++) {
			if (ops.get(i)=='+') {
				preCalcByOps.add(nums.get(i)+nums.get(i+1));
			} else if (ops.get(i)=='-') {
				preCalcByOps.add(nums.get(i)-nums.get(i+1));
			} else {
				preCalcByOps.add(nums.get(i)*nums.get(i+1));
			}
		}
		dfs(0, 0);
		System.out.println(maxNum);
	}

	public static void dfs(int opsIdx, int bit) {
		if (opsIdx == ops.size()) {
			ArrayList<Integer> values = new ArrayList<>();
			ArrayList<Character> operators = new ArrayList<>();

			values.add(nums.get(0));

			for (int i=0; i<ops.size(); i++) {
				if ((bit&(1<<i))!=0) {
					values.remove(values.size()-1);
					values.add(preCalcByOps.get(i));
				} else {
					// 괄호 없음: 연산자와 숫자 추가
					operators.add(ops.get(i));
					values.add(nums.get(i+1));
				}
			}

			for (int i = operators.size()-1; i >= 0; i--) {
				if (operators.get(i) == '*') {
					int result = values.get(i) * values.get(i+1);
					values.set(i, result);
					values.remove(i+1);
					operators.remove(i);
				}
			}

			int result = values.get(0);
			for (int i = 0; i < operators.size(); i++) {
				if (operators.get(i) == '+') {
					result += values.get(i+1);
				} else {
					result -= values.get(i+1);
				}
			}

			maxNum = Math.max(result, maxNum);
			return;
		}
		if (opsIdx>0 && (bit &(1<<(opsIdx-1)))==0) dfs(opsIdx+1, bit|(1<<opsIdx));
		else if (opsIdx==0) dfs(opsIdx+1, bit|(1<<opsIdx));
		dfs(opsIdx+1, bit);
	}
}