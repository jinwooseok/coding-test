from collections import deque

subin, bro = map(int, input().split())

q = deque()
q.append((subin, 0))
visited = [False] * 100001
visited[subin] = True
while q:
    curset = q.popleft()
    cur, cnt = curset[0], curset[1]
    if cur == bro:
        print(cnt)
        break
    if 0 <= cur*2 < 100001 and not visited[cur*2]:
        visited[cur*2] = True
        q.append((cur*2, cnt+1))
    if 0 <= cur+1 < 100001 and not visited[cur+1]:
        visited[cur+1] = True
        q.append((cur+1, cnt+1))
    if 0 <= cur-1 < 100001 and not visited[cur-1]:
        visited[cur-1] = True
        q.append((cur-1, cnt+1))
