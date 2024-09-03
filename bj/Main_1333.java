import java.io.*;
import java.util.*;
public class Main_1333 {
	/*
	 * 1.안내멘트가 L초 동안 재생됨
	 * 2.안내 멘트가 나오는 시간에는 상담원과 연결 x
	 * 3.끝난 후 5초동안 조용할 때 연결 요청 시 상담원 연결
	 * 
	 * 고객은 d초에 1번 1초간 연결 요청
	 * 동시에 x
	 */
	static int N,L,D;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // n번 후 반드시 재생
		L = Integer.parseInt(st.nextToken()); // 안내멘트 재생 시간
		D = Integer.parseInt(st.nextToken()); // d초에 한번씩 연결요청

		int i=1;
		while (true) {
			if (L*N+5*(N-1)<=D*i) {
				System.out.println(D*i); 
				break;
			}
//			System.out.println(D*i);
			if (D*i % (L+5) >= L) {
				System.out.println(D*i); 
				break;
			}
			i++;
		}
	}
}
