import sys
from itertools import product

sys.setrecursionlimit(10**6)

class Node:
    def __init__(self, key=None):
        self.key = key
        self.child = {}
        self.is_last = False
class Trie:
    def __init__(self):
        self.root = Node()

    def insert(self, string):
        current=self.root

        for word in string:
            if word not in current.child.keys():
                current.child[word] = Node(word)
            current = current.child[word]
        current.is_last = True

    def search(self, string, i, current, data="", visited=None):
        if visited == None:
            visited = set()

        if len(string)==i and current.is_last==True:
            return visited.add(data)
        elif len(string)==i:
            return visited

        #별이면 모든 id를 탐색 key안에 있으면 그 node 탐색, 일치하지 않는다면 제재된 아이디가 아닌것임        
        if string[i] == '*':
            for key,value in current.child.items():
                self.search(string, i+1, value, data+key, visited)
        elif string[i] in current.child.keys():
            self.search(string, i+1, current.child[string[i]], data+string[i], visited)
        return visited

def solution(user_id, banned_id):
    answer = 0
    #user_id로 트라이 구성
    tree=Trie()
    for string in user_id:
        tree.insert(string)
    #banned_id가 될 수 있는 경우의 수 추출
    l = []
    for string in banned_id:
        l.append(tree.search(string, 0, tree.root))

    # 가능한 모든 조합의 경우 생성
    all_combinations = product(*l)

    # 중복을 제거하기 위해 set 사용
    unique_combinations = set()
    for comb in all_combinations:
        if len(set(comb))==len(comb):
            unique_combinations.add(tuple(sorted(comb)))

    return len(unique_combinations)