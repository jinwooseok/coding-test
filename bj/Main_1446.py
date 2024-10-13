'''
D킬로미터의 고속도록. 모든 지름길은 일방통행. 역주행 X
N개 - 최대 12개
시작, 도착, 길이.
dp로 해결가능할 듯
d크기의 dp배열을 활용.
'''

from collections import defaultdict
N, D = map(int,input().split())
dp = [i for i in range(D+1)]
roadinfos = defaultdict(list)
#시작점을 바로 찾을 수 있도록 딕셔너리로 간선정보 저장
for i in range(N):
    start, end, length = map(int,input().split())
    roadinfos[start].append([end, length])

for i in range(0,D+1):
    if i!=0:
        dp[i] = min(dp[i-1]+1,dp[i])
    if i in roadinfos.keys():
        for end, length in roadinfos[i]:
            if end>D:
                continue
            dp[end] = min(length+dp[i],dp[end])
print(dp[D])