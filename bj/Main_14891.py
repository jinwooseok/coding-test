from collections import deque
gears = []
for i in range(4):
    gears.append(deque(list(input())))
    
rotateCnt = int(input())
commands = []
for i in range(rotateCnt):
    commands.append(list(map(int,input().split())))

#명령에 따라 회전하는 함수
def rotate(target, direction):
    if direction == 1:
        target.appendleft(target.pop())
    else:
        target.append(target.popleft())
        
#두 바퀴의 관계를 검사하는 함수
def check(gear, target, direction, alpha):
    #같으면 회전 x, 다르면 회전o
    gearNum = 2
    targetNum = 6
    if alpha == -1:
        gearNum = 6
        targetNum = 2
        
    if gear[gearNum] != target[targetNum]:
        return True
    return False

# last check
def lastCheck(gears):
    score = 0
    if gears[0][0]=='1':
        score+=1
    
    if gears[1][0]=='1':
        score+=2
    
    if gears[2][0]=='1':
        score+=4
    
    if gears[3][0]=='1':
        score+=8

    return score

def operation(gears, startGear, direction, alpha):
    if startGear+alpha<0 or startGear+alpha>=len(gears):
        return
    if check(gears[startGear], gears[startGear+alpha], direction, alpha):
        operation(gears,startGear+alpha,-1*direction, alpha)
        rotate(gears[startGear+alpha],direction)
    else:
        return
            
for command in commands:
    #print(gears)
    operation(gears, command[0]-1, -1*command[1], -1)
    operation(gears, command[0]-1, -1*command[1], 1)
    rotate(gears[command[0]-1], command[1])
    
print(lastCheck(gears))