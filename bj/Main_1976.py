import sys
input = sys.stdin.readline
N = int(input())
M = int(input())
'''
방문이 가능한지 알아보는 것...
짧은지는 중요 x

'''
adjarr = [list(map(int, input().split())) for i in range(N)]

plan = list(map(int, input().split()))

# for i in range(N): #경
#     for j in range(N): #출
#         for k in range(N): #도
#             if j!=k:
#                 if adjarr[j][i]==1 and adjarr[i][k]==1:
#                     adjarr[j][k]=1

# flag = True
# for i in range(M-1):
#     if plan[i]-1 != plan[i+1]-1 and adjarr[plan[i]-1][plan[i+1]-1] == 0:
#         flag = False
#         break

parents = [i for i in range(N)]
def find(parents,x):
    if parents[x] == x:
        return x
    parents[x] = find(parents,parents[x])
    return parents[x]

def union(parents,a,b):
    ap = find(parents,a)
    bp = find(parents,b)
    if ap != bp:
        parents[ap] = bp
        find(parents,a)

for i in range(N):
    for j in range(N):
        if adjarr[i][j] == 1:
            union(parents,i,j)

p = find(parents,plan[0]-1)
flag = True
#print(parents)
for i in range(1,M):
    if find(parents, plan[i]-1) != p:
        flag = False
        break
if flag:
    print("YES")
else:
    print("NO")