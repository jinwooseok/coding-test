N = int(input())
arr = list(map(int, input().split()))
dp = [0 for i in range(1001)]
dp[arr[0]]=1
for i in range(1,N):
    maxNum = 0
    for j in range(arr[i]+1, 1001):
        maxNum=max(dp[j],maxNum)
    dp[arr[i]]=max(maxNum+1,dp[arr[i]])
result=0
#print(dp)
for i in range(0,1001):
    result=max(result,dp[i])
print(result)    
# print(max(dp[N-1][0],dp[N-1][1]))
