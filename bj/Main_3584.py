import sys
from collections import defaultdict, deque
T = int(sys.stdin.readline())
for tc in range(T):
    N = int(sys.stdin.readline())
    
    tree = defaultdict(list)
    parents = [0]*(N+1)
    levels = [0]*(N+1)
    
    for i in range(N+1):
        parents[i]=i
    
    for i in range(N-1):
        parent, child=map(int, sys.stdin.readline().split())
        parents[child] = parent
        tree[parent].append(child)
    
    node1, node2 = map(int, sys.stdin.readline().split())
    
    #root찾기
    root = -1
    for i in range(1,N+1):
        if (parents[i]==i):
            root = i
    
    #level 정하기
    dq = deque()
    dq.append(root)
    levels[root] = 0
    while dq:
        node=dq.popleft()
        for next_node in tree[node]:
            levels[next_node]=levels[node]+1
            dq.append(next_node)
    
    if levels[node1]>levels[node2]:
        while levels[node1]!=levels[node2]:
            node1=parents[node1]
    elif levels[node2]>levels[node1]:
        while levels[node1]!=levels[node2]:
            node2=parents[node2]
    
    while node1 != node2:
        node1 = parents[node1]
        node2 = parents[node2]
    
    print(node1)