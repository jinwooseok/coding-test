import sys
total_date=int(sys.stdin.readline())
schedules = [list(map(int,sys.stdin.readline().split())) for i in range(total_date)]
dp=[0]*(total_date+50)

for i in range(total_date):
    dp[i+1] = max(dp[i+1], dp[i])
    dp[i+schedules[i][0]] = max(dp[i]+schedules[i][1], dp[i+schedules[i][0]])
print(dp[total_date])
