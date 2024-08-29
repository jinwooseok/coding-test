
'''
주사위 굴리기
1 6
3 4
2 5
초기상태 : ㅜ잇면이 1, 동쪽이 3인 상태로 놓임
굴리고 난 후 아랫면의 수가 복사됟거나 캄의 수가 복사됨
동서북남
1 2 3 4

  2
4 1 3
  5
  6

동쪽 이동
  2
동서방향으로 이동 시
왼 위 오 아
4 1 3 6
남북방향으로 이동 시

5 1 2 6
'''
#명령에 따른 주사위 이동
#주사위가 이동하면서 면이 바뀌는 기능
#이동 후 복사하는 방향 선택
#바깥 이동 시 명령 무시
#위치 이동.

from collections import deque
def move(x,y, arr, command, dice_we:deque, dice_ns:deque):
    MOVE = [(0,1),(0,-1),(-1,0),(1,0)]
    #주사위 윗면 찾기
    #위치 이동
    if x+MOVE[command-1][0]>=len(arr) or y+MOVE[command-1][1]>=len(arr[0]) or x+MOVE[command-1][0]<0 or y+MOVE[command-1][1]<0:
        return x, y
    
    x, y = x+MOVE[command-1][0], y+MOVE[command-1][1] 
    
    #동:we 오른쪽 방향
    if command == 1:
        dice_we.appendleft(dice_we.pop())
        dice_ns[0] = dice_we[0]
        dice_ns[2] = dice_we[2]
    #서
    elif command == 2:
        dice_we.append(dice_we.popleft())
        dice_ns[0] = dice_we[0]
        dice_ns[2] = dice_we[2]
    #북
    elif command == 3:
        dice_ns.appendleft(dice_ns.pop())
        dice_we[0] = dice_ns[0]
        dice_we[2] = dice_ns[2]

    #남
    elif command == 4:
        dice_ns.append(dice_ns.popleft())
        dice_we[0] = dice_ns[0]
        dice_we[2] = dice_ns[2]
    #칸 복사
    if arr[x][y] == 0: #0이면 주사위 밑면 값 복사
        arr[x][y]=dice_we[2]
    else: #0이 아니면 주사위의 밑면에 복사되면서 칸은 0이 됨
        dice_we[2] = arr[x][y]
        dice_ns[2] = arr[x][y]
        arr[x][y] = 0
    #print(dice_we, dice_ns)
    #출력
    print(dice_we[0])
    return x, y
#입력
N, M, x, y, K = map(int,input().split())
arr = [0]*N
for i in range(N):
    arr[i]=list(map(int, input().split()))

commands = list(map(int, input().split()))

#동서 남북 이동 시 바뀌는 부분 설정
dice_we = deque([0,0,0,0])
dice_ns = deque([0,0,0,0])
for command in commands:
    x, y=move(x, y, arr, command, dice_we, dice_ns)
