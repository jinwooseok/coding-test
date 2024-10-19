import sys
from collections import deque
input = sys.stdin.readline
N,M = map(int, input().split())
arr = [[0]*M for i in range(N)]
visited = [[-1]*M for i in range(N)]
target = None
for i in range(N):
    line = list(map(int, input().split()))
    for j in range(M):
        if line[j] == 2:
            target = (i,j)
            visited[i][j] = 0
        if line[j] == 0:
            visited[i][j] = 0
        arr[i][j] = line[j]

q = deque([(target[0],target[1],0)])
MOVE = [(0,1),(1,0),(0,-1),(-1,0)]

while q:
    r, c, dist = q.popleft()
    for dr, dc in MOVE:
        nr = r+dr
        nc = c+dc
        if nr<0 or nc<0 or nr>=N or nc>=M or visited[nr][nc]!=-1:
            continue
        
        visited[nr][nc] = dist+1
        q.append((nr,nc,dist+1))
for row in visited:
    print(*row)