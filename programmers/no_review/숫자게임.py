import heapq
def solution(A, B):
    result = 0
    heapq.heapify(A)
    heapq.heapify(B)
    while B:
        a=heapq.heappop(A)
        b=heapq.heappop(B)
        if b <= a:
            heapq.heappush(A,a)
        else:
            result+=1
    return result