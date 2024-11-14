'''
틀림 -> 턱걸이 5회
아침 식사 대신해 계란찜을 먹음
?? 아씨 이거 문제 아니잖아 개열심히 읽고 있었네

계란끼리 대결하면 상대 계란만큼 깎임
내구도 0이되면 파괴

왼쪽부터 다른 계란을 쳐서 최대한 많은 계란을 깸
1. 가장 왼쪽의 계란을 듬
2. 깨지지 않는 다음 계란 중 하나를 침. 손에 든 계란이 깨졌거나 다른 계란이 다깨졌으면 넘어감(종료). 원래자리에 두기. (한번 든 계란은 한번만 듬)
3. 가장 최근에 든 계란의 한칸 오른쪽 계란을 다시 침. 끝까지 했으면 종료

계란 수 8개 1개들고 7개 중 하나를 선택.
7**7 = 823543
백트 dfs
그리디안이분탐색안됨
'''

#[내구도, 무게]
def dfs(eggs, idx, cnt, maxCnt):
    if idx == len(eggs):
        maxCnt[0] = max(maxCnt[0], cnt)
        return
    if len(eggs)-cnt < maxCnt[0]-cnt:
        return
    if eggs[idx][0]<=0:
        dfs(eggs, idx+1, cnt, maxCnt)
        return
    for i in range(0, len(eggs)):
        if i != idx:
            if eggs[i][0]>0:
                eggs[i][0] -= eggs[idx][1]
                eggs[idx][0] -= eggs[i][1]
                if eggs[i][0]<=0 and eggs[idx][0]<=0:
                    dfs(eggs, idx+1, cnt+2, maxCnt)
                elif eggs[i][0]<=0 or eggs[idx][0]<=0:
                    dfs(eggs, idx+1, cnt+1, maxCnt)
                else:
                    dfs(eggs, idx+1, cnt, maxCnt)
                eggs[i][0] += eggs[idx][1]
                eggs[idx][0] += eggs[i][1]
            else:
                dfs(eggs, idx+1, cnt, maxCnt)
    return

eggs = []
import sys
input = sys.stdin.readline
N = int(input())
for i in range(N):
    eggs.append(list(map(int,input().split())))
maxCnt = [0]
dfs(eggs, 0, 0, maxCnt)
print(maxCnt[0])