'''
1. 현재 칸 청소
주변 4칸 중 청소되지 않은 빈칸이 없을 경우
    1. 바라보는 방향 유지하고 한칸 후진하고 청소
    2. 뒤쪽이 벽이면 작동 멈춤

청소되지 않은 빈칸이 있는 경우
    1. 반시계 방향 90도 회전
    2. 앞쪽칸이 청소되지 않은 빈 칸인 경우 한 칸 전진 후 청소
'''
#입력
N, M = map(int, input().split())

robot_r, robot_c, dir = map(int, input().split())

arr = [0]*N
for i in range(N):
    arr[i] = list(map(int, input().split()))
    
#방향 이동 (0 북, 1 동, 2 남, 3 서)
delta = [(-1,0),(0,1),(1,0),(0,-1)]

#현재 칸 청소
def clean(r,c):
    arr[r][c] = -1

#주변 확인 로직. 반시계 방향 우선 회전. 방향 리턴. 먼지없으면 -1
def check_around_and_go(r, c, dir):
    next_dir=dir
    for i in range(4):
        next_dir=(next_dir+3)%4
        #4방향 돌았을 때 청소할거 있으면 그 방향으로 ㄱㄱ. 반시계 방향으로 확인했으니 ㅇㅋ
        if 0<=r+delta[next_dir][0]<N and 0<=c+delta[next_dir][1]<M and arr[r+delta[next_dir][0]][c+delta[next_dir][1]]==0:
            return go(r,c,next_dir)
    #먼지 없으면 고백로직
    return go_back(r,c,dir)

def go(r,c,dir):
    return r+delta[dir][0], c+delta[dir][1], dir
#깨끗한 경우 후직 로직 작동
def go_back(r,c,dir):
    return r-delta[dir][0], c-delta[dir][1], dir


room_cnt = 0

while 0<=robot_r<N and 0<=robot_c<M and arr[robot_r][robot_c]!=1:
    #print(robot_r, robot_c)
    #본인 칸 확인
    if arr[robot_r][robot_c]==0:
        clean(robot_r, robot_c)
        room_cnt+=1
    #주변 칸 확인
    robot_r, robot_c, dir=check_around_and_go(robot_r,robot_c,dir)

print(room_cnt)