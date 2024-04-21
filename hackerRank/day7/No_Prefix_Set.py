#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'noPrefix' function below.
#
# The function accepts STRING_ARRAY words as parameter.


class Node:
    def __init__(self, key):
        self.key = key
        self.child = {}

class Trie:
    def __init__(self):
        self.head = Node(None)

    def insert(self, string):
        current = self.head
        for char in string:
            if char not in current.child:
                current.child[char] = Node(char)
            current = current.child[char]
            if '*' in current.child:
                return string
            
        current.child['*'] = Node('*')
        if len(current.child.keys())>1:
            return string
                    
def noPrefix(words):
    trie = Trie()
    for word in words:
        result = trie.insert(word)
        if result:
            print("BAD SET")
            print(result)
            return
    print("GOOD SET")    
           
if __name__ == '__main__':
    n = int(input().strip())

    words = []

    for _ in range(n):
        words_item = input()
        words.append(words_item)

    noPrefix(words)
