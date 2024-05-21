from collections import defaultdict, deque
import sys
sys.setrecursionlimit(10**6)
def solution(a, edges):
    #가중치를 0. 두점을 한쪽은 1증가 한쪽은 1감소
    #-1 or 최소횟수
    #가중치 만들고 상위 노드로 올려가며 확인
    #단 root가 어딘지 알수가 없다..0으로 root 고정
    parents = [-1]*len(a)
    parents[0]=0
    dic = defaultdict(list)
    for e1, e2 in edges:
        dic[e1].append(e2)
        dic[e2].append(e1)
    queue = deque()
    queue.append(0)
    while queue:
        cur=queue.popleft()
        for i in dic[cur]:
            if parents[i] == -1:
                parents[i] = cur
                queue.append(i)
            
    tree={i:{'w':a[i], 'children':[], 'count':0} for i in range(len(a))}
    for i in range(1,len(a)):
        tree[parents[i]]['children'].append(i)
    
    dfs(tree, 0)
    if tree[0]['w']==0:
        return tree[0]['count']
    else:
        return -1

def dfs(tree, node):
    for i in tree[node]['children']:
        result=dfs(tree,i)
        tree[node]['w']+=result
        tree[node]['count']+=abs(result)+tree[i]['count']
    return tree[node]['w']