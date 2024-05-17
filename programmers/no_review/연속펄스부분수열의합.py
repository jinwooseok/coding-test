def solution(sequence):
    for m in [1, -1]:
        dp = [[0]*len(sequence) for i in range(2)]
        dp[0][0] = sequence[0]*1
        dp[1][0] = sequence[0]*(-1)
        for j in [0,1]:
            for i in range(1, len(sequence)):
                seq = sequence[i]*((-1)**(i+j))
                if seq>dp[j][i-1]+seq:
                    dp[j][i] = seq
                else:
                    dp[j][i] = dp[j][i-1]+seq

        return max(max(dp[0]), max(dp[1]))