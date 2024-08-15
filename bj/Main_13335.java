import java.io.*;
import java.util.*;
public class Main_13335 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		
		//queue사용
		Queue<Integer> wait = new ArrayDeque<>();
		Queue<Integer[]> bridge = new ArrayDeque<>();
		
		//wait에 트럭 insert
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<N;i++) {
			wait.add(Integer.parseInt(st.nextToken()));
		}
		
 		int bridgeW = 0; // 현재 다리의 상태
 		int curTime = 0; // 현재 시간
 		int cnt = 0; // 나간 차량
 		
 		//bridge => 가중치, 들어온 시간 저장
 		while (cnt<N) {
 			//다리가 비어있지 않다면 꼭대기의 트럭이 들어온 시간을 점검하고 다리를 통과할 시간이면 지나간다.
 			if (!bridge.isEmpty()) {
 				if (curTime-bridge.peek()[1]==W) {
 					bridgeW-=bridge.poll()[0];
 					cnt++;
 				}
 			}
 			//지나가는 작용 후에 삽입 작용 시작
 			if (!wait.isEmpty() && bridgeW+wait.peek()<=L) {
 				int w = wait.poll();
 				bridge.add(new Integer[] {w,curTime});
 				bridgeW+=w;
 			}
 			curTime++;
 		}
 		
 		System.out.println(curTime);
	}
}