'''
여러 색깔의 뿌요. 중력의 영향을 받음
다른 뿌요를 만날때까지 떨어짐

1. 뿌요를 놓기
2. 도착 후 같은 색 뿌요가 상하좌우인지 확인
3. 한꺼번에 없어짐
4. 중력의 영향으로 아래로 떨어짐
5. 연쇄작용

bfs로 상하좌우 체크. 4개 이상이면. 다 제거

'''
from collections import deque

# qlist = [deque() for i in range(6)]
# for i in range(6):    
#     idx = 11
#     while arr[idx][i] != '.':
#         qlist[i].append(arr[idx][i])
#         idx-=1
 
#상하좌우에 터지는게 있는지 확인하는 함수. 4개 이상이면 
MOVE = [(0,1),(1,0),(0,-1),(-1,0)]

#연쇄 하나
def chainProcess(arr):
    visited = [[False]*6 for i in range(12)]
    chains = []
    for i in range(12):
        for j in range(6):
            if arr[i][j] != '.' and not visited[i][j]:
                color = arr[i][j]
                chain = []
                #bfs 시작
                q = deque([(i,j)])
                visited[i][j] = True
                chain.append((i,j))
                count = 1
                while q:
                    r,c = q.popleft()
                    for dr, dc in MOVE:
                        nr = r + dr
                        nc = c + dc
                        
                        if 0<=nr<12 and 0<=nc<6 and not visited[nr][nc] and color == arr[nr][nc]:
                            visited[nr][nc] = True
                            q.append((nr,nc))
                            chain.append((nr,nc))
                            count+=1    
                #만약 count >= 4라면?
                if count >= 4:
                    #모은 체인을 나중에 한번에 pop할 체인리스트에 넣음
                    chains.append(chain)        
    return chains
    #pop할 리스트 반환    

def getNextArr(arr, chains):
    newArr = [['.' for i in range(6)] for i in range(12)]
    for chain in chains:
        for r, c in chain:
            arr[r][c] = '.'
    q = deque()
    for i in range(6):    
        for j in range(11, -1, -1):
            if arr[j][i] != '.':
                q.append(arr[j][i])
        idx = 11        
        while q:
            color = q.popleft()
            newArr[idx][i] = color
            idx -= 1
    # for i in newArr:
    #     print(i)
    return newArr
    
    
def main():
    chainCnt = 0
    arr = [list(input()) for i in range(12)]
    while True:
        chains = chainProcess(arr) #한 사이클의 체인리스트 반환
        if len(chains) == 0:
            return chainCnt
        else:
            chainCnt += 1
            arr = getNextArr(arr, chains)
        
print(main())