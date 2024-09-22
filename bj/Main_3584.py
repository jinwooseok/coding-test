import sys
from collections import defaultdict, deque
T = int(sys.stdin.readline())
for tc in range(T):
    N = int(sys.stdin.readline())
    
    tree = defaultdict()
    parents = [0]*(N+1)
    levels = [0]*(N+1)
    for i in range(N+1):
        parents[i]=i
    
    for i in range(N-1):
        parent, child=map(int, sys.stdin.readline())
        parents[child] = parent
        tree[parent].append(child)
    
    node1, node2 = map(int, sys.stdin.readline())
    
    #root찾기
    root = -1
    for i in range(N+1):
        if (parents[i]==i):
            root = i
    
    #level 정하기
    dq = deque()
    deque.append(root)
    while deque:
        

        