from collections import defaultdict

class Node:
    def __init__(self, word=None, last=False, children={}):
        self.word = word
        self.last = last
        self.children = children
        self.count = 1
        
class Trie:
    def __init__(self):
        self.root = Node()
    
    def insert(self, string):
        current = self.root
        for i, s in enumerate(string):
            if s in current.children:
                current = current.children[s]
                current.count+=1
            else:
                if i == len(string)-1:
                    current.children[s]=Node(word=s, last=True, children={})
                else:
                    current.children[s]=Node(word=s, last=False, children={})
                current = current.children[s]
        return

    def search(self, string):
        current = self.root
        count = 0
        for s in string:
            count+=1
            if s in current.children:
                current = current.children[s]
                
            if current.count == 1:
                return count
        return count
    
def solution(words):
    answer = 0
    trie = Trie()
    for word in words:
        trie.insert(word)
    for word in words:
        answer+=trie.search(word)
    return answer