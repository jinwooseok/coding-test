'''
유클리드 호제법
'''
def solution(n, m):
    a, b = min(n,m), max(n,m)
    
    while True:
        if a%b == 0:
            return [b, max(n,m)*min(n,m)/b]
        a, b = b, a%b