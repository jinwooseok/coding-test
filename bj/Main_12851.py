from collections import deque

subin, bro = map(int, input().split())

q = deque()
q.append((subin, 0))
visited = [100000] * 100001
visited[subin] = 0
min_time = 0
cnt = 0
while q:
    size = len(q)
    #print(size)
    for i in range(size):
        curset = q.popleft()
        cur, time = curset[0], curset[1]
        if cur == bro:
            cnt+=1
        for num in (cur*2, cur-1, cur+1):
            if 0 <= num < 100001 and visited[num]>=time:
                visited[num] = time
                q.append((num, time+1))
    if cnt != 0:
        min_time = time
        break
# 10 15 16 17
# 6 12 18 17
print(min_time)
print(cnt)