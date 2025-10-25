import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main_10800 {
	/**
	 * 1. 각 컬러별로 크기 순 정렬
	 * 2. 준비물 : LIST + MAP + SORTED, 각 유저의 공 배열 (1~N), 각 컬러별 부분합 배열
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 입력 준비
		int N = Integer.parseInt(br.readLine());
		int[] results = new int[N];
		int[][] balls = new int[N][2];
		int[] ballSizes = new int[N];
		Map<Integer, List<Integer>> map = new HashMap<>();

		for (int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int color = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			balls[i][0] = color;
			balls[i][1] = size;
			ballSizes[i] = size;
			map.putIfAbsent(color, new ArrayList<>());
			map.get(color).add(size);
		}

		// 부분합 배열 준비
		List<Integer>[] subSums = new ArrayList[N+1];
		int[] totalSums = new int[N+1];
		for (int i=0;i<N+1;i++) {
			subSums[i] = new ArrayList<>();
		}

		Arrays.sort(ballSizes);
		for (int i=0;i<N;i++) {
			totalSums[i+1]=ballSizes[i]+totalSums[i];
		}

		for (Map.Entry<Integer, List<Integer>> entry:map.entrySet()) {
			int color = entry.getKey();
			Collections.sort(entry.getValue());
			subSums[color].add(0);
			for (int i=0;i<entry.getValue().size();i++) {
				subSums[color].add(subSums[color].get(i)+entry.getValue().get(i));
			}
			// System.out.println(subSums[color]);
			// System.out.println(map.get(color));
		}

		// 유저별 이분탐색 진행 (컬러별)
		for (int i=0;i<N;i++) {
			int totalResult = 0;
			int personalResult = 0;
			//전체 배열에서 이분탐색 진행
			int left = 0;
			int right = N-1;
			int mid = (left+right)/2;
			while (left<=right) {
				if (ballSizes[mid]<balls[i][1]) {
					left = mid+1;
				} else {
					right = mid-1;
				}
				mid = (left+right)/2;
			}
			totalResult = totalSums[left];

			left = 0;
			right = map.get(balls[i][0]).size()-1;
			mid = (left+right)/2;
			while (left<=right) {
				if (map.get(balls[i][0]).get(mid)<balls[i][1]) {
					left = mid+1;
				} else {
					right = mid-1;
				}
				mid = (left+right)/2;
			}
			personalResult = subSums[balls[i][0]].get(left);
			// System.out.println(subSums[entry.getKey()]);
			// System.out.println(entry.getValue());
			// System.out.println("현재 숫자 : "+balls[i][1]+", 들어갈 인덱스? : "+left);
			results[i]=totalResult-personalResult;
		}
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<N;i++) {
			sb.append(results[i]).append("\n");
		}
		System.out.println(sb);
	}
}
/*
5
1 3
1 4
2 3
3 3
4 3
 */