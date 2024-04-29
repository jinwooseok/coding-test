from collections import deque
def solution(a):
    answer = 0
    left_min = [10**10]*len(a)
    right_min = [10**10]*len(a)
    left_min[0] = a[0]
    right_min[-1] = a[-1]
    for i in range(1,len(a)):
        left_min[i] = min(left_min[i-1],a[i])
    
    for i in range(len(a)-2,-1,-1):
        right_min[i] = min(right_min[i+1], a[i])
        
    for i in range(len(a)):
        if a[i]>left_min[i] and a[i]>right_min[i]:
            continue
        else:
            answer+=1
    return answer