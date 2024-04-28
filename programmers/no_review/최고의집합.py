def solution(n, s):
    answer = [None]*n
    if n > s:
        return [-1]
    p=s//n
    m=s%n
    for i in range(n):
        if i<m:
            answer[n-i-1]=p+1
        else:
            answer[n-i-1]=p
    return answer