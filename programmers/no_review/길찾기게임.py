from collections import defaultdict, deque
import heapq
import sys
sys.setrecursionlimit(10**6)
class Node:
    def __init__(self, key, data, left=None, right=None):
        self.key = key
        self.data = data
        self.left = left
        self.right = right
        
class Tree:
    def __init__(self, root=None):
        self.root = root
        
    def insert(self, key, data): 
        node = Node(key, data) # 삽입할 노드 생성
        parent = None
        current = self.root
        
        while current: 
            parent = current
            if node.data[0] < current.data[0]:
                current = current.left
            else: 
                current = current.right
        
        if parent is None: # original tree가 비어있던 경우
            self.root = node
        elif node.data[0] < parent.data[0]:
            parent.left = node
        else:
            parent.right = node
            
    def preorder(self, node, visited):
        if node is not None:
            visited.append(node.key)
            self.preorder(node.left, visited)
            self.preorder(node.right, visited)
        return visited

    def postorder(self, node, visited):
        if node is not None:
            self.postorder(node.left, visited)
            self.postorder(node.right, visited)
            visited.append(node.key)
        return visited

def solution(nodeinfo):
    answer = [[]]
    level_order = []
    for i, node in enumerate(nodeinfo):
        heapq.heappush(level_order,(-node[1], (i+1,node)))
    
    tree=Tree()        

    while level_order:
        next_data = heapq.heappop(level_order)
        tree.insert(next_data[1][0], next_data[1][1])
        
    print(tree.preorder(tree.root,[]))
                  
    #트리를 형성하는게 최우선 목표
    #그 뒤로 전위 순회 후위순회는 쉬울것으로 보임
    return [tree.preorder(tree.root,[]), tree.postorder(tree.root,[])]