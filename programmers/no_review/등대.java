import java.util.*;
class Solution {
    int result=0;
    public int solution(int n, int[][] lighthouse) {
        //힙에 연결된 노드 개수를 저장해두는 것도 필요할 듯
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] light:lighthouse){
            map.computeIfAbsent(light[1],k->new ArrayList<Integer>()).add(light[0]);
            map.computeIfAbsent(light[0],k->new ArrayList<Integer>()).add(light[1]);

        }
        //그리디. set.add를 하고 그 개수가 n이 될 경우 멈춰
        dfs(map, lighthouse[0][0], 0);
        // dfs(map, lighthouse[0][1], 0);
        return result;
    }

    int dfs(Map<Integer, List<Integer>> map, int cur, int before){
        if (map.get(cur).size() == 1 && map.get(cur).get(0)==before) return 1;
        int count=0;
        for (int next:map.get(cur)){
            if (next == before) continue;
            count+=dfs(map, next, cur);
        }
        if (count==0) return 1;

        result++;
        return 0;
    }
}