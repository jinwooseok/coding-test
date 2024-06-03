import java.util.*;

class Solution {
    int[][] MOVE = {{0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};

    public int solution(int[] arrows) {
        int answer = 0;
        Map<String, Set<String>> visited = new HashMap<>();
        int[] current = {0, 0};
        String currentKey = Arrays.toString(current);
        visited.put(currentKey, new HashSet<>());

        for (int arrow : arrows) {
            for (int i = 0; i < 2; i++) {
                int[] nextNode = {current[0] + MOVE[arrow][0], current[1] + MOVE[arrow][1]};
                String nextKey = Arrays.toString(nextNode);

                if (!visited.containsKey(nextKey)) {
                    visited.put(nextKey, new HashSet<>());
                    visited.get(nextKey).add(currentKey);
                    visited.get(currentKey).add(nextKey);
                } else if (!visited.get(nextKey).contains(currentKey)) {
                    answer += 1;
                    visited.get(currentKey).add(nextKey);
                    visited.get(nextKey).add(currentKey);
                }

                current = nextNode;
                currentKey = nextKey;
            }
        }
        return answer;
    }
}
