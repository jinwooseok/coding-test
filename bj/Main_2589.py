'''
L로만 이동 가능
한칸 이동에 한시간
최단거리.
보물의 위치는 안알려줌
근데 아무튼 최단거리중에서 최장거리를 구해야함.
백트래킹
2500 * 2500
'''
from collections import deque
MOVE = [(0,1),(1,0),(0,-1),(-1,0)]
def getStart(arr, r, c, N, M):
    visited = [[False]* M for i in range(N)]
    q = deque([(r,c,0)])
    r, c, dist = 0, 0, 0
    while q:
        r, c, dist = q.popleft()
        for dr, dc in MOVE:
            nr = r+dr
            nc = c+dc
            if 0<=nr<N and 0<=nc<M and visited[nr][nc] is False and arr[nr][nc] == 'L':
                visited[nr][nc] = True
                q.append((nr,nc,dist+1))   
    return r, c, dist

def bfs(arr, r, c, N, M):
    q = deque([(r,c,0)])
    r, c, dist = 0, 0, 0
    while q:
        r, c, dist = q.popleft()
        for dr, dc in MOVE:
            nr = r+dr
            nc = c+dc
            if 0<=nr<N and 0<=nc<M and arr[nr][nc] == 'L':
                arr[nr][nc] = 'W'
                q.append((nr,nc,dist+1))   
    return r, c, dist


N, M = map(int, input().split())
arr = [list(input()) for i in range(N)]
max_dist = 0
for i in range(N):
    for j in range(M):
        if arr[i][j] == 'L':
            startR, startC, dist = getStart(arr, i, j, N, M)
            print(startR, startC)
            arr[startR][startC] = 'W'
            endR, endC, result = bfs(arr, startR, startC, N, M)
            print(endR, endC)
            max_dist = max(max_dist,result)
print(max_dist)
'''
7 7
WLLLLLW
LWLWLWW
LLLWLWW
LWWWLWW
LLLLLWW
LWWWWWW
WWWWWWW
'''