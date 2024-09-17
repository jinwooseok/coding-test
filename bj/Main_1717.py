'''
두원소가 같은 집합에 포함
'''
import sys
n,m = map(int, sys.stdin.readline().split())
parents = [i for i in range(n+1)]
def union(parents,a, b):
    parentA = find(parents, a)
    parentB = find(parents, b)
    if parentA == parentB:
        return
    else:
        parents[parentB] = parentA
        return
    
def find(parents, x):
    if parents[x] == x:
        return x
    parents[x] = find(parents, parents[x])
    return parents[x]

for i in range(m):
    command, a, b = map(int, sys.stdin.readline().split())
    if command == 0:
        union(parents, a, b)
    else:
        if (find(parents, a)==find(parents, b)):
            print('YES')
        else:
            print('NO') 
    #print(parents)  