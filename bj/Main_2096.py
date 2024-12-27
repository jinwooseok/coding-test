'''
내려가기 게임
첫줄에서 시작, 마지막 줄 끝
바로아래 혹은 인접한 수로만 이동 가능.
최대 배열, 최소 배열 따로 만들어둬보자
'''
import sys
input = sys.stdin.readline
N =int(input())
min_next = [float('inf')] * 3
min_prev = [0] * 3
max_next = [float('-inf')] * 3
max_prev = [0] * 3
boundary = [-1,0,1]
arr = [0]*3
for i in range(N):
    arr[0], arr[1], arr[2] = map(int, input().split())
    # print(min_prev)
    # print(max_prev)
    if i == 0:
        for i in range(3):
            min_prev[i] = arr[i]
            max_prev[i] = arr[i]
    else:
        for j in range(3):
            for k in boundary:
                if 0 <= j + k < 3:  # 유효 범위 확인
                    #print(j, min_prev[j + k] + arr[j])
                    min_next[j] = min(min_next[j], min_prev[j + k] + arr[j])
                    max_next[j] = max(max_next[j], max_prev[j + k] + arr[j])
        for i in range(3):
            min_prev[i] = min_next[i]
            max_prev[i] = max_next[i]
            min_next[i] = float('inf')
            max_next[i] = float('-inf')  
print(max(max_prev), min(min_prev))