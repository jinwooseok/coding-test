from collections import defaultdict, deque
def solution(n, edge):
    answer = 0
    graph = defaultdict(list)
    distance = defaultdict(int)
    for _ in edge:
        graph[_[0]].append(_[1])
        graph[_[1]].append(_[0])
    
    #bfs
    queue = deque()
    queue.append((1,1))
    visited = [0]*n
    max_count = 0
    while queue:
        node=queue.popleft()
        if visited[node[1]-1] == 0:
            visited[node[1]-1]=node[0]
            distance[node[0]]+=1
        else:       
            continue
        if max_count < node[0]:
            max_count = node[0]
        for _ in graph[node[1]]:
            queue.append((node[0]+1,_))

    return distance[max_count]