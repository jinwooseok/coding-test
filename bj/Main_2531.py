'''
원하는만큼 초밥 골라먹기 가능. 단 순서대로 먹을 경우 할인된 가격
초밥의 종류가 1개! 쓰인 쿠폰 발행 후 참가할 경우 무료 제공. 벨트위에 없을 경우 추가도 가능

원하는 것은 초밥 가짓수의 최댓값

경우의 수
1. 무작위로 k개 골라먹기 -> 조합써야함. 최대 3000개 종류 중 k개 선택. 최대는 1500개 선택. 1500*750
2. 연속된 k개 골라먹는데 쿠폰 못씀
3. 연속된 k개 + 쿠폰 가능

즉, 가능한 경우는 k개의 종류를 먹거나 k+1개를 먹거나
연속되게 한바퀴 돌면서 k개를 다 먹을 수 있는지 보자
'''
# import sys
# from collections import defaultdict
# sys.setrecursionlimit(10**6)
# input = sys.stdin.readline
# sushiCnt, sushiTypeCnt, seqDishCnt, couponNum = map(int, input().split())
# belt = [0]*sushiCnt
# sushiTypes = defaultdict(bool)

# for i in range(sushiCnt):
#     belt[i] = int(input())

# def dfs(belt, sushiTypes, startIndex, lastIndex, couponNum, maxCnt):
#     result = 0
#     if startIndex == lastIndex:
#         if sushiTypes[couponNum] == False:
#             return maxCnt+1
#         else:
#             return maxCnt 
#     else:
#         if sushiTypes[belt[startIndex]]==False:
#             sushiTypes[belt[startIndex]] = True
#             result=dfs(belt, sushiTypes, (startIndex+1)%len(belt), lastIndex, couponNum, maxCnt+1)
#             sushiTypes[belt[startIndex]] = False
#         else:
#             result=dfs(belt, sushiTypes, (startIndex+1)%len(belt), lastIndex, couponNum, maxCnt)
#     return max(maxCnt,result)

# maxCnt = 0
# for i in range(sushiCnt):
#     maxCnt = max(maxCnt,dfs(belt, sushiTypes, i, (i+seqDishCnt)%sushiCnt, couponNum, 0))
# print(maxCnt)
# 초기 윈도우 설정

import sys
from collections import defaultdict
input = sys.stdin.readline
sushiCnt, sushiTypeCnt, seqDishCnt, couponNum = map(int, input().split())
belt = [0]*sushiCnt
sushiTypes = defaultdict(int)

for i in range(sushiCnt):
    belt[i] = int(input())
maxCnt = 0
#초기 설정
for i in range(seqDishCnt):
    sushiTypes[belt[i]] += 1

maxCnt = len(sushiTypes)
if couponNum not in sushiTypes.keys():
    maxCnt += 1
#print(sushiTypes, maxCnt)
#슬라이딩 시작
for i in range(1, sushiCnt):
    sushiTypes[belt[(i + seqDishCnt - 1)%sushiCnt]] += 1
    
    sushiTypes[belt[i-1]] -= 1
    
    if sushiTypes[belt[i-1]] == 0:
        del sushiTypes[belt[i-1]]
    
    curCnt = len(sushiTypes)
    if couponNum not in sushiTypes.keys():
        curCnt += 1
    #print(sushiTypes, curCnt)
    maxCnt = max(maxCnt, curCnt)
    
print(maxCnt)