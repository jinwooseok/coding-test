'''
조건
- 정사각형 => 서로 겹치면 안됨
- 도형은 모두 연결
- 정사각형의 꼭짓점끼리 맣다으면 안됨

즉, 정사각형은 선분으로 만나야 한다는 뜻
정사각형 4개를 이어붙여 5개의 모양을 만들 수 있음.
근데 이 5개 모양 이외의 모양은 절대로 불가능하게 나오는 듯하다.
이때 최대값을 구하시오. 회전이나 대칭을 시켜도됨.

설계
1. 모양을 만들어놓고 회전하고 갖다붙이고 이러쿵저러쿵하기
2. 정사각형을 막 갖다놓고 테트로미노인지 판단하기
3. dfs로 테트로미노를 만들고 테스트하자. 
4. dp를 사용 가능한가? 어려울거같다.

3. dfs
dfs를 사용하여 만든다.
for문으로 4칸까지의 범위를 탐색. 
왔던 곳을 또오지 않게 방문처리 + 오른쪽과 아래 방향으로만 전개시키면 중복없이 진행가능한가 싶다.
방문처리는 백트래킹으로 되돌려놓기, 리턴값 사용해도 될듯
250000*4
'''

#입력부분
import sys
N, M=map(int, sys.stdin.readline().split())
arr=[list(map(int, sys.stdin.readline().split())) for i in range(N)]

#로직 함수
MOVE = [[0,1],[1,0],[-1,0],[0,-1]]
MOVE2 = [[0,1],[1,0],[0,-1]]
visited = [[False]*M for i in range(N)]
def dfs(r,c,cnt,depth):
    if r<0 or c<0 or r>=N or c>=M:
        return 0
    if visited[r][c]==True:
        return 0
    if depth==4:
        return cnt
    #백트래킹
    visited[r][c]=True
    sumnum=0
    for dr, dc in MOVE2:
        sumnum=max(sumnum,dfs(r+dr,c+dc,cnt+arr[r][c],depth+1))
    visited[r][c]=False
    return sumnum

def fuckyouShape(r,c):
    maxcnt = 0
    for i in range(4):
        cnt = arr[r][c]
        for j in range(4):
            if i!=j:
                if r+MOVE[j][0]<0 or c+MOVE[j][1]<0 or r+MOVE[j][0]>=N or c+MOVE[j][1]>=M:
                    break
                cnt+=arr[r+MOVE[j][0]][c+MOVE[j][1]]
        maxcnt = max(maxcnt, cnt)
    return maxcnt

maxsum = 0
for i in range(N):
    for j in range(M):
        maxsum=max(maxsum,dfs(i,j,0,0))
        maxsum=max(maxsum, fuckyouShape(i,j))
print(maxsum)