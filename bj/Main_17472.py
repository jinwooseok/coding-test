from collections import deque
import heapq
MOVE = [(0,1),(1,0),(0,-1),(-1,0)]
N, M = map(int,input().split())

arr = [list(map(int, input().split())) for i in range(N)]
markedArr = [[0]*M for i in range(N)]
# 섬 번호, 개수 마킹 bfs

def bfs(arr, markedArr, r, c, num, N, M):
    q = deque([(r,c)])
    while q:
        r, c = q.popleft()
        for dr, dc in MOVE:
            nr = r+dr
            nc = c+dc
            if 0<=nr<N and 0<=nc<M and arr[nr][nc]==1 and markedArr[nr][nc]==0:
                markedArr[nr][nc] = num
                q.append((nr,nc))

def getDist(markedArr, adjArr, r, c, direction):
    start = markedArr[i][j]
    dist = 0
    while True:
        dist += 1
        nr, nc = r+MOVE[direction][0]*dist, c+MOVE[direction][1]*dist
        if nr<0 or nr>=N or nc<0 or nc>=M:
            return
        if markedArr[nr][nc] == start:
            return
        if markedArr[nr][nc] != 0:
            if dist-1 == 1:
                return
            else:
                adjArr[start-1][markedArr[nr][nc]-1] = min(adjArr[start-1][markedArr[nr][nc]-1],dist-1)
                return

num = 0
for i in range(N):
    for j in range(M):
        if arr[i][j] == 1 and markedArr[i][j] == 0:
            num+=1
            markedArr[i][j] = num
            bfs(arr, markedArr, i, j, num, N, M)

adjArr = [[100]*num for i in range(num)]
pq = []
for i in range(N):
    for j in range(M):
        if markedArr[i][j] != 0:
            for k in range(4):
                getDist(markedArr, adjArr, i, j, k)

# 프림 사용

visited = [False]*num
cnt = 0
minDist = 0
heapq.heappush(pq, (0,0))
visited[0] = True
while cnt<num-1 and pq:
    dist, v = heapq.heappop(pq)
    if not visited[v]:
        visited[v] = True
        cnt += 1
        minDist += dist
    for i in range(num):
        if adjArr[v][i] != 100 and not visited[i]:
            heapq.heappush(pq,(adjArr[v][i], i))
# print(visited)
# for i in markedArr:
#     print(i)
# for i in adjArr:
#     print(i)

if cnt<num-1:
    print(-1)
else:
    print(minDist)