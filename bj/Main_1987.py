R, C = map(int, input().split())
arr = [list(input()) for i in range(R)]
visited = 0

from collections import deque

MOVE = [(0,1),(1,0),(0,-1),(-1,0)]
q = deque()
#현재 위치, visited 비트
q.append((0,0,0|(1<<(ord(arr[0][0])-ord('A')))))
level = 0
visited = [[set() for i in range(C)] for j in range(R)]
visited[0][0].add(1<<(ord(arr[0][0])-ord('A'))) #방문에 set추가
while q:
    size = len(q)
    #한 단계
    for i in range(size):
        r,c,curbit = q.popleft()
        for dr, dc in MOVE:
            nr = r+dr
            nc = c+dc
            #벗어났으면 건너뛰기
            if nr<0 or nc<0 or nr>=R or nc>=C:
                continue
            #방문했으면 건너뛰기
            bit = ord(arr[nr][nc])-ord('A')
            if curbit & (1<<bit):
                continue
            #현재 세트가 다른 곳을 방문했을 때와 똑같은 경우
            nextbit = curbit | (1<<bit)
            if nextbit in visited[nr][nc] :
                #print(level,visited[nr][nc], bin(curbit))
                continue
            else:
                
                visited[nr][nc].add(nextbit)
                q.append((nr,nc,nextbit))
    level+=1 #단계 업
print(level)