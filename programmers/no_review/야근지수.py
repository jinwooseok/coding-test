import heapq
def solution(n, works):
    answer = 0
    maxheap = []
    for work in works:
        heapq.heappush(maxheap,(-work,work))
    while n > 0 and maxheap:
        num=heapq.heappop(maxheap)
        n-=1
        if num[1]-1 < 0:
            heapq.heappush(maxheap,(0,0))
        else:
            heapq.heappush(maxheap,(num[0]+1,num[1]-1))

    for num in maxheap:
        answer+=num[1]**2
    return answer