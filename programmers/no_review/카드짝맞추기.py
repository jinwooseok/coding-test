from itertools import permutations
from collections import defaultdict, deque
import sys
import copy
def solution(board, r, c):
    answer = 0
    #카드의 위치 저장
    loc=defaultdict(list)
    for i in range(4):
        for j in range(4):
            if board[i][j] != 0:
                loc[board[i][j]].append((i,j))

    #방문할 순서 경우의 수
    min_count= float('inf')
    for case in permutations(list(loc.keys())):
        tmp_board = copy.deepcopy(board)
        tmp_li = [[(r,c)],[0]] # 현재 위치를 담음 # 횟수를 담음

        for num in case: #최단경로를 찾을 번호
            li = [[],[]]
            for cur_loc, cur_cnt in zip(tmp_li[0], tmp_li[1]):
                v1, v2 = loc[num]
                a1, a2 = search(tmp_board, cur_loc, v1)
                b1, b2 = search(tmp_board, cur_loc, v2)
                c1, c2 = search(tmp_board, v1, v2)
                d1, d2 = search(tmp_board, v2, v1)
                
                li[0]+=[v2, v1]
                li[1]+=[cur_cnt+a2+c2+2, cur_cnt+b2+d2+2]
            tmp_li = li
            tmp_board[loc[num][0][0]][loc[num][0][1]] = 0
            tmp_board[loc[num][1][0]][loc[num][1][1]] = 0

        if min_count > min(tmp_li[1]):
            min_count = min(tmp_li[1])

    return min_count
            
                 
def ctrl_move(r, c, dr, dc, board):
    while True:
        r += dr
        c += dc
        if 0 <= r < 4 and 0 <= c < 4:
            if board[r][c] != 0:
                return r, c
        else:
            return r - dr, c - dc
        
# 한지점에서 한지점 최단거리 구하기
MOVE = {(0,1),(0,-1),(1,0),(-1,0)}
def search(board, start, arrive):
    visited = set()
    queue = deque()
    queue.append((start[0],start[1],0))
    while queue:
        r, c, cnt = queue.popleft()
        if (r, c) == arrive:
            return (r,c), cnt
        elif (r, c) in visited:
            continue
        else:
            visited.add((r,c))
        for next_r, next_c in MOVE:
            nr, nc = r+next_r, c+next_c
            if 0<=nr<4 and 0<=nc<4:
                #근처로 이동
                queue.append((nr, nc, cnt+1))
                cr, cc = ctrl_move(r,c,next_r,next_c,board)
                queue.append((cr,cc,cnt+1))
            