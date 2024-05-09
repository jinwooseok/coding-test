def solution(n, costs):
    answer = 0
    #최소신장트리
    costs.sort(key= lambda x:x[2])
    parents = [None]*n
    for cost in costs:
        parents, possible = union(parents, cost[0], cost[1])
        if possible is True:
            answer+=cost[2]
    return answer

def union(parents, a, b):
    a_parent=find(parents,a)
    b_parent=find(parents,b)
    if a_parent == b_parent:
        return parents, False
    if a_parent < b_parent:
        parents[b_parent] = a_parent
    else:
        parents[a_parent] = b_parent
    return parents, True

def find(parents, x):
    if parents[x] is None:
        return x
    return find(parents, parents[x])