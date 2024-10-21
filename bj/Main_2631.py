'''
1 2 3 4 5 6 7

3 7 5 2 6 1 4
3 5 2 6 1 4 7
3 4 5 2 6 1 7

최장증가 수열을 찹으면 나머지의 위치만 적절하게 옮기면 밀리면서 풀림
줄은 증가하는 순서대로 세워야 함.
즉 증가하지 않는 부분들을 앞으로 빼면 완성됨.
그중에서 가장 적게 이동하는 것이 전체에서 증가하는 부분수열을 빼는 것임
'''
N = int(input())
arr = [0]*N
dp = [0]*N
for i in range(N):
    arr[i] = int(input())
dp[0] = 1
for i in range(1,N):
    dp[i] = 1
    for j in range(0,i):
        if arr[j]<arr[i]:
            dp[i] = max(dp[j]+1, dp[i]) 
print(N-max(dp))