'''
힙을 써서 풀어봄
heapq.heapify(list) : list를 힙으로 만들어줌
heapq.heappop(heap) : heap에서 가장 작은 원소를 pop해줌
'''
import heapq
def solution(d, budget):
    answer = 0
    heapq.heapify(d)
    while d:
        consume = heapq.heappop(d)
        
        if budget-consume < 0:
            return answer

        budget -= consume
        answer+=1
        
    return answer