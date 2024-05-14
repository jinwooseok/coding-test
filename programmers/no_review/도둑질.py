def solution(money):
    answer = 0
    dp = [0]*len(money)
    dp[0] = money[0]
    dp[1] = money[1]
    for i in range(2,len(money)-1):
        if i == 2:
            dp[i]=dp[0]+money[i]
        else:
            dp[i] = money[i]+max(dp[i-2], dp[i-3])
    
    dp2 = [0]*len(money)
    dp2[-1] = money[-1]
    dp2[-2] = money[-2]        
    for i in range(len(money)-3, 0, -1):
        if i == len(money)-3:
            dp2[i]=dp2[len(money)-1]+money[i]
        else:
            dp2[i] = money[i]+max(dp2[i+2], dp2[i+3])

    return max(dp[-3:]+dp2[:3])

#2개인 경우 1 or 2
#3개인 경우 1,3 or 2
#4개인 경우 1,3 or 2,4
#5개인 경우 1,3,5 or 1,4 or 2,4 or 2,5
#6개인 경우 1,3,5 or or 2,4,6 or 2,5 3차이가 한계이다. 4차이 나는 곳부터는 2차이 나는 곳과 중복된다.
