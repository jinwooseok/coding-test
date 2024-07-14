T = int(input())
MOVE = [[0,1],[0,-1],[1,0],[-1,0],[1,1],[1,-1],[-1,1],[-1,-1]]
                
def cnt_mine(arr):
    N = len(arr)
    for r in range(N):
        for c in range(N):
            if arr[r][c] == '*':
                continue
            cnt = 0
            for move_r, move_c in MOVE:
                nr = r + move_r
                nc = c + move_c
                if 0 <= nr < N and 0 <= nc < N and arr[nr][nc] == '*':
                    cnt += 1
            arr[r][c] = cnt

def dfs(arr,r,c):
    N = len(arr)
    if arr[r][c] == 0:
        arr[r][c] = -1
        for move_r, move_c in MOVE:
            nr = r + move_r
            nc = c + move_c
            if 0 <= nr < N and 0 <= nc < N and arr[nr][nc] != '*':
                dfs(arr,nr,nc)
    elif arr[r][c] != '*':
        arr[r][c] = -1

for i in range(T):
    N = int(input())
    arr = [list(input()) for _ in range(N)]
    count = 0
    cnt_mine(arr)

    for r in range(N):
        for c in range(N):
            if arr[r][c] == 0:
                dfs(arr,r,c)
                count+=1
    print(count)
    for r in range(N):
        for c in range(N):
            if arr[r][c] != -1 and arr[r][c] != '*':
                count+=1
    print(count)
    print(f"#{i+1} {count}")
