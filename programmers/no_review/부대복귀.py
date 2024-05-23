from collections import defaultdict, deque
def solution(n, roads, sources, destination):
    answer = [0]*len(sources)
    #destination에서 시작해서 sources까지의 최단거리 : 다익스트라 사용하면 될듯?
    graph = defaultdict(list)
    distance = [0]+[float('inf')]*n
    for r1, r2 in roads:
        graph[r1]+=[r2]
        graph[r2]+=[r1]

    queue = deque()
    queue.append([destination,0])
    visited = set()
    while queue:
        cur=queue.popleft()
        if cur[0] in visited:
            continue
        distance[cur[0]]=cur[1]
        visited.add(cur[0])
        for node in graph[cur[0]]:
            queue.append([node,cur[1]+1])

    j=0
    for i in sources:
        if distance[i] != float('inf'):
            answer[j]=distance[i]
        else:
            answer[j]=-1
        j+=1
    return answer