import java.util.*;

public class Solution_5643 {

    // BFS를 통해 도달할 수 있는 학생 수를 계산하는 함수
    public static int bfsCount(List<List<Integer>> graph, int start, int N, int[] memo) {
        // 메모이제이션: 이미 계산된 값이 있다면 반환
        if (memo[start] != -1) {
            return memo[start];
        }

        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            count++; // 도달한 학생 수 증가

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        memo[start] = count; // 계산된 결과 메모이제이션
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // 테스트 케이스 개수

        for (int tc = 1; tc <= T; tc++) {
            int N = sc.nextInt(); // 학생 수
            int M = sc.nextInt(); // 비교 결과의 수

            // 정방향 그래프와 역방향 그래프 초기화
            List<List<Integer>> forwardGraph = new ArrayList<>();
            List<List<Integer>> reverseGraph = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                forwardGraph.add(new ArrayList<>());
                reverseGraph.add(new ArrayList<>());
            }

            // 비교 결과 입력
            for (int i = 0; i < M; i++) {
                int a = sc.nextInt() - 1; // 1-based index를 0-based로 변경
                int b = sc.nextInt() - 1;
                forwardGraph.get(a).add(b);  // a -> b (a번 학생이 b번 학생보다 작음)
                reverseGraph.get(b).add(a);  // b -> a (b번 학생이 a번 학생보다 큼)
            }

            // 메모이제이션 배열 초기화
            int[] forwardMemo = new int[N];
            int[] reverseMemo = new int[N];
            Arrays.fill(forwardMemo, -1);
            Arrays.fill(reverseMemo, -1);

            int count = 0; // 자신이 몇 번째인지 알 수 있는 학생 수

            for (int student = 0; student < N; student++) {
                // 정방향으로 자신보다 큰 학생 수와 역방향으로 자신보다 작은 학생 수 계산
                int biggerCount = bfsCount(forwardGraph, student, N, forwardMemo) - 1; // 본인 제외
                int smallerCount = bfsCount(reverseGraph, student, N, reverseMemo) - 1; // 본인 제외

                // 자신보다 큰 학생 수 + 자신보다 작은 학생 수 == N-1 이면 자신이 몇 번째인지 알 수 있음
                if (biggerCount + smallerCount == N - 1) {
                    count++;
                }
            }

            // 결과 출력
            System.out.println("#" + tc + " " + count);
        }

        sc.close();
    }
}
