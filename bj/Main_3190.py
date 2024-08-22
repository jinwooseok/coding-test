'''
1. 몸길이를 늘려 다음칸에 위치
2. 사과가있으면 길이 유지. 사과 없앰
3. 사과가 없다면 꼬리부분 축소.

시작점 : 0, 0 길이 : 1. 오른쪽
방향 전환 횟수, 방향 변환 정보 - L : 왼쪽으로 90도 - R : 오른쪽으로 90도 

설계
1. 입력값 받기
2. 0,0 에서 시작
3. 1초가 지났을 때 시뮬레이션 돌리기
4. 다음 커맨드 작동 타임 전까지 앞으로 이동
'''
from collections import deque
#보드 생성
boardSize = int(input())
appleSize = int(input())
board = [[0]*boardSize for i in range(boardSize)]
#사과 생성
for i in range(appleSize):
    r, c = map(int, input().split())    
    board[r-1][c-1] = 2
#방향변환정보
rotateCnt = int(input())

commands = [[0,0] for i in range(rotateCnt)]
for i in range(rotateCnt):
    timing, direction = map(str, input().split())
    commands[i][0] = int(timing)
    commands[i][1] = direction

#배열 업데이트 로직
def update_loc(board:list, queue:deque, head_r:int, head_c:int):
    if board[head_r][head_c] == 2: #사과라면
        board[head_r][head_c] = 1 #머리생성
    else: #사과가 아니라면 그냥 패스
        board[head_r][head_c] = 1 #머리생성
        tail=queue.pop() #꼬리를 자르기
        board[tail[0]][tail[1]] = 0
    queue.appendleft((head_r,head_c))

#방향수정 오-아-왼-위(시계)
MOVE = [(0,1),(1,0),(0,-1),(-1,0)]
commands = deque(commands)
snake = deque()
snake.append((0,0))
board[0][0] = 1
direction = 0
time = 0
while True:
    #print(board)
    #명령에 따른 방향 변경
    if len(commands)!=0 and commands[0][0] == time:
        #print(time, commands[0][1])
        if commands[0][1] == 'D': #시계방향
            direction=(direction+1)%4
        else:                     #반시계방향
            direction=(3+direction)%4
        commands.popleft()
    move = MOVE[direction]
    #머리를 현재 진행방향으로 이동(시계 or 반시계)
    time+=1 #시간 업데이트
    if snake[0][0]+move[0]<0 or snake[0][1]+move[1]<0 or snake[0][0]+move[0]>=boardSize or snake[0][1]+move[1]>=boardSize:
        print(time) #벽에 부딪히는 경우 현재 시간 출력 후 탈출
        break
    if board[snake[0][0]+move[0]][snake[0][1]+move[1]]==1: #진행지점에 몸통이 있던 경우엔 출력 후 탈출
        print(time)
        break
    update_loc(board, snake, snake[0][0]+move[0],snake[0][1]+move[1])