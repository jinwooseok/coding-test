import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1956 {
	/*
	사이클을 이루는 도로의 길이의 합이 최소
	가장 작은 사이클을 찾아라. (가장 짧아야 함)
	마을 개수 최대: 400개
	도로 개수: 400*399 = 160000
	플로이드?
	플로이드면 64000000. 갔다가 오는걸로 계산

	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		long[][] adjArr = new long[V][V];
		for (int i=0;i<V;i++) {
			Arrays.fill(adjArr[i], Long.MAX_VALUE);
			adjArr[i][i] = 0;
		}
		for (int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			long c = Long.parseLong(st.nextToken());
			adjArr[a][b] = c;
		}

		for (int i=0;i<V;i++) {
			for (int j=0;j<V;j++) {
				for (int k=0;k<V;k++) {
					if (i==j || j==k || k==i) continue;
					if (adjArr[j][i]==Long.MAX_VALUE || adjArr[i][k]==Long.MAX_VALUE) continue;
					adjArr[j][k] = Math.min(adjArr[j][k], adjArr[j][i]+adjArr[i][k]);
				}
			}
		}
		long result = Long.MAX_VALUE;
		for (int i=0;i<V;i++) {
			for (int j=0;j<V;j++) {
				if (i==j) continue;
				if (adjArr[j][i]==Long.MAX_VALUE || adjArr[i][j]==Long.MAX_VALUE) continue;
				result = Math.min(result, adjArr[i][j]+adjArr[j][i]);
			}
		}
		if (result == Long.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(result);
	}
}
