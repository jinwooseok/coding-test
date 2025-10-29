import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_13141 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		float[][][] graph = new float[2*N+1][2*N+1][2];
		for (int i=1;i<2*N+1;i++) {
			for (int j=1;j<2*N+1;j++) {
				graph[i][j][0] = Integer.MAX_VALUE;
				graph[i][j][1] = Integer.MIN_VALUE;
			}
		}

		float[][] minDist = new float[2*N+1][2*N+1];
		for (int i=0;i<2*N+1;i++) {
			Arrays.fill(minDist[i], Integer.MAX_VALUE);
		}
		for (int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			if (s == e) {
				e = s+N;
				graph[s][e][0] = Math.min(graph[s][e][0], (float) l/2);
				graph[s][e][1] = Math.max(graph[s][e][1], (float) l/2);
				graph[e][s][0] = Math.min(graph[e][s][0], (float) l/2);
				graph[e][s][1] = Math.max(graph[e][s][1], (float) l/2);
				minDist[s][e] = Math.min(minDist[s][e], (float) l/2);
				minDist[e][s] = Math.min(minDist[e][s], (float) l/2);
			} else {
				graph[s][e][0] = Math.min(graph[s][e][0], l);
				graph[s][e][1] = Math.max(graph[s][e][1], l);
				graph[e][s][0] = Math.min(graph[e][s][0], l);
				graph[e][s][1] = Math.max(graph[e][s][1], l);
				minDist[s][e] = Math.min(minDist[s][e], l);
				minDist[e][s] = Math.min(minDist[e][s], l);
			}
		}
		// 플로이드
		// 경유지
		// for (int i=1;i<N+1;i++) {
		// 	System.out.println(Arrays.toString(minDist[i]));
		// }
		for (int i=1;i<N+1;i++) {
			// 시작점
			for (int j=1;j<2*N+1;j++) {
				// 끝점
				for (int k=1;k<2*N+1;k++) {
					if (j==k) {
						minDist[j][k] = 0;
						continue;
					}
					if (minDist[j][i]!=Integer.MAX_VALUE&&minDist[i][k]!=Integer.MAX_VALUE)
						minDist[j][k] = Math.min(minDist[j][k], minDist[j][i]+minDist[i][k]);
				}
			}
		}
		// for (int i=1;i<N+1;i++) {
		// 	System.out.println(Arrays.toString(minDist[i]));
		// }
		// 가장 최대거리가 작은것은?
		// int bestStartPoint = 0;
		// float bestMaxDistance = Integer.MAX_VALUE;
		// for (int i=1;i<N+1;i++) {
		// 	float maxDistance = Integer.MIN_VALUE;
		// 	for (int j=1;j<2*N+1;j++) {
		// 		//System.out.println(minDist[i][j]);
		// 		if (minDist[i][j] != Integer.MAX_VALUE) {
		// 			//System.out.println(minDist[i][j]);
		// 			maxDistance = Math.max(minDist[i][j], maxDistance);
		// 		}
		// 	}
		// 	//System.out.println(maxDistance);
		// 	if (bestMaxDistance > maxDistance) {
		// 		bestStartPoint = i;
		// 		bestMaxDistance = maxDistance;
		// 	}
		// }
		// float result = 0;
		// System.out.println(bestStartPoint);
		// System.out.println(bestMaxDistance);
		float result = Integer.MAX_VALUE;

		// 모든 시작점에 대해 시도
		for (int start=1; start<=N; start++) {
			float maxTime = 0;

			for (int i=1;i<N+1;i++) {
				for (int j=i+1;j<2*N+1;j++) {
					if (graph[i][j][1] != Integer.MIN_VALUE) {
						float iArriveTime = minDist[start][i];
						float jArriveTime = minDist[start][j];
						if (iArriveTime == Integer.MAX_VALUE || jArriveTime == Integer.MAX_VALUE) {
							continue;
						}
						// j에 도착한 시간이 더 멀다는 것을 무조건 가정
						if (iArriveTime>jArriveTime) {
							float temp = iArriveTime;
							iArriveTime = jArriveTime;
							jArriveTime = temp;
						}
						float edgeLen = graph[i][j][1];
						float timeDiff = jArriveTime - iArriveTime;
						float edgeTime;
						// 한쪽에서 이미 다 태운 경우
						if (j > N) {
							// self-loop: 도착 시간 + 전체 길이
							edgeTime = iArriveTime + edgeLen;
						} else {
							// 일반 간선
							if (timeDiff >= edgeLen) {
								edgeTime = iArriveTime + edgeLen;
							} else {
								edgeTime = (iArriveTime + jArriveTime + edgeLen) / 2;
							}
						}
						maxTime = Math.max(maxTime, edgeTime);
					}
				}
			}
			result = Math.min(result, maxTime);
		}

		System.out.printf("%.1f%n", result);
		// minDist[4][ed] 로 도착한 시간 확인
	}
}
