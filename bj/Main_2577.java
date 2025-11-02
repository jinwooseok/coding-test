import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_2577 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int A = Integer.parseInt(br.readLine());
		int B = Integer.parseInt(br.readLine());
		int C = Integer.parseInt(br.readLine());

		Map<Integer, Integer> map = new HashMap<>();
		for (int i=0;i<10;i++) {
			map.put(i,0);
		}

		int num = A*B*C;
		String numString = Integer.toString(num);
		for (int i=0;i<numString.length();i++) {
			int convertedNum = numString.charAt(i)-'0';
			map.put(convertedNum, map.get(convertedNum)+1);
		}
		for (int i=0;i<10;i++) {
			System.out.println(map.get(i));
		}
	}
}
