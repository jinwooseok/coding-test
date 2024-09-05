'''
n^2명의 학생.
각 학생은 4명의 학생을 좋아ㅏㅎㅁ.
1-base Index
사방에 있을 경우 인접하다고 한다.
좋아하는 학생은 인접행렬을 통해 저장
1.인접한칸에 좋아하는 학생이 많은 칸으로 자리를 ㅓㅈㅇ한다.
2. 1번이 여러개이면 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
3. 2번도 여러개인 경우는 행번호, 열번호가 가장 작은 칸으로 자리를 정한다.

for문으로 위에서부터 하나하나 검토함.
1.좋아하는 학생이 없는 경우를 탐색
2.없다면 비어있는 칸 4칸부터 줄여나감
'''
import sys

N = int(sys.stdin.readline())

adjArr = [[0]*(N**2+1) for i in range(N**2+1)]
Map = [[0]*N for i in range(N)]
seq = [0]*(N**2)
for i in range(N**2):
    a, b1, b2, b3, b4= map(int, sys.stdin.readline().split())
    seq[i] = a
    adjArr[a][b1] = 1
    adjArr[a][b2] = 1
    adjArr[a][b3] = 1
    adjArr[a][b4] = 1

MOVE = [(0,1),(1,0),(0,-1),(-1,0)]
def searchLikey():
    #널을 숫자
    for num in seq:
        #배열 순회
        dic = {0:[],1:[],2:[],3:[],4:[]}
        for i in range(N):
            for j in range(N):
                if Map[i][j]!=0:
                    continue
                #사방탐색
                cnt = 0
                for nr, nc in MOVE:
                    if (i+nr<0 or j+nc<0 or i+nr>=N or j+nc>=N):
                        continue
                    if (adjArr[num][Map[i+nr][j+nc]]):
                        cnt += 1
                dic[cnt].append([i, j])
        for i in range(4,-1,-1):
            if len(dic[i])==1:
                loc = dic[i][0]
                Map[loc[0]][loc[1]]=num
                break
            elif len(dic[i])>1:
                loc = searchMaxEmpty(dic[i])
                Map[loc[0]][loc[1]]=num
                break
def searchMaxEmpty(li):
    # 배열 순회
    dic = {0: [], 1: [], 2: [], 3: [], 4: []}
    for i, j in li:
        # 사방탐색
        cnt = 0
        for nr, nc in MOVE:
            if (i+nr<0 or j+nc<0 or  i+nr>=N or j+nc>=N):
                continue
            if (Map[i + nr][j + nc] == 0):
                cnt += 1
        dic[cnt].append([i, j])
    for i in range(4,-1,-1):
        if len(dic[i])==1:
            return dic[i][0]
        elif len(dic[i])>1:
            return dic[i][0]

searchLikey()
cntSum=0
for i in range(N):
    for j in range(N):
        #사방탐색
        cnt = 0
        for nr, nc in MOVE:
            if (i+nr<0 or j+nc<0 or i+nr>=N or j+nc>=N):
                continue
            if (adjArr[Map[i][j]][Map[i+nr][j+nc]]):
                cnt += 1
        if cnt!=0:
            cntSum+=10**(cnt-1)    
print(cntSum)