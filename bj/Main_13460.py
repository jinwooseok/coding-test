'''
빨강, 파랑 구슬을 하나씩 넣고 빨간 구슬을 구멍을 통해 빼냄
구멍은 하나. 바깥쪽 행과열은 막혀있음.
중력으로 이리 저리 굴림.
동시에 구멍에 빠져도 실패
최대 10번 이후 -1
하나씩 따로 하는건 문제 발생 가능.
둘이 겹치는 것도 안됨.

'''
from collections import deque

N, M = map(int, input().split())
arr = [list(input()) for i in range(N)]
MOVE = [(-1, 0), (0, 1), (1, 0), (0, -1)]

# red, blue 좌표 뽑기
red, blue, hole = (0, 0), (0, 0), (0, 0)
for i in range(N):
    for j in range(M):
        if arr[i][j] == 'R':
            red = (i, j)
            arr[i][j] = '.'
        elif arr[i][j] == 'B':
            blue = (i, j)
            arr[i][j] = '.'
        elif arr[i][j] == 'O':
            hole = (i, j)

# BFS 탐색
def bfs():
    q = deque()
    visited = [[[[0 for _ in range(M)] for _ in range(N)] for _ in range(M)] for _ in range(N)]
    q.append(State(red[0], red[1], blue[0], blue[1], 0))

    while q:
        curState = q.popleft()

        if visited[curState.rr][curState.rc][curState.br][curState.bc]:
            continue
        visited[curState.rr][curState.rc][curState.br][curState.bc] = 1

        if curState.step >= 10:
            continue

        for i in range(4):
            nrr, nrc, nbr, nbc = to(curState, i)

            # 파란 구슬이 구멍에 빠졌으면 무시
            if nbr == hole[0] and nbc == hole[1]:
                continue

            # 빨간 구슬이 구멍에 빠졌으면 성공
            if nrr == hole[0] and nrc == hole[1]:
                return curState.step + 1

            # 큐에 추가
            if not visited[nrr][nrc][nbr][nbc]:
                q.append(State(nrr, nrc, nbr, nbc, curState.step + 1))

    return -1

# to 함수 구현
def to(state, idx):
    seq = [(0, state.rr, state.rc), (1, state.br, state.bc)]
    
    if idx == 0 and state.rr > state.br:
        seq.reverse()
    if idx == 1 and state.rc < state.bc:
        seq.reverse()
    if idx == 2 and state.rr < state.br:
        seq.reverse()
    if idx == 3 and state.rc > state.bc:
        seq.reverse()

    for i in range(2):
        color, r, c = seq[i]
        while True:
            nr, nc = r + MOVE[idx][0], c + MOVE[idx][1]

            if arr[nr][nc] == 'O':
                seq[i]=(color, nr, nc)
                break
            if arr[nr][nc] == '#' or (seq[1-i][1] == nr and seq[1-i][2] == nc):
                seq[i]=(color, r, c)
                break


            r, c = nr, nc
    red, blue = None, None
    for i in range(2):
        if seq[i][0]==0:
            red = (seq[i][1], seq[i][2])
            blue = (seq[1-i][1], seq[1-i][2])
            break
    #print(red, blue)
    return red[0], red[1], blue[0], blue[1]

class State:
    def __init__(self, rr, rc, br, bc, step):
        self.rr = rr
        self.rc = rc
        self.br = br
        self.bc = bc
        self.step = step

print(bfs())
