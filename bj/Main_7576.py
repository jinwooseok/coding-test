'''
익지 않은 토마토는 익은 토마토의 영향을 받아 익게 됨
bfs, visited체크해서 이미 방문한거승ㄴ 패스
아직 안익은 토마토 개수 알아둬야할듯
'''
from collections import deque
M, N = map(int, input().split())

arr = [list(map(int,input().split())) for i in range(N)]
remain_cnt = 0
q = deque()
visited = [[False]*M for i in range(N)]
for i in range(N):
    for j in range(M):
        if arr[i][j] == 1:
            q.append((i,j))
            visited[i][j] = True

level = 0
MOVE = [(0,1),(1,0),(0,-1),(-1,0)]
while q:
    size = len(q)
    for i in range(size):
        r,c = q.popleft()
        for dr, dc in MOVE:
            nr = r+dr
            nc = c+dc
            if nr<0 or nc<0 or nr>=N or nc>=M:
                continue
            if not visited[nr][nc] and arr[nr][nc]!=-1:
                visited[nr][nc] = True
                arr[nr][nc] = 1
                q.append((nr,nc))
    if len(q) == 0:
        break
    else:
        level+=1
for i in range(N):
    for j in range(M):
        if arr[i][j] == 0:
            level = -1
print(level)
