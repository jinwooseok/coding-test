'''
국경선을 공유하는 두 나라의 인구차이가 L<=R이라면국경선을 하루동안 연다.
국경선이 모두 열렸다면 인구이동을 시작
사방탐색 DFS로 연합을 찾음. visited로 구분. 모든 연합을 찾고
바로 연합대로 계산하면 다음 연합에 영향을 줄 가능성이 있음
그러므로 모든 연합을 구한 후 한번에 계산
visited에는 0과 1이 아닌 나눠진 숫자로 구분.
결과가 다음으로 이어짐. 그냥 백트래킹 말고 한번에 계산
2500*2000*배열생성하기 등 할만하다.
'''
import sys
sys.setrecursionlimit(10**8)
MOVE = [[0,1],[0,-1],[1,0],[-1,0]]
def dfs(arr, after, r, c, start, N, L, R):
    population = 0
    cnt = 0
    after[r][c] = start
    for dr, dc in MOVE:
        nr = r+dr
        nc = c+dc
        if nr<0 or nc<0 or nr>=N or nc>=N or after[nr][nc]!=0:
            continue
        if L<=abs(arr[nr][nc]-arr[r][c])<=R:
            #조건에 맞다면 끝까지 간다. 끝까지 갔을 때의 개수와 총합을 더함. 돌아올 때 다음 배열에 삽입
                p1, c1 = dfs(arr, after, nr, nc, start, N, L, R)
                population+=p1
                cnt+=c1
    return population+arr[r][c], cnt+1

N, L, R = map(int, input().split())
arr = [list(map(int, input().split())) for i in range(N)]
visited = [[False]*N for i in range(N)]
result = 0
while True:
    pop_map={}
    after = [[0]*N for i in range(N)]
    union = False
    #print(arr)
    for i in range(N):
        for j in range(N):
            if after[i][j]==0:
                population, cnt = dfs(arr, after, i, j, i*N+j+1, N, L, R)
                if cnt > 1:  # 연합이 형성된 경우만 처리
                        pop_map[i * N + j + 1] = population // cnt
                        union = True
    # 더 이상 이동할 연합이 없다면 종료
    if not union:
        break

    # 모든 연합에 대해 인구 이동 적용
    for i in range(N):
        for j in range(N):
            arr[i][j] = pop_map[after[i][j]] if after[i][j] in pop_map else arr[i][j]

    result += 1
print(result)