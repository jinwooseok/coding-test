cnt=0
visited=[[False]*100 for i in range(100)]
N = int(input())
black = []
for i in range(N):
    black.append(map(int,input().split()))
for n,m in black:
    for i in range(n,n+10):
        for j in range(m,m+10):
            visited[i][j]=True
        
for i in range(100):
    for j in range(100):
        if visited[i][j]:
            cnt+=1
print(cnt)