def solution(arr):
    answer = -1
    nums = [0]*(len(arr)//2+1)
    for i in range(0,len(arr),2):
        nums[i//2] = int(arr[i])
    
    N = len(nums)
    max_dp = [[0]*N for i in range(N)]
    min_dp = [[0]*N for i in range(N)]
    for diag in range(N):
        for i in range(N - diag):
            j = i + diag
            if diag == 0:
                max_dp[i][j] = nums[i]
                min_dp[i][j] = nums[i]
            else:
                max_val = float('-inf')
                min_val = float('inf')
                for k in range(i, j):
                    if arr[2 * k + 1] == '+':
                        max_val = max(max_val, max_dp[i][k] + max_dp[k + 1][j])
                        min_val = min(min_val, min_dp[i][k] + min_dp[k + 1][j])
                    else:
                        max_val = max(max_val, max_dp[i][k] - min_dp[k + 1][j])
                        min_val = min(min_val, min_dp[i][k] - max_dp[k + 1][j])
                max_dp[i][j] = max_val
                min_dp[i][j] = min_val

    return max_dp[0][-1]
'''
  1  3  5   8
1 1 -2  3   1
3    3  8   0
5       5  -3
8           8

3번째부터는 그 전, 그 후 중 큰 것을 삽입
'''
