import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_10986 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Map<Integer, Integer> prefixMap = new HashMap<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int prefix = 0;
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			int k = Integer.parseInt(st.nextToken());
			prefix=(prefix+k)%M;
			prefixMap.putIfAbsent(prefix, 0);
			prefixMap.put(prefix, prefixMap.get(prefix)+1);
		}
		long result = 0;
		if (prefixMap.containsKey(0)) result+=prefixMap.get(0);
		for (int i=0;i<M;i++) {
			if (!prefixMap.containsKey(i)) continue;
			long cnt = prefixMap.get(i);
			result+=cnt*(cnt-1)/2;
		}
		System.out.println(result);
	}
}
