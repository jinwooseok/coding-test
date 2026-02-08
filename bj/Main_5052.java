import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main_5052 {
	/*

	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		StringBuilder resultSb = new StringBuilder();
		Set<String>[] arr;
		String[] lines;
		for (int t=0;t<T;t++) {
			arr = new HashSet[11];
			for (int i=1;i<11;i++) {
				arr[i] = new HashSet<>();
			}
			int N = Integer.parseInt(br.readLine());
			lines = new String[N];
			for (int i=0;i<N;i++) {
				String line = br.readLine();
				arr[line.length()].add(line);
				lines[i] = line;
			}
			boolean flag = false;
			// System.out.println(arr[3]);
			for (String line:lines) {
				for (int i=0;i<line.length();i++) {
					if (flag) break;
					sb.append(line.charAt(i));
					//System.out.println(sb);
					if (arr[sb.length()].contains(sb.toString()) && line.length()!=sb.length()) {
						flag = true;
						break;
					}
				}
				sb.setLength(0);
			}
			if (flag) resultSb.append("NO").append("\n");
			else resultSb.append("YES").append("\n");

		}
		System.out.println(resultSb);
	}
}

// 1 11 111 1111 11111 111111 저장할때
// 111111
// xxxxxx