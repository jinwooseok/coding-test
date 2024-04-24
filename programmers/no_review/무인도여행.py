import sys
sys.setrecursionlimit(10**6)
def solution(maps):
    answer = []
    visited = [False]*len(maps[0])*len(maps)
    for i in range(len(maps)):
        for j in range(len(maps[0])):
            if visited[i*len(maps[0])+j]==False and maps[i][j] != "X":
                visited, count = dfs(maps, i, j, visited, 0)
                answer.append(count)
                
    if len(answer) == 0:
        return [-1]
    else:
        return sorted(answer)

MOVE = [(0, 1), (1, 0),(-1,0),(0,-1)]
def dfs(maps, r, c, visited, count):
    visited[r*len(maps[0])+c]=True
    count += int(maps[r][c])

    for mr, mc in MOVE:
        new_r, new_c = r + mr, c + mc
        if new_r < 0 or new_c < 0 or new_r >= len(maps) or new_c >= len(maps[0]) or maps[new_r][new_c] == "X":
            continue
        if visited[new_r*len(maps[0])+new_c]:
            continue
        visited, count = dfs(maps, new_r, new_c, visited, count)

    return visited, count