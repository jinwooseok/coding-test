import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_5525 {
	/*
	O의 개수로 N 확정
	타고타고가다가..끊기면 어떻게 할지
	- I가 한번더 나와서 끊기면 그 I부터 다시 시작.
	- 다른 문자라면 그냥 초기화. 처음부터 다시 시작.
	만족하는가? 와 O의 개수
	o의 개수로 몇군데인지 파악.
	더 많이 반복되는 경우, 최대 N까지만 하고, N이라면 1개 추가.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int length = Integer.parseInt(br.readLine());
		String S = br.readLine();

		int oCount = 0;
		char last;
		char secondLast;

		// 첫번째 두번째만 진행
		secondLast = S.charAt(0);
		last = S.charAt(1);
		int result = 0;
		for (int i=2;i<length;i++) {
			char word = S.charAt(i);
			if (secondLast == 'I' && last == 'O' && word == 'I') {
				if (oCount<N) oCount++;
				if (oCount == N) result++;
			} else if (!(last == 'I' && word == 'O') && !(last == 'O' && word == 'I')) {
				oCount = 0;
			}
			//System.out.println(secondLast + " " + last + " " + word +" " + oCount);
			secondLast = last;
			last = word;

		}
		System.out.println(result);
	}
}
