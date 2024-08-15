'''
배열을 이동하는 문제입니다.
단 벽에 부딫힐 시 방향 변경에 신경써줘야 하는 문제입니다.
0,0이 아래쪽에 위치하고 (row, col) 이 아닌 (x, y)로 위치가 설정된 것에 유의해야 함 

로직
이동 로직이 핵심이다.
1. x와 y를 따로 보고 배열이 한계없이 무한대라고 생각한다.
2. 그리고 나면 x와 y의 총 이동이 나오는데 이 각각 x,y를 따로 본다면 그냥 다들 왔다갔다만 하고있다는 것을 알 수 있다.
3. x,y가 각각 왔다갔다 하는 것을 알았다면 그 뒤로는 두가지 경우의 수로 줄어든다. 1:반대로 이동하는 경우 2:정방향으로 이동하는 경우이다
4. 이동 방향에 따라 위치를 알 수 있을 것이고, 이러면 시뮬레이션을 돌리지 않아도 된다. 

입력값
1. 너비, 높이
2. 초기좌표(x,y)
3. 움직일 시간

설계
- 입력값 받기
- 이동 로직 구현
'''
import sys
width, height = map(int,sys.stdin.readline().split())
x, y = map(int, sys.stdin.readline().split())
time = int(sys.stdin.readline())

if ((x+time)//width)%2 == 0:
    x = (x+time)%width
else:
    x = width-(x+time)%width
   
if ((y+time)//height)%2 == 0:
    y = (y+time)%height
else:
    y = height-(y+time)%height

print(x, y)

