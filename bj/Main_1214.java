import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1214 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		long D = Long.parseLong(st.nextToken());
		long P = Long.parseLong(st.nextToken());
		long Q = Long.parseLong(st.nextToken());
		if (P < Q) {
			long temp = P;
			P = Q;
			Q = temp;
		}
		long answer = Long.MAX_VALUE;
		if (D%Q == 0) {
			answer = D;
		} else if (D%P == 0) {
			answer = D;
		} else {
			answer = Math.min(Q * (D/Q + 1), P*(D/P + 1));
		}
		for (long i=1;i<D/P+1;i++) {
			if (answer==D) break;
			if (i>Q) break;
			if (D%(P*i)==0 || (D-P*i)%Q==0) {
				answer=D;
				break;
			}
			if (D/(P*i)==0) answer = Math.min(answer, P*i);
			else answer = Math.min(answer,P*i+Q*((D-P*i)/Q+1));
		}
		System.out.println(answer);
	}
}
