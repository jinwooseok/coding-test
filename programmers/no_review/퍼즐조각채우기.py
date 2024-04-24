import sys
sys.setrecursionlimit(10**6)
def solution(game_board, table):
    #visited를 놓고 일단 게임보드에서 채워야할 부분 추출 -> dfs가 적당할듯.
    array = {0:game_board, 1:table}
    result = {0:[], 1:[]}
    for num, board in array.items():
        visited = set()
        for i in range(len(board)):
            for j in range(len(board)):
                if board[i][j] == num and (i,j) not in visited:
                    v_set=dfs(board, num, i, j, set())
                    visited.update(v_set)
                    result[num].append(v_set)
    #다 추출한 후, 모양을 1과 o으로 표현. 
    result2={0:[],1:[]}
    for num, board in result.items():
        for block in board:
            r, c = zip(*block)
            clen, rlen = max(c)-min(c)+1, max(r)-min(r)+1
            array = [[0]*(clen) for i in range(rlen)]
            for x,y in block:
                array[x-min(r)][y-min(c)] = 1
            result2[num].append(array)

    #1과 0으로 된 배열을 배열 회전 알고리즘을 사용해 판단
    answer = 0
    flag=[False]*len(result2[1])
    for comparison in result2[0]:
        for idx, block in enumerate(result2[1]):
            if flag[idx] == True:
                continue
            for i in range(4):
                rotated_block, c = rotate(block)
                if rotated_block == comparison:
                    answer+=c
                    flag[idx] = True
                    break
                else:
                    block = rotated_block
            if flag[idx] == True:
                break
                
    return answer

def dfs(array, num, i, j, v):
    move = {(0,1), (0,-1), (1, 0), (-1,0)}
    if i<0 or j<0 or i>=len(array) or j>=len(array[0]) or array[i][j] != num or (i, j) in v:
        return v
    else:
        v.add((i,j))
        for c, r in move:
            dfs(array, num, i+c, j+r, v)
        return v
    
def rotate(puzzle):
    rotate = [[0] * len(puzzle) for _ in range(len(puzzle[0]))]
    count = 0
    for i in range(len(puzzle)):
        for j in range(len(puzzle[i])):
            if puzzle[i][j] == 1:
                count += 1
            rotate[j][len(puzzle) - 1 - i] = puzzle[i][j]
    return rotate, count    

