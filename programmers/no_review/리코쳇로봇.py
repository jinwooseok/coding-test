from collections import deque

def solution(board):
    answer = 0
    #시작지점과 목표지점, 장애물 위치 찾기
    r, g = None, None

    for i in range(len(board)):
        for j in range(len(board[0])):
            if board[i][j] =='R':
                r = (i,j)
            elif board[i][j] == 'G':
                g = (i,j)

    #bfs를 통해 최소 횟수를 구하며 탐색
    MOVE = [(0,1),(1,0),(-1,0),(0,-1)]
    queue = deque()
    visited = set()
    queue.append([r,0])
    while queue:
        #방문했다면 넘어감
        loc, cnt = queue.popleft()
        if loc == g:
            return cnt
        if loc in visited:
            continue
        else:
            visited.add(loc)

        for r_move, c_move in MOVE:
            if r_move > 0:
                for i in range(loc[0], len(board)+1,1):
                    if i==len(board) or board[i][loc[1]] == 'D':
                        queue.append([(i-1,loc[1]), cnt+1])
                        break
            elif r_move < 0:
                for i in range(loc[0],-2,-1):
                    if i==-1 or board[i][loc[1]] == 'D':
                        queue.append([(i+1,loc[1]), cnt+1])
                        break
            elif c_move > 0:
                for i in range(loc[1], len(board[0])+1,1):
                    if i==len(board[0]) or board[loc[0]][i] == 'D':
                        queue.append([(loc[0], i-1), cnt+1])
                        break
            elif c_move < 0:
                for i in range(loc[1],-2,-1):
                    if i==-1 or board[loc[0]][i] == 'D':
                        queue.append([(loc[0], i+1), cnt+1])
                        break
    return -1
