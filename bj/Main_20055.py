'''
길이가 n인 컨베이어 벨트
길이가 2n인 커베이어벨트가 위아래로 있음
1번 : 올리는 위치
n번 : 내리는 위치
로봇이 이동하면 내구도 1 감소
1. 로봇과 함께 한칸 회전
2. 가장먼저 벨트에 올라간 로봇부터 회전방향으로 한칸 이동. 로봇이 없으면 내구도가 1이상 남아야함
3. 올리는위치의 칸의 내구도가 0일 때까지 로봇을 올림
4. 내구도가 0인 칸의 개수가 k개이상이면 과정 종료

1 : 컨베이어벨트를 한칸씩이동
2 : 로봇을 한칸씩 이동.
3 : 올리는 위치의 내구도를 판단하고 올림
4 : 내구도가 0인 칸의 개수를 판단

돌아가는 과정은 큐로 하면 쉬울듯하다. 벨트는 큐.
큐는 내구도와 로봇이 있는지.
첫 로봇의 위치만 정하기.
'''
from collections import deque
#한 칸 클래스
class Area:
    def __init__(self, cnt):
        self.cnt = cnt
        self.isRobot = False
#2개의 큐를 회전
def rotateBelt(conbainerq:deque, waitq:deque):
    conbainerq.appendleft(waitq.popleft())
    waitq.append(conbainerq.pop())
    #회전 후 맨 뒤에 로봇있음 내리기
    if conbainerq[-1].isRobot:
        conbainerq[-1].isRobot = False
        

#컨베이너 큐 1개를 돌며 로봇을 옮김
def moveRobot(conbainerq:deque, waitq:deque, k):
    #내리는 경우는 
    for i in range(0, len(conbainerq)):
        back:Area=conbainerq.pop()
        #그자리에 로봇이 있는데
        if back.isRobot:
            #이동하려는 위치에 로봇이 없고 내구도가 있으면 이동시키고 내구도 감소
            if conbainerq[0].isRobot is False and conbainerq[0].cnt>0:
                back.isRobot = False
                conbainerq[0].isRobot = True
                conbainerq[0].cnt-=1
                if conbainerq[0].cnt==0:
                    k+=1
            #그 외에는 안하기
        conbainerq.appendleft(back) #뒤를 앞으로 빼기
    #회전 후 맨 뒤에 로봇있음 내리기
    if conbainerq[-1].isRobot:
        conbainerq[-1].isRobot = False
    return k

def putRobot(conbainerq:deque, k):
    if conbainerq[0].cnt>0:
        conbainerq[0].isRobot = True
        conbainerq[0].cnt-=1
        if conbainerq[0].cnt==0:
            k+=1
    return k
    
waitq = deque()
conbainerq = deque()    
N, K = map(int, input().split())
tmp = list(map(int,input().split()))
for i in range(N):
    conbainerq.append(Area(tmp[i]))

for i in range(2*N-1, N-1, -1):
    waitq.append(Area(tmp[i]))
    
k = 0
level = 0
while k<K:
    level += 1
    rotateBelt(conbainerq, waitq)
    k=moveRobot(conbainerq, waitq,k)
    k=putRobot(conbainerq,k)
print(level)