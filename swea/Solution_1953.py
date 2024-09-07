'''
지하 터널 어딘가 은신중
탈주범이 ㅣㅇㅆ을 수 있는 위치의 개수 계산
1. 상하좌우
2. 상하
3. 좌우

4. 상우
5. 하우
6. 하좌
7. 상좌

0. 가만히 있는 경우 포함
dfs가 생각이 나네요..

'''
import sys
sys.setrecursionlimit(10**6)
#이동가능방향들을 모아놓은 함수
MOVE = [(-1,0),(1,0),(0,-1),(0,1)]
def move(command): #반환하는 것은 MOVE의 인덱스 리스트. FOR문에서 해당 리스트만 탐색.
    if command == 1:
        return [0,1,2,3]    
    elif command == 2:
        return [0,1]
    elif command == 3:
        return [2,3]
    elif command == 4:
        return [0,3]
    elif command == 5:
        return [1,3]
    elif command == 6:
        return [1,2]
    elif command == 7:
        return [0,2]
    
def isImPossible(direction, next):
    if next == 0:
        return True
    elif direction==0 and next in [3,4,7]:
        return True
    elif direction==1 and next in [3,5,6]:
        return True
    elif direction==2 and next in [2,6,7]:
        return True
    elif direction==3 and next in [2,4,5]:
        return True
    return False

def outMask(r,c,n,m):
    return r<0 or c<0 or r>=n or c>=m
#dfs함수 구현
def dfs(arr:int, visited:list, dp:list, r:int, c:int, depth:int, R:int):
    #5칸오면 종료
    if depth == R:
        return
    #for문은 move로 부터 방향 리스트를 전달받고 해당위치에서 가능한 것에 따라 for문.
    cnt = 0
    for direction in move(arr[r][c]):
        nr, nc = r+MOVE[direction][0], c+MOVE[direction][1]
        #배열 벗어난 경우
        if outMask(nr,nc,N,M): 
            continue
        #갈수없는 파이프?
        if isImPossible(direction, arr[nr][nc]):
            continue
        #방문안했으면 한걸로 표시. 
        #방문했던 경우 가지 않음. visited
        #방문배열은 백트래킹하지 않아도 될듯
        if visited[depth+1][nr][nc]:
            continue
        else:
            visited[depth+1][nr][nc]=True
            dp[nr][nc]=True
            dfs(arr, visited, dp, nr, nc, depth+1, R)        
    
    
#입력
T = int(input())
for t in range(1,T+1):
    N, M, startR, startC, lastTime = map(int, input().split())
    arr = [list(map(int, input().split())) for i in range(N)]
    
    #visited배열 생성
    visited = [[[False for i in range(M)] for i in range(N)] for i in range(lastTime+1)]
    dp=[[False]*M for i in range(N)]
    #현재 위치 방문 표시
    visited[0][startR][startC] = True
    dp[startR][startC] = True
    #dfs 실행.
    dfs(arr, visited,dp,startR,startC,0,lastTime-1)    
    cnt = 0
    #print(dp)
    for i in range(N):
        for j in range(M):
            if dp[i][j]:
                cnt+=1
    print(f"#{t} {cnt}")
    