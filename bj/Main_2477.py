'''
한쪽 모퉁이가 비어있을 때 면적을 구하는 문제이다.
로직
---
기존에 이동방향은 반시계 방향이다.
하지만 움푹 들어간 구간은 이동하는 순간 반드시 반시계 방향이 아닌 시계방향으로 이동하게 된다.
그점을 활용해서 갑자기 방향이 바뀌는 구간의 넓이를 구하면 끝이다.

입력
1. 1m^2에 자라는 참외의 개수
2. 참외밭에서 이동방향, 이동거리

설계
1. 입력값을 먼저 받는다.
2. 방향이 바뀌는 부분을 체크한다. 해당 너비를 구한다.
3. 동시에 최대길이와 최소길이도 체크한다. 해당 너비를 구한다.
4. 한바퀴를 돌고난 후 참외의 수 * (최대너비- 작은너비) 로 구한다.
'''
import sys
fruit_cnt = int(sys.stdin.readline())

farm = [[0,0] for i in range(7)]
direction, length = map(int, sys.stdin.readline().split())
#끝지점을 한칸 추가해주면 방향이 바뀌는 부분을 체크할 때 더 편해진다.
#시작지점
farm[0][0] = direction
farm[0][1] = length
#끝지점
farm[6][0] = direction
farm[6][1] = length
for i in range(1,6):
    direction, length = map(int, sys.stdin.readline().split())
    farm[i][0] = direction
    farm[i][1] = length

internal_width = 0
max_row, max_col = 0, 0
for i in range(6):
    if farm[i][0] == 1:
        max_row = max(max_row, farm[i][1])
        if farm[i+1][0] == 3:
            internal_width = farm[i][1] * farm[i+1][1]
    elif farm[i][0] == 2:
        max_row = max(max_row, farm[i][1])
        if farm[i+1][0] == 4:
            internal_width = farm[i][1] * farm[i+1][1]
    elif farm[i][0] == 3:
        max_col = max(max_col, farm[i][1])
        if farm[i+1][0] == 2:
            internal_width = farm[i][1] * farm[i+1][1]
    elif farm[i][0] == 4: 
        max_col = max(max_col, farm[i][1])
        if farm[i+1][0] == 1:
            internal_width = farm[i][1] * farm[i+1][1]
total_width = max_row*max_col
print(fruit_cnt*(total_width - internal_width))