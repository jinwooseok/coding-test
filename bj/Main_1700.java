import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.SortedMap;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_1700 {
	static Map<Integer, Integer> tap = new HashMap<>();
	static Map<Integer, Queue<Integer>> qMap = new HashMap<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		// K:N의 배열
		int[] seq = new int[K];
		st = new StringTokenizer(br.readLine());
		for (int i=0;i<K;i++) {
			seq[i] = Integer.parseInt(st.nextToken());
			qMap.putIfAbsent(seq[i], new LinkedList<>());
			qMap.get(seq[i]).add(i);
		}
		// 처음엔 순서대로 빈자리 넣기
		int index = 0;
		int switchCnt = 0;

		// 초기 세팅: 멀티탭이 다 찰 때까지
		while (index < K && tap.size() < N) {
			if (!tap.containsKey(seq[index])) {
				tap.put(seq[index], tap.size());
			}
			qMap.get(seq[index]).poll();
			index++;
		}

		if (index == K) {
			System.out.println(0);
		} else {
			for (int i=index;i<K;i++) {
				// 이미 자리에 있다면
				if (tap.containsKey(seq[i])) {
					qMap.get(seq[i]).poll();
					continue;
				}

				// 자리에 없다면 바꿀 자리 정하기
				int maxIndex = -1;
				int maxNum = -1;

				// 멀티탭에 꽂혀있는 기기들 중 선택
				for (Map.Entry<Integer, Integer> entry : tap.entrySet()) {
					int device = entry.getKey();

					// 이 기기가 앞으로 사용되지 않으면 바로 선택
					if (qMap.get(device).isEmpty()) {
						maxNum = device;
						break;
					}

					// 다음 사용 시점이 가장 늦은 것 선택
					int nextUse = qMap.get(device).peek();
					if (nextUse > maxIndex) {
						maxIndex = nextUse;
						maxNum = device;
					}
				}

				// 교체
				int slot = tap.get(maxNum);
				tap.remove(maxNum);
				tap.put(seq[i], slot);
				qMap.get(seq[i]).poll();
				switchCnt++;
			}

			System.out.println(switchCnt);
		}
	}
	// public static class Device implements Comparable<Device> {
	// 	int location;
	// 	Device(int cnt, int loc) {
	// 		this.remainCnt = cnt;
	// 		this.location = loc;
	// 	}
	//
	// 	@Override
	// 	public int compareTo(Device o) {
	// 		return Integer.compare(o.remainCnt, this.remainCnt);
	// 	}
	// }

	// public static void main(String[] args) throws IOException {
	// 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// 	StringTokenizer st = new StringTokenizer(br.readLine());
	// 	int N = Integer.parseInt(st.nextToken());
	// 	int K = Integer.parseInt(st.nextToken());
	// 	// K:N의 배열
	// 	int[] arr = new int[N];
	// 	int[] seq = new int[K];
	// 	st = new StringTokenizer(br.readLine());
	// 	Arrays.fill(arr, -1);
	// 	for (int i=0;i<K;i++) {
	// 		seq[i] = Integer.parseInt(st.nextToken());
	// 	}
	// 	// 처음엔 순서대로 빈자리 넣기
	// 	int index = 0;
	// 	boolean flag = false;
	// 	int switchCnt = 0;
	// 	for (int i=0;i<N;i++) {
	// 		if (index>=K) {
	// 			flag = true;
	// 			break;
	// 		}
	// 		if (!tap.containsKey(seq[index]) || tap.get(seq[index])==-1) {
	// 			tap.put(seq[index],i);
	// 			arr[i] = seq[index++];
	// 		}
	// 	}
	// 	//System.out.println(index);
	// 	if (flag) {
	// 		System.out.println(0);
	// 	} else {
	// 		dfs(arr, seq, index, switchCnt);
	// 		System.out.println(minSwitchCnt);
	// 	}
	// 	// 꽉차있는 상태에서 어떤 자리에 넣을 건지를 dfs
	// 	// if 같다면 안바꿔도 됨. 바꾼
	//
	// }

	// public static void dfs(int[] arr, int[] seq, int seqIdx, int curCnt) {
	// 	if (seqIdx==seq.length) {
	// 		minSwitchCnt = Math.min(minSwitchCnt, curCnt);
	// 		return;
	// 	}
	// 	if (tap.containsKey(seq[seqIdx]) && tap.get(seq[seqIdx])!=-1) {
	// 		dfs(arr, seq, seqIdx+1, curCnt);
	// 	} else {
	// 		for (int i=0;i<arr.length;i++) {
	// 			// 변경
	// 			int tmp = arr[i];
	// 			tap.put(seq[seqIdx], i);
	// 			tap.put(arr[i], -1);
	// 			arr[i] = seq[seqIdx];
	// 			dfs(arr, seq, seqIdx+1, curCnt+1);
	// 			tap.put(seq[seqIdx], -1);
	// 			tap.put(tmp, i);
	// 			arr[i] = tmp;
	// 		}
	// 	}
	// }
}