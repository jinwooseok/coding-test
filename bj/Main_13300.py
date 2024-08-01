N, k = map(int,input().split())
#학년,[남,여] 숫자합
arr = [[0,0] for i in range(6)]
for i in range(N):
    s, y =map(int,input().split())
    arr[y-1][s]+=1

answer = 0
for i in range(6):
    for j in range(2):
        if arr[i][j]==0:
            continue
        if arr[i][j]%k==0:
            answer+=arr[i][j]//k
        else:
            answer+=arr[i][j]//k+1
print(answer)            
        