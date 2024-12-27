import math
a,b,c = map(int, input().split())
DP = [0]*((int) (math.sqrt(b)+1))
def dfs(num, cnt):
    if cnt<len(DP):
        if DP[cnt] != 0:
            return DP[cnt]
        else:
            DP[cnt]=dfs(num, cnt//2)*dfs(num, cnt-cnt//2)
            return DP[cnt]
    else:
        return dfs(num, cnt//2)*dfs(num, cnt-cnt//2)

DP[1] = a
DP[2] = a*a

print(dfs(a,b)%c)