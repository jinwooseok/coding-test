from collections import deque
import sys

input = sys.stdin.readline
N, M = map(int, input().split())
children = [0]*(N+1)
parents = [[] for i in range(N+1)]
for i in range(M):
    a, b = map(int, input().split())
    children[b] += 1
    parents[a].append(b)

q = deque()
for i in range(1,N+1):
    if children[i] == 0:
        q.append(i)
level = 0
result = [0]*(N+1)
#print(parents, children, q)
while q:
    level += 1
    size = len(q)
    for i in range(size):
        child = q.popleft()
        result[child] = level
        for parent in parents[child]:
            children[parent] -= 1
            if children[parent] == 0:
                q.append(parent)


print(*result[1:])
