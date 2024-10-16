from collections import deque
string = deque(list(input()))
target = list(input())
arr = []
start = 0
while string:
    arr.append(string.popleft())    
    if arr[len(arr)-len(target):] == target:
        for j in range(len(target)):
            arr.pop()

    
if len(arr) == 0:
    print("FRULA")
else:
    print("".join(arr))
    