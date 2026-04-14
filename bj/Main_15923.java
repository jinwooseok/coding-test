import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15923 {
	/*
	건물 설계
	꼭지점의 좌표로 관리
	설계도는 바닥부분만 나타낸 밑면도
	직각으로 볼록한 닫힌 곡선
	타원의 디지털화
	밑면도의 모든 꼭지점 좌표가 주어짐.
	설계도는 좌표 순서대로 i - i+1 순서로 이어짐. 그 선분을 그려서 복원 완료함
	건물의 길이를 궇하자

	*.....*..
	.........
	......*.*
	.........
	......*.*
	.........
	*.....*..


	격자와 딱 맞다고 하니 이전 것과 다음것을 잇도록 하자.
	arr에 r, c 쿼리들을 저장한 후, 이전 지점을 저장하여 하도록. 마지막 지점은 나머지를 활용하면 더 깔끔하게 할 수 있을 것 같다.
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] arr = new int[N][2];
		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			arr[i][0] = row;
			arr[i][1] = col;
		}
		int sum = 0;
		for (int i=0;i<N;i++) {
			sum += Math.abs((arr[(i+1)%N][0]-arr[i][0]) + (arr[(i+1)%N][1]-arr[i][1]));
		}
		System.out.println(sum);
	}
}
