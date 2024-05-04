import sys
sys.setrecursionlimit(10**6)
def solution(board, aloc, bloc):
    #winner, loser의 행동이 다름 -> a,b일때 각각 비교하여 bfs 홀수일때 : a win
    return dfs(board, aloc, bloc)

MOVE = {(1,0),(-1,0),(0,1),(0,-1)}
def dfs(board, aloc, bloc):
    if board[aloc[0]][aloc[1]] == 0:
        return 0
    board[aloc[0]][aloc[1]] = 0
    ret = 0
    for move_r, move_c in MOVE:
        nr, nc = aloc[0]+move_r, aloc[1]+move_c
        if 0>nr or nr>=len(board) or 0>nc or nc>=len(board[0]) or board[nr][nc]==0:
            continue
        cnt=1+dfs(board, bloc, [nr, nc])
    
        if ret % 2 == 0:
            if cnt % 2 == 0:
                ret = max(ret, cnt)
            else:
                ret = cnt
        else:
            if cnt % 2 == 1:
                ret = min(ret, cnt)   
    board[aloc[0]][aloc[1]] = 1
    return ret