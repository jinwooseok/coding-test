N = int(input())
sizes = list(map(int, input().split()))
T, P = map(int,input().split())

tCnt = 0
for size in sizes:
    if size%T == 0:
        tCnt += size//T
    else:
        tCnt += size//T+1

print(tCnt)
print(N//P, N%P)