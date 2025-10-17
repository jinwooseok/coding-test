import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_11438 {
	/**
	 * 정점이 100000개인 트리
	 * 두개의 노드쌍이 주어졌을 때 가장 가까운 공통 조상이 몇번인지?
	 * 부모를 타고 올라가는 형식은 맞으나 레벨을 기록함.
	 * 그래서 레벨이 작은것을 동일한 레벨까지 올리기 + 그 이후로는 동시에 부모 노드로 올라가며 만나는 지점 찾기
	 * 100000개 정점에서 타고 올라가는거 최대 logn
	 * 100000번 실행 -> nlogn
	 * O(nlogn)
	 * 레벨을 바꾸면 안된다. 루트가 틀려지면서 달라짐
	 * 부모 배열에 기록. 레벨은?
	 * 그래프로 만들기 -> 타고 내려가기 -> 내려가면서 부모배열에 기록 + depth
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] parents = new int[N+1][17];
		int[] depth = new int[N+1];
		List<Integer>[] nodes = new ArrayList[N+1];
		int curDepth = 0;

		for (int i=1;i<N+1;i++) {
			nodes[i] = new ArrayList<>();
			depth[i] = -1;
		}
		// 그래프 초기화
		for (int i=0;i<N-1;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes[a].add(b);
			nodes[b].add(a);
		}

		// 루트부터 시작해 parents, depth 배열 채우기
		Queue<Integer> q = new LinkedList<>();
		int root=1;
		q.add(root);
		parents[root][0]=1;
		depth[1]=curDepth++;
		while (!q.isEmpty()) {
			// 레벨을 카운트 하기 위해 뽑기 전 몇개의 노드가 들어있는지 확인
			int seq = q.size();
			for (int i=0;i<seq;i++) {
				int cur = q.poll();
				// System.out.println(cur);
				// System.out.println(Arrays.toString(parents));
				for (int next:nodes[cur]) {
					// 초기에 다 -1에서 시작했으므로 정해졌다면 pass
					if (depth[next]!=-1) continue;
					q.add(next);
					parents[next][0]=cur;
					depth[next]=curDepth;
				}
			}
			// 레벨 높이기
			curDepth++;
		}

		// 전처리 과정 필요
		for (int i=1;i<parents[0].length;i++) {
			for (int j=0;j<parents.length;j++) {
				if (parents[j][i - 1] != 0) {
					parents[j][i] = parents[parents[j][i - 1]][i - 1];
				}
			}
		}

		int M = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// 거슬러 올라가는 함수
			// a depth와 b depth 중 깊은것을 그것의 차만큼 단독으로 진행
			int aDepth = depth[a];
			int bDepth = depth[b];

			// System.out.println("num: " + a + " " + b);
			// System.out.println("depth: "+aDepth+" "+bDepth);
			int lca = -1;
			if (aDepth<bDepth) {
				for (int j=0;j<17;j++) {
					if (((bDepth - aDepth) & (1 << j)) > 0) {
						b = parents[b][j];
					}
				}
			} else if (bDepth<aDepth) {
				for (int j=0;j<17;j++) {
					if (((aDepth - bDepth) & (1 << j)) > 0) {
						a = parents[a][j];
					}
				}
			}

			if (a == b) {
				lca = a;
			} else {
				for (int j=16;j>=0;j--) {
					if (parents[a][j]>0 && parents[a][j]!=parents[b][j]) {
						a = parents[a][j];
						b = parents[b][j];
					}
				}
				lca = parents[a][0];
			}

			sb.append(lca).append('\n');
		}
		System.out.println(sb);
	}
}
