'''
마법사 상어가 크기가 n*n인 격자에 파이어볼 m개를 발사.
질량 m, 방향 d, 속력 s

1. 모든 파이어볼이 d로 s칸 이동. 이동하는 중에는 여러개의 파이어볼이 있을 수 있음
2. 이동 종료 후 파이어볼이 2개이상 있으면 하나로 합치기, 4개로 나누기. 4개의 질량은 
    합쳐지는 파이어볼의 방향이 홀수 or 짝수이면 0246 아니면 1356
지ㅣㄹ량이 0인 파이어볼은 소멸되어 없어진다.


k가 1000. 이동하는 도중엔 신경안써도 되니까 한번에 옮기자.
1. 이동하기
2. 2개이상을 저장할수있어야함. 각 칸은..
3. 힙은 매번 정렬 오래걸릴듯함
'''
from collections import deque
import sys
#방향 0~8
DIRECTIONS = [(-1,0),(-1,1),(0,1),(1,1),(1,0),(1,-1),(0,-1),(-1,-1)]

#한칸에는 무조건 하나
def move(arr, N):
    for r in range(N):
        for c in range(N):
            size=len(arr[r][c])
            for i in range(size):
                fireBall = arr[r][c].popleft()
                if fireBall.visited:
                    continue
                else:
                    fireBall.visited = True
                    next_r = (N+r+fireBall.s*DIRECTIONS[fireBall.d][0])%N
                    next_c = (N+c+fireBall.s*DIRECTIONS[fireBall.d][1])%N
                    arr[next_r][next_c].append(fireBall)

def magic(arr, N):
    #2개 이상의 파이어볼이 있는 칸을 찾기.
    for r in range(N):
        for c in range(N):
            size = len(arr[r][c])
            if size>=2:
                fireBall=arr[r][c].popleft()
                msum, ssum = fireBall.m, fireBall.s 
                isOdd = fireBall.d%2
                direction = -1
                while arr[r][c]:
                    #합쳐지는 파이어볼
                    #합쳐질 때 바이어폴의 방향이 모두 홀수이거나 짝수인지 체크
                    fireBall=arr[r][c].popleft()
                    msum+=fireBall.m
                    ssum+=fireBall.s
                    if fireBall.d%2 != isOdd and direction == -1:
                        direction = 1
                if direction == -1:
                    direction = 0
                
                next_m = msum//5
                if next_m <= 0:
                    continue
                next_s = ssum//size
                for i in range(direction, 8, 2):
                    arr[r][c].append(FireBall(next_m, next_s, i, False))
                
def countFireBall(arr, N):
    cnt = 0
    for i in range(N):
        for j in range(N):
            while arr[i][j]:
                cnt+=arr[i][j].popleft().m
    return cnt

input = sys.stdin.readline
#파이어볼
class FireBall:
    def __init__(self, m, s, d, visited):
        self.m = m
        self.s = s
        self.d = d
        self.visited = visited
N, M, K = map(int, input().split())

arr = [[deque() for i in range(N)] for i in range(N)]
for i in range(M):
    r,c,m,s,d=map(int,input().split())
    arr[r-1][c-1].append(FireBall(m,s,d,False))
    
for i in range(K):
    print(arr)
    move(arr, N)
    magic(arr, N)
    print(arr)

print(countFireBall(arr, N))