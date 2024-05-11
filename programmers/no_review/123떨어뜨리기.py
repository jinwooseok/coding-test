from collections import defaultdict
def solution(edges, target):
    graph = defaultdict(list)
    conn = {}
    leaf_visited = {}
    for st, ar in edges:
        graph[st]+=[ar]
        if ar not in graph.keys():
            graph[ar]=[]
        conn[st] = 0
        
    for key, values in graph.items():
        if len(values) == 0:
            leaf_visited[key] = []
        graph[key].sort()
        
    root = 1
    count = 0
    while True:
        if count > sum(target):
            return [-1]
        count+=1
        if check(target, leaf_visited):
            break
        while True:
            if len(graph[root]) == 0:
                leaf_visited[root].append(count)
                root = 1
                break
            connection = conn[root]
            conn[root] = (conn[root]+1)%len(graph[root])
            root = graph[root][connection]
        
    return insert(target, leaf_visited, count-1)
#1,2,3의 조합을 vis의 개수만큼 했을 때조합이 가능한지
def check(target,leaf_visited):
    for node, vis in leaf_visited.items():
        n_sum=target[node-1]
        n_count = len(vis)
        if n_count*1 > n_sum or n_sum > n_count*3:
            return False
    return True

def insert(target, visit_log, count):
    result = [0]*count
    for key, values in visit_log.items():
        flag = False
        target[key-1]-=len(values)
        for i in range(len(values)-1, -1, -1):
            if target[key-1]>=2:
                result[values[i]-1] = 3
                target[key-1]-=2
            elif target[key-1]==1:
                result[values[i]-1] = 2
                target[key-1]-=1
            elif target[key-1]==0:
                result[values[i]-1] = 1
    return result