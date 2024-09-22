'''
A[r][c]
나머지를 통해 계속 순환하게 만들기
8개의 정수 순서

순서
1. 모든 구름이 d방향으로 s칸 이동
2. 그 위치에서 바구니의 양을 1증가
3. 구름 사라짐
4. 2의 칸에서 물복사버그 마법 시전(대각선 방향으로 거리가 1인칸에 물의 양증가)
    (이동과 다르게 N으로 순환하지 않는 듯)
5. 물의 양을 추가한 후 2이상이면 구름이 생기고 2 줄어듬.
'''
from collections import deque
#1번부터 8번까지
MOVE = [(0,-1),(-1,-1),(-1,0),(-1,1),(0,1),(1,1),(1,0),(1,-1)]

def move(cloud_loc:deque, direction, size):
    N = len(arr)
    dr,dc=MOVE[direction-1]
    for i in range(len(cloud_loc)):
        r,c=cloud_loc.popleft()
        cloud_loc.append(((r+dr*size)%N, (c+dc*size)%N))

#양 증가시킨 후 물 복사하기
def raining(arr, cloud_loc:deque, visited):
    for r,c in cloud_loc:
        visited[r][c]= True
        arr[r][c]+=1

#버그 작동
def bug(arr, cloud_loc:deque):
    N = len(arr)
    for r,c in cloud_loc:
        for i in range(1,8,2): #move의 대각선방향만 갖고옴
            dr,dc=MOVE[i]
            if r+dr<0 or c+dc<0 or r+dr>=N or c+dc>=N:
                continue
            if arr[r+dr][c+dc]>0:
                arr[r][c]+=1
#구름생성
def create_cloud(arr, cloud_loc:deque, visited):
    N = len(arr)
    forward_size = len(cloud_loc)
    for i in range(N):
        for j in range(N):
            if arr[i][j]>=2:
                if visited[i][j]: #이전에 사라진 구름이면 continue
                    visited[i][j]=False
                    continue
                cloud_loc.append((i,j))
                arr[i][j]-=2
    #이전번째 구름 제거
    for i in range(forward_size):
        cloud_loc.popleft()
    
    
N, M = map(int, input().split())

arr = [list(map(int, input().split())) for i in range(N)]
visited=[[False]*N for i in range(N)]
#명령을 수행하게 시킬 것임
cloud_loc = deque([(N-1,0),(N-1,1),(N-2,0),(N-2,1)])
for i in range(M):
    direction, size=map(int,input().split())
    #1. 구름이 이동
    move(cloud_loc, direction, size)
    #2. 그칸의 양 증가
    raining(arr, cloud_loc,visited)
    #4. 물복사버그
    bug(arr, cloud_loc)
    #5. 다음 구름 위치 확인
    create_cloud(arr, cloud_loc, visited)
total = 0
for i in range(N):
    total+=sum(arr[i])

print(total)