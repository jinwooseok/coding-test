'''
상담하는데 필요한 기간과 돈이 다름
1일에 T가 3인 상담을 하면 4일부터 다른 상담 가능
백준이가 얻을 수 있는 최대 수익은?

부분집합 or 그리디

그리디 x - 날짜에 따라 상담이 다르고 기간도 다름. 
최대부분집합 - 2^16-1. 할만할듯
'''
import sys
sys.setrecursionlimit(10**6)

#비트마스크로 총합 계산. 날짜는 기간만큼 건너뛰게됨
def cal_total(arr, visited):
    i = 0
    total=0
    while i<len(arr):
        if visited & (1<<i) and i+arr[i][0]<=len(arr):
            total+=arr[i][1]
            i+=arr[i][0]
        else:
            i+=1
    return total
#부분집합 구하기
max_num = 0 #저장할 전역 max_num
def subset(arr, visited, start_idx):
    if (start_idx>=len(arr)): #기저조건 : 부분집합 구했으면 최대 이익 계산
        global max_num
        max_num=max(max_num,cal_total(arr, visited))
        return
    subset(arr, visited|(1<<start_idx), start_idx+1)#선택하고
    subset(arr, visited, start_idx+1)#선택안하고

total_date=int(sys.stdin.readline())
schedules = [0]*total_date
for i in range(total_date):
    schedules[i] = list(map(int,sys.stdin.readline().split()))

subset(schedules, 0, 0)
print(max_num)

total_date=int(sys.stdin.readline())
schedules = [0]*total_date
dp=[0]*21 # 15+7 까지 가능
for i in range(total_date):
    schedules[i] = list(map(int,sys.stdin.readline().split()))

for i in range(total_date):
    dp[i+1] = max(dp[i+1], dp[i])
    dp[i+schedules[i][0]] = max(dp[i]+schedules[i][1], dp[i+schedules[i][0]])
print(dp[total_date])