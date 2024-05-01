from collections import deque
def solution(n):
    answer = 0
    queue=deque()
    queue.append((1,0))
    while queue:
        opened, closed=queue.popleft()
        if opened == n and closed == n:
            answer+=1
        if opened > n or closed > n:
            continue
        
        if opened > closed:
            queue.append((opened+1,closed))
            queue.append((opened,closed+1))
        elif opened == closed:
            queue.append((opened+1, closed))
        
    return answer