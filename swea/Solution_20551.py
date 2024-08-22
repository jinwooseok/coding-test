'''
뒤에서부터 이동하면서 조건을 만족하되 최소한의 사탕을 먹도록한다.
253 이라면 세번째가 3이므로 두번째로 이동했을 때 3-1=2만큼 남기게 하면 최소한으로 먹을 수 있다.
불가능한 경우 : 현재 숫자가 1인 경우 
'''

def func(boxes):
    cnt = 0
    for i in range(2,0,-1):
        if boxes[i-1]<boxes[i]:
            continue
        elif boxes[i]==1:
            return -1        
        else:
            cnt+=boxes[i-1]-(boxes[i]-1)
            boxes[i-1] = boxes[i]-1
    return cnt

T = int(input())
for t in range(1, T+1):
    boxes = list(map(int, input().split()))
    print(f"#{t} {func(boxes)}")