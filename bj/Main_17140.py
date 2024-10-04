'''
calR : 모든 행 정렬 수행. 행의 개수>= 열의 개수인 경우에 적용
calC : 모든 열 정렬 수행. 행의 개수 < 열의 개수인 경우에 적용
수의 등장 횟수가 커지는 순, 수가 커지는 순. 결과 다시 넣기
while True:
    if arr[r][c]==k:
        break
    else:
        함수 실행.
'''
from collections import defaultdict
r,c,k = map(int, input().split())
arr = [list(map(int, input().split())) for i in range(3)]

def cal(arr):
    results = []
    if len(arr)<len(arr[0]):
        for i in range(len(arr[0])):
            result = defaultdict(int)
            for j in range(len(arr)):
                if arr[j][i]!=0:
                    result[arr[j][i]]+=1
            result = list(result.items())
            result=sorted(result,key = lambda x:(x[1],x[0]))
            results.append(result)
        maxLen = 0
        for i in range(len(results)):
            maxLen=max(maxLen,len(results[i]))
        if maxLen>50:
            maxLen=50
        newarr = [[0]*len(results) for i in range(maxLen*2)]
        for i in range(len(results)):
            result = results[i]
            for j in range(len(result)):
                if j>=50:
                    break
                newarr[j*2][i] = result[j][0]
                newarr[j*2+1][i] = result[j][1]
    else:
        for i in range(len(arr)):
            result = defaultdict(int)
            for j in range(len(arr[0])):
                if arr[i][j]!=0:
                    result[arr[i][j]]+=1
            result = list(result.items())
            result=sorted(result,key = lambda x:(x[1],x[0]))
            results.append(result)
        maxLen = 0
        for i in range(len(results)):
            maxLen=max(maxLen,len(results[i]))
        if maxLen>50:
            maxLen=50
        newarr = [[0]*maxLen*2 for i in range(len(results))]
        for i in range(len(results)):
            result = results[i]
            for j in range(len(result)):
                if j>=50:
                    break
                newarr[i][j*2] = result[j][0]
                newarr[i][j*2+1] = result[j][1]
    return newarr
time = 0
while True:
    if 1<=r<=len(arr) and 1<=c<=len(arr[0]) and arr[r-1][c-1]==k:
        break
    if time>=100:
        time=-1
        break
    else:
        time+=1
        arr=cal(arr)

print(time)