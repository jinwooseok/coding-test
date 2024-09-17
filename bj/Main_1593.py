'''
W의 순열이 S에 포함되는 모든 경우의 수
단어 W의 길이는 최대 3000. 순열은 에바인데 된다면 가지치기 개잘하기
문자열 S의 길이도 최대 3000000이다. 그러므로 탐색 너무 오래걸릴듯
200000000
슬라이딩 윈도우 사용.
g길이의 언어들이 w배열에 포함되는지
'''
from collections import defaultdict

def check(wordSet:dict):
    for val in wordSet.values():
        if val != 0:
            return False
    return True


g, s = map(int, input().split())
W = input()
S = input()
wordSet = defaultdict(int)
for i in range(g):
    wordSet[W[i]]+=1

    
result = 0
startWord=list(S[0:g])

for w in startWord:
    if w in wordSet.keys():
        wordSet[w]-=1
if check(wordSet):
    result+=1
for i in range(s-g):
    if S[i] in wordSet.keys():
        wordSet[S[i]] += 1
    if S[i+g] in wordSet.keys():
        wordSet[S[i+g]] -= 1
    if check(wordSet):
        result+=1

print(result)