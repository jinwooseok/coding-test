import java.io.*;
import java.util.*;
public class Main_6987 {
    //맞추는 경우 4번 * 각 나라 6개 * 승무패 3개
    /*
    결과가 가능하려면 필요한 판단 요소
    1. 각 나라의 경기 수의 합이 5
    2. 모든 나라의 승패의 합이 동일해야함
    3. 무승부가 났을 때 다른 나라의 무승부를 제거하면 0이 나와야 함.
     */
    public static void main(String[] args) throws IOException {
        //입력 파트
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Team[][] resultTable = new Team[4][6];
        for (int i=0;i<4;i++){
            st = new StringTokenizer(br.readLine().trim()," ");
            for (int j=0;j<6;j++) {
                Team team = new Team(j,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
                resultTable[i][j] = team;
            }
        }
        //가능판단 로직 + 출력
        StringBuilder sb = new StringBuilder();
        for (int i=0;i<4;i++) {
            if (isPossible(resultTable[i])) sb.append(1).append(' ');
            else sb.append(0).append(' ');
        }
        System.out.println(sb);
    }

    static boolean isPossible(Team[] resultTable) {
        // 팀의 승, 무, 패의 합이 각각 맞아야 함
        int totalMatches = 0;
        for (int i = 0; i < 6; i++) {
            totalMatches += resultTable[i].win + resultTable[i].draw + resultTable[i].lose;
            if (resultTable[i].win + resultTable[i].lose + resultTable[i].draw != 5) {
                return false;  // 각 팀의 경기 수 합이 5가 아니면 불가능
            }
        }
        if (totalMatches != 30) return false;  // 전체 경기 수가 15경기여야 함
        //체크
        return resultCheck(resultTable);
    }
    //543010 100000
    private static boolean resultCheck(Team[] teams) {
        int[] wins = new int[6];
        int[] loses = new int[6];
        int[] draws = new int[6];

        Team[] copiedTeams = Arrays.copyOf(teams, teams.length);
        Arrays.sort(copiedTeams, Comparator.comparingInt((Team t) -> t.win).reversed());
        for (int i = 0; i < teams.length; i++) {
            wins[i] = copiedTeams[i].num;
        }

        // lose 기준 오름차순 정렬
        Arrays.sort(copiedTeams, Comparator.comparingInt((Team t) -> t.lose).reversed());
        for (int i = 0; i < teams.length; i++) {
            loses[i] = copiedTeams[i].num;
        }

        // draw 기준 오름차순 정렬
        Arrays.sort(copiedTeams, Comparator.comparingInt((Team t) -> t.draw).reversed());
        for (int i = 0; i < teams.length; i++) {
            draws[i] = copiedTeams[i].num;
        }

//        for (int j=0;j<6;j++) {
//            System.out.println(Arrays.toString(wins));
//            System.out.println(teams[wins[j]]);
//        } 5 4 3 1 1 1  0 0 0 0 0 1
        for (int i=0;i<6;i++){
            for (int j=0;j<6;j++) {
                if (teams[wins[i]].win==0) break;
                if (teams[wins[i]] == teams[loses[j]]) continue;
                if (teams[loses[j]].lose==0) continue;
                teams[wins[i]].win--; teams[loses[j]].lose--;
            }
            if (teams[wins[i]].win!=0) return false;
        }
        for (int i=0;i<6;i++){
            if (teams[wins[i]].lose != 0) return false;
        }
        // 011345 443210 5 0 0 4 0 1 3 0 2 1 1 3 0 1 4 1 0 4
        System.out.println("여기는 통과?");
        for (int i=0;i<6;i++) {
            for (int j=0;j<6;j++) {
                if (teams[draws[i]].draw == 0) break;
                if (teams[draws[j]].draw == 0) continue;
                if (teams[draws[i]] == teams[draws[j]]) continue;
                teams[draws[i]].draw--;
                teams[draws[j]].draw--;
            }
            if (teams[draws[i]].draw!=0) return false;
        }
        return true;
    }

    static class Team {
         int num;
        int win;
        int lose;
        int draw;
        public Team(int num, int win, int draw, int lose) {
            this.num = num;
            this.win = win;
            this.lose = lose;
            this.draw = draw;
        }

        @Override
        public String toString() {
            return this.win + " " + this.draw +" "+this.lose;
        }
    }
}
