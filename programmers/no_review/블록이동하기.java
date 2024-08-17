import java.util.*;
public class 블록이동하기 {
    int[][] field;
    int[][] CATEGORY={{1,1},{1,2},{2,1},{2,2}};
    int[][] MOVE={{0,1},{1,0},{-1,0},{0,-1}};
    public int solution(int[][] board) {
        int answer = 0;
        field = board;
        return bfs(); //1차원 배열인 경우 가로 2차원 배열인 경우 세로로 판단할 수 있을듯
    }

    int[][] rotate(Case cur, int ex, int direction){
        //90도좌측회전
        //상태가 가로 : 1 세로 : 2
        //(왼쪽을 회전. 오른쪽을 회전). (90도회전. -90도 회전). (가로일때. 세로일때)
        int new_r1; int new_r2;
        int new_c1; int new_c2;
        int status;
        //가로인지 세로인지 확인 후
        if (cur.r2==cur.r1) status = 1;
        else status = 2;

        if (ex==1){ //왼쪽을 축으로
            new_r1 = cur.r1;
            new_c1 = cur.c1;
            if (direction == 1){ // 90도 방향
                if (status == 1){// 가로라면
                    if (cur.r1+1>=field.length || cur.c1+1>=field.length || field[cur.r1+1][cur.c1+1]==1)
                        return new int[][]{{-1,-1},{-1,-1}};//검사
                    new_r2 = cur.r1+1;
                    new_c2 = cur.c1;
                }
                else{
                    if (cur.r1+1>=field.length || cur.c1-1<0 || field[cur.r1+1][cur.c1-1]==1)
                        return new int[][]{{-1,-1},{-1,-1}};//검사
                    new_r2 = cur.r1;
                    new_c2 = cur.c1-1;
                }//세로라면
            }
            else {
                if (status == 1){// 가로라면
                    if (cur.r1-1<0 || cur.c1+1>=field.length ||field[cur.r1-1][cur.c1+1]==1)
                        return new int[][]{{-1,-1},{-1,-1}};
                    new_r2 = cur.r1-1;
                    new_c2 = cur.c1;
                }
                else{
                    if (cur.r1+1>=field.length || cur.c1+1>=field.length || field[cur.r1+1][cur.c1+1]==1)
                        return new int[][]{{-1,-1},{-1,-1}};//검사
                    new_r2 = cur.r1;
                    new_c2 = cur.c1+1;
                }//세로라면
            }
        }
        else {//오른쪽을 축으로
            new_r2 = cur.r2;
            new_c2 = cur.c2;
            if (direction == 1){ // 90도 방향
                if (status == 1){// 가로라면
                    if (cur.r2-1<0 || cur.c2-1<0 || field[cur.r2-1][cur.c2-1]==1)
                        return new int[][]{{-1,-1},{-1,-1}};//검사
                    new_r1 = cur.r2-1;
                    new_c1 = cur.c2;
                }
                else{
                    if (cur.r2-1<0 || cur.c2+1>=field.length || field[cur.r2-1][cur.c2+1]==1)
                        return new int[][]{{-1,-1},{-1,-1}};//검사
                    new_r1 = cur.r2;
                    new_c1 = cur.c2+1;
                }//세로라면
            }
            else {
                if (status == 1){// 가로라면
                    if (cur.r2+1>=field.length || cur.c2-1<0 || field[cur.r2+1][cur.c2-1]==1)
                        return new int[][]{{-1,-1},{-1,-1}};//검사
                    new_r1 = cur.r2+1;
                    new_c1 = cur.c2;
                }
                else{
                    if (cur.r2-1<0 || cur.c2-1<0 || field[cur.r2-1][cur.c2-1]==1)
                        return new int[][]{{-1,-1},{-1,-1}};//검사
                    new_r1 = cur.r2;
                    new_c1 = cur.c2-1;
                }//세로라면
            }
        }
        return new int[][]{{new_r1,new_c1},{new_r2,new_c2}};
    }


    int bfs(){
        //큐 (현재위치, 시간)
        Deque<Case> queue = new LinkedList<>();
        //삽입
        boolean[][][][] visited = new boolean[field.length][field.length][field.length][field.length];
        queue.addLast(new Case(0,0,0,1,0));
        //반복. visited도 활용
        while (!queue.isEmpty()){
            int status;
            Case cur=queue.pollFirst();
            //범위 벗어남
            if (cur.r1>=field.length || cur.c1>=field.length || cur.r2>=field.length || cur.c2>=field.length) continue;
            if (cur.r1<0 || cur.c1<0 || cur.r2<0 || cur.c2<0) continue;
            //도착한 경우


            //visited에 있는경우
            if (visited[cur.r1][cur.c1][cur.r2][cur.c2]) continue;
            else visited[cur.r1][cur.c1][cur.r2][cur.c2]=true;

            //1인경우
            if (field[cur.r1][cur.c1]==1 || field[cur.r2][cur.c2]==1) continue;

            if (cur.r2==field.length-1 && cur.c2==field.length-1) return cur.count;

            //System.out.println(cur.r1+" "+cur.c1+" "+cur.r2+" "+cur.c2+" "+cur.count);
            //변경된 후에 1과 겹치는 경우 패스
            for(int[] move : MOVE){
                queue.addLast(new Case(cur.r1+move[0],cur.c1+move[1],cur.r2+move[0],cur.c2+move[1],cur.count+1));
            }
            //회전하는 경우
            for(int[] category : CATEGORY){
                //회전도중 1과 겹치는 경우 패스
                int[][] new_case = rotate(cur, category[0], category[1]);
                if (new_case[0][0]!=-1)
                    queue.addLast(new Case(new_case[0][0],new_case[0][1],new_case[1][0],new_case[1][1],cur.count+1));
            }

        }
        //도착시 반환
        return 0;
    }
}

class Case{
    int r1; int c1;
    int r2; int c2;
    int count;
    Case(int r1, int c1, int r2, int c2, int count){
        if (r1>r2 || c1>c2){
            this.r1=r2;
            this.c1=c2;
            this.r2=r1;
            this.c2=c1;
        }
        else{
            this.r1=r1;
            this.c1=c1;
            this.r2=r2;
            this.c2=c2;
        }
        this.count=count;
    }
}