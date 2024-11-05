'''
현재 모양과 위치를 들고가기
'''
from collections import deque
#
#
# def move(q, r, c, N, MOVE, direction):
#     cnt = 0
#     #그게 아니라면 이동시키는데 이동 후 방향이 벽이거나 나가면 안됨
#     for i, (dr, dc) in enumerate(MOVE):
#         if abs(i - direction) > 1:
#             continue
#         nr = r + dr
#         nc = c + dc
#         if 0 <= nr < N and 0 <= nc < N and arr[nr][nc] == 0:
#             if i == 1 and (arr[nr-1][nc] == 1 or arr[nr][nc-1] == 1):
#                 continue
#             if nr == N - 1 and nc == N - 1:
#                 cnt += 1
#                 continue
#             q.append((nr, nc, i))
#     return cnt



N = int(input())
arr = [list(map(int, input().split())) for i in range(N)]
dp = [[[0]*3 for i in range(N)] for i in range(N)]
dp[0][1][0] = 1
MOVE = [(0, 1), (1, 1), (1, 0)]
for r in range(N):
    for c in range(N):
        for k in range(3): #현재 방향이 k인지?
            if dp[r][c][k] != 0: #k방향이 0보다 크다면
                for i, (dr, dc) in enumerate(MOVE): #새로운 방향과 델타
                    if abs(i - k) > 1:
                        continue
                    nr = r + dr
                    nc = c + dc
                    if 0 <= nr < N and 0 <= nc < N and arr[nr][nc] == 0:
                        if i == 1 and (arr[nr - 1][nc] == 1 or arr[nr][nc - 1] == 1):
                            continue
                        dp[nr][nc][i] += dp[r][c][k]
#print(dp)

print(sum(dp[N-1][N-1]))