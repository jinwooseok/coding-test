'''
N,M.
성이 있는 칸에 궁수 배치 가능. 3명 배치
D이하인 적중에서 가장 가까운적, 맞으면 게임 제외
1. 궁수의 공격 확인
2. 공격 후 제외.
3. 남은 적 이동
반복
성 도착 시 제외
이 때 성에 도착한게 아닌 궁수의 공격으로 제거할 수 있는 적의 최대 수

위치를 항상 순회할 순 없으니까 적들을 큐에 넣자. 적들이 죽었을 때 빼기도 쉽고
비교도 간단하다. 일차원 배열의 경우 적이 죽었을 때 빼기 어려움
'''

#궁수 배치 함수 - 조합
import copy
maxcnt = 0
def select(N, M, result, idx,  depth, D, enemyq):
    global maxcnt
    if depth == 3:
        copiedq = copy.deepcopy(enemyq)
        maxcnt = max(maxcnt,simulation(copiedq, result, D, N))
        return
    if idx >= M:
        return
    result[depth] = idx
    select(N, M, result, idx+1, depth+1, D, enemyq)
    select(N, M, result, idx+1, depth, D, enemyq)
#궁수 공격 함수 - 큐 한바퀴돌며 맞는 적을 선택. 3번 다 선택. 적을 제거하는건 나중에 한꺼번에이므로 나중에 한번에 빼기-중요함
def attack(enemyq, attackerlist, D, N):
    targets = set()
    for attacker in attackerlist:
        target = None
        mindist = float('inf')
        for i in range(len(enemyq)):
            dist = abs(N-enemyq[i][0]) + abs(attacker-enemyq[i][1])
            if dist <= D and dist < mindist:
                mindist = dist
                target = (enemyq[i][0], enemyq[i][1])
        
        if target is not None:
            targets.add(target)
    cnt = len(targets)
    for i in range(len(enemyq)):
        enemy = enemyq.popleft()
        if enemy in targets:
            continue
        if enemy[0]+1 == N:
            continue
        enemyq.append((enemy[0]+1, enemy[1])) #문제 없다면 한칸 내려보내고 통과
    return cnt

def simulation(enemyq, attackerlist, D, N):
    cnt = 0
    while enemyq:
        cnt += attack(enemyq, attackerlist, D, N)
    
    return cnt
from collections import deque
N, M, D = map(int, input().split())
arr = [list(map(int, input().split())) for i in range(N)]
enemyq = deque()
for i in range(M):
    for j in range(N):
        if arr[j][i] == 1:
            enemyq.append((j,i))

select(N, M, [0]*3, 0, 0, D, enemyq)
print(maxcnt)