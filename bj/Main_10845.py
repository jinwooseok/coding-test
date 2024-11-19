from collections import deque

def run(command, q, x = None):
    if command == "push":
        q.append(x)
    elif command == "pop":
        if len(q) == 0:
            print(-1)
        else:
            print(q.popleft())
    elif command == "size":
        print(len(q))
    elif command == "empty":
        if len(q) == 0:  
            print(1)
        else:
            print(0)
    elif command == "front":
        if len(q) == 0:
            print(-1)
        else:
            print(q[0])
    elif command == "back":
        if len(q) == 0:
            print(-1)
        else:
            print(q[-1])
import sys
input = sys.stdin.readline
N = int(input())
q = deque([])
for i in range(N):
    l = input().split()
    if len(l) == 1:
        run(l[0], q)
    else:
        run(l[0], q, l[1])