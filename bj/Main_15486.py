N = int(input())
arr = [list(map(int,input().split())) for i in range(N)]

dp = [0]*N
for i in range(N):
    num = i
    while num<N:
        if num+arr[num][0]-1<N:
            dp[num+arr[num][0]-1]=arr[num][1]
        num += arr[num][0]
    
print(dp)