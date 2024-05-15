def solution(target):
    dp = [[0,0] for i in range(100001)]
    for i in range(1,target+1):
        if 1<=i<=20:
            dp[i][0]=1
            dp[i][1]=1
        elif 21<=i<=40:
            if i%2 == 0 or i%3==0:
                dp[i][0]=1
                dp[i][1]=0
            else:
                dp[i][0]=2
                dp[i][1]=2
        elif 41<=i<50:
            if i%3 == 0:
                dp[i][0]=1
                dp[i][1]=0
            else:
                dp[i][0]=2
                dp[i][1]=1
        elif i==50:
            dp[i][0]=1
            dp[i][1]=1
        elif 50<i<=60:
            if i%3==0:
                dp[i][0]=1
                dp[i][1]=0
            else:
                dp[i][0]=2
                dp[i][1]=2
        elif 60<i<=70:
            dp[i][0]=2
            dp[i][1]=2
        elif i>311:
            dp[i][0]=dp[i-60][0]+1
            dp[i][1]=dp[i-60][1]
        else:
            max_num = 1
            for j in range(2,61):
                if dp[i-j][0]+dp[j][0] == dp[i-max_num][0]+dp[max_num][0]:
                    dp[i][0]=dp[i-j][0]+dp[j][0]
                    if dp[i-j][1]+dp[j][1] > dp[i-max_num][1]+dp[max_num][1]:
                        dp[i][1] = dp[i-j][1]+dp[j][1]
                        max_num = j
                    else:
                        dp[i][1] = dp[i-max_num][1]+dp[max_num][1]
                elif dp[i-j][0]+dp[j][0] > dp[i-max_num][0]+dp[max_num][0]:
                    dp[i][0]=dp[i-max_num][0]+dp[max_num][0]
                    dp[i][1]=dp[i-max_num][1]+dp[max_num][1]
                else:
                    dp[i][0]=dp[i-j][0]+dp[j][0]
                    dp[i][1] = dp[i-j][1]+dp[j][1]
                    max_num = j
    return dp[target]