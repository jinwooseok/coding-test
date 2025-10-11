import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_1143 {
	/**
	 * scc 그래프 생성
	 * 각 scc마다 1개의 경찰서가 있어야 함.
	 * 마을 개수 N
	 * 각 마을에 경찰서를 설치하는데 드는 비용
	 * scc마다 1개의 경찰서를 설치해야함. YN으로 연결 구분
	 * double 형
	 *  0->1, 1->2, 2->3, 3->1
	 * 0, [123]
	 * 0->1
	 * 비용이 최소가 되는것이 아닌 평균값이 최소여야 하는구나.
	 * 평균값이 최소가 되기 위해선 개수는 많고 총합은 작아야함
	 *
	 * 위상정렬을 통해 순서 준비
	 * 최상위 scc에 경찰서를 설치한 경우 1개면 됨.
	 * 최상위 scc가 너무 비싸다면? 2번째 scc에 설치한다면? 어차피 최상위는 못감.
	 * minWeights 들을 작은거부터 뽑아서 조건을 만족할때까지 더하기(
	 *	0->1 0->4
	 *	1->0 1->3
	 *  2->3 2->4
	 *  [0,1], [2] ,[3], [4]
	 * 그러므로 위상정렬 후 indegree가 0인 scc들의 최소 경찰서끼리 합하고 평균
	 * @param args
	 * @throws IOException
	 */
	static int[] sccId;
	static int[] dfn;
	static int[] lows;
	static Stack<Integer> stack;
	static boolean[] onStack;
	static List<Integer>[] graph;
	static int[] weights;
	static int sccCounter;
	static int dfnCounter;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());

		sccId = new int[N];
		dfn = new int[N];
		lows = new int[N];
		stack = new Stack<>();
		onStack = new boolean[N];
		weights = new int[N];
		sccCounter = 0;
		dfnCounter = 1;
		graph = new ArrayList[N];

		for (int i=0;i<N;i++) {
			graph[i] = new ArrayList<>();
			weights[i] = Integer.parseInt(st.nextToken());
		}
		for (int i=0;i<N;i++) {
			String line = br.readLine();
			for (int j=0;j<N;j++) {
				if (line.charAt(j) == 'Y') graph[i].add(j);
			}
		}

		for (int i=0;i<N;i++) {
			if (dfn[i] == 0) tarjan(i);
		}
		// System.out.println(Arrays.toString(sccId));
		SccInfo[] sccInfos = new SccInfo[sccCounter];
		for (int i=0;i<sccCounter;i++) {
			sccInfos[i] = new SccInfo(i);
		}
		for (int i=0;i<N;i++) {
			sccInfos[sccId[i]].setValues(weights[i]);
		}

		for (int i=0;i<sccCounter;i++) {
			sccInfos[i].sort();
		}

		// 위상 정렬(indegree를 boolean으로 해서 있는지 없는지만 체크)
		boolean[] isIndegree = new boolean[sccCounter];

		// 루트 노드 찾기
		for (int start=0;start<N;start++) {
			for (int end:graph[start]) {
				if (sccId[start]!=sccId[end]) {
					isIndegree[sccId[end]] = true;
				}
			}
		}
		double sum = 0;
		int policeCnt = 0;
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (SccInfo sccInfo:sccInfos) {
			int start = 0;
			if (!isIndegree[sccInfo.sccId]) {
				sum+=sccInfo.bestCost;
				policeCnt+=1;
				start = 1;
			}
			for (int i=start;i<sccInfo.weights.size();i++) {
				pq.add(sccInfo.weights.get(i));
			}
		}
		while (!pq.isEmpty()) {
			int cost = pq.poll();
			double currentAverage = sum / policeCnt;
			double newSum = sum + cost;
			int newCnt = policeCnt + 1;
			if (currentAverage > (newSum/newCnt)) {
				sum = newSum;
				policeCnt = newCnt;
			} else {
				break;
			}
		}

		System.out.println(sum/policeCnt);
	}

	static void tarjan(int num) {
		dfn[num] = lows[num] = dfnCounter++;
		stack.push(num);
		onStack[num] = true;
		for (int next:graph[num]) {
			if (dfn[next] == 0) {
				tarjan(next);
				lows[num] = Math.min(lows[next], lows[num]);
			} else if (onStack[next]) {
				lows[num] = Math.min(dfn[next], lows[num]);
			}
		}

		if (dfn[num] == lows[num]) {
			while (true) {
				int popped = stack.pop();
				onStack[popped] = false;
				sccId[popped] = sccCounter;
				if (popped == num) break;
			}
			sccCounter++;
		}
	}

	static class SccInfo implements Comparable<SccInfo> {
		int sccId;
		List<Integer> weights;
		int bestCost;
		int bestCount;
		double bestAverage;
		SccInfo(int sccId) {
			this.sccId = sccId;
			this.weights = new ArrayList<>();
		}

		public void setValues(int weight) {
			weights.add(weight);
		}

		public void sort() {
			Collections.sort(weights);
			bestCost = weights.get(0);
			bestCount = 1;
			bestAverage = (double) bestCost / bestCount;
			// int currentCost = bestCost;
			// for (int i = 1; i < weights.size(); i++) {
			// 	currentCost += weights.get(i);
			// 	int currentCount = i + 1;
			// 	double currentAverage = (double) currentCost / currentCount;
			//
			// 	if (currentAverage < bestAverage) {
			// 		bestCost = currentCost;
			// 		bestCount = currentCount;
			// 		bestAverage = currentAverage;
			// 	}
			// }
		}

		@Override
		public int compareTo(SccInfo o) {
			return Double.compare(this.bestAverage, o.bestAverage);
		}
	}
}
/*
10
1 2 3 4 5 6 7 8 9 10
NYNNNNNNNN
NNYNNNNNNN
YNNNNNNNNN
NNNNYNNNNN
NNNNNYNNNN
NNNYNNNNNN
NNNNNNNNNN
NNNNNNNNNN
NNNNNNNNNN
NNNNNNNNNN
 */