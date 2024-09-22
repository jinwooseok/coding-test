# '''
# A[r][c]
# 나머지를 통해 계속 순환하게 만들기
# 8개의 정수 순서

# 순서
# 1. 모든 구름이 d방향으로 s칸 이동
# 2. 그 위치에서 바구니의 양을 1증가
# 3. 구름 사라짐
# 4. 2의 칸에서 물복사버그 마법 시전(대각선 방향으로 거리가 1인칸에 물의 양증가)
#     (이동과 다르게 N으로 순환하지 않는 듯)
# 5. 물의 양을 추가한 후 2이상이면 구름이 생기고 2 줄어듬.
# '''
# from collections import deque
# #1번부터 8번까지
# MOVE = [(0,-1),(-1,-1),(-1,0),(-1,1),(0,1),(1,1),(1,0),(1,-1)]

# def move(cloud_loc:deque, direction, size):
#     N = len(arr)
#     dr,dc=MOVE[direction-1]
#     for i in range(len(cloud_loc)):
#         r,c=cloud_loc.popleft()
#         cloud_loc.append(((r+dr*size)%N, (c+dc*size)%N))

# #양 증가시킨 후 물 복사하기
# def raining(arr, cloud_loc:deque, visited):
#     for r,c in cloud_loc:
#         visited[r][c]= True
#         arr[r][c]+=1

# #버그 작동
# def bug(arr, cloud_loc:deque):
#     N = len(arr)
#     for r,c in cloud_loc:
#         for i in range(1,8,2): #move의 대각선방향만 갖고옴
#             dr,dc=MOVE[i]
#             if r+dr<0 or c+dc<0 or r+dr>=N or c+dc>=N:
#                 continue
#             if arr[r+dr][c+dc]>0:
#                 arr[r][c]+=1
# #구름생성
# def create_cloud(arr, cloud_loc:deque, visited):
#     N = len(arr)
#     forward_size = len(cloud_loc)
#     for i in range(N):
#         for j in range(N):
#             if arr[i][j]>=2:
#                 if visited[i][j]: #이전에 사라진 구름이면 continue
#                     visited[i][j]=False
#                     continue
#                 cloud_loc.append((i,j))
#                 arr[i][j]-=2
#     #이전번째 구름 제거
#     for i in range(forward_size):
#         cloud_loc.popleft()
    
    
# N, M = map(int, input().split())

# arr = [list(map(int, input().split())) for i in range(N)]
# visited=[[False]*N for i in range(N)]
# #명령을 수행하게 시킬 것임
# cloud_loc = deque([(N-1,0),(N-1,1),(N-2,0),(N-2,1)])
# for i in range(M):
#     direction, size=map(int,input().split())
#     #1. 구름이 이동
#     move(cloud_loc, direction, size)
#     #2. 그칸의 양 증가
#     raining(arr, cloud_loc,visited)
#     #4. 물복사버그
#     bug(arr, cloud_loc)
#     #5. 다음 구름 위치 확인
#     create_cloud(arr, cloud_loc, visited)
# total = 0
# for i in range(N):
#     total+=sum(arr[i])

# print(total)


'''
비바라기 
-> (N,1), (N,2), (N-1,1), (N-1,2)
1. 방향 d 거리 s
2. 구름 : True False
    물: +1
3. 물복사버그(대각선만큼 증가)
4. 물의 양 >= 2이면 구름, 물-2, 
'''
def is_valid(r,c):
    return 1<=r<= N and 1<=c<= N

def be_valid(r,c):
    if r<1:
        r = N - (abs(r) % N)
    elif r > N:
        r = r%N
        if r == 0:
            r = N

    if c<1:
        c = N - (abs(c) % N)
        
    elif c>N:
        c = c%N
        if c == 0:
            c = N
    return r, c

dr = ['N', 0, -1, -1, -1, 0, 1, 1, 1]
dc = ['N', -1, -1, 0, 1, 1, 1, 0, -1]

def move_cloud(r,c,d,s):
    # 구름 이동
    nr = r + dr[d]*s
    nc = c + dc[d]*s
    nr, nc = be_valid(nr, nc)
    return nr, nc

def add_water(r,c):
    plus = 0
    for d in range(2, 9, 2):
        nr = r+dr[d]
        nc = c+dc[d]
        if is_valid(nr, nc) and arr[nr][nc]:
            plus += 1

    return plus


N, M = map(int, input().split())
# arr = [list(map(int, input().split())) for _ in range(N)]
arr = [[0]*(N+1) for _ in range(N+1)]
for i in range(1, N+1):
    arr[i][1:N+1] = map(int, input().split())

# 구름의 상태: 구름이 있는지, 이전에 사라졌는지
cloud = [[False]*(N+1) for _ in range(N+1)]
total_water = 0

initial_cloud = [(N,1), (N,2), (N-1,1), (N-1,2)]
for r, c in initial_cloud:
    cloud[r][c] = True

for _ in range(M):
    d, s = map(int, input().split())
    now_cloud = [[False]*(N+1) for _ in range(N+1)]
    

    # 1.구름 이동
    for r in range(1, N+1):
        for c in range(1, N+1):
            if cloud[r][c]:
                nr, nc = move_cloud(r,c,d,s)
                now_cloud[nr][nc] = True
                # 2.물 증가
                arr[nr][nc] += 1
    cloud = now_cloud

    
    for r in range(1, N+1):
        for c in range(1, N+1):
            if cloud[r][c]:
                arr[r][c] += add_water(r,c)

    for r in range(1, N+1):
        for c in range(1, N+1):
            if arr[r][c] >= 2 and not cloud[r][c]:
                # if cloud[r][c] != False:
                cloud[r][c] = True
                arr[r][c] -= 2
            #구름 사라지는걸 작업안함 그래서 구름이 쌓임.
            #이거 두줄만 추가했음. 구름이었던거 사라지게함
            elif cloud[r][c] == True:
                cloud[r][c] = False
    #print(cloud)
    print('###')

for r in range(1, N+1):
    for c in range(1, N+1):
        total_water += arr[r][c]

print(total_water)
# print(arr)
# print('************')
# print(cloud)