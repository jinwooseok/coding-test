import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main_10546 {
	/*
	매년 모두 완주했지만 한명만 완주 못함

	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<String, Integer> list = new HashMap<>();
		int N = Integer.parseInt(br.readLine());
		for (int i=0;i<2*N-1;i++) {
			String name = br.readLine();
			if (!list.containsKey(name)) {
				list.put(name, 1);
			} else {
				list.put(name, list.get(name)+1);
			}
		}
		for (Map.Entry<String, Integer> result:list.entrySet()) {
			if (result.getValue() % 2 != 0) {
				System.out.println(result.getKey());
				return;
			}
		}
	}
}
