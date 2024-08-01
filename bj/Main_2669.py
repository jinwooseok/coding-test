arr = [[0]*101 for i in range(101)]
answer = 0
for i in range(4):
    li = list(map(int, input().split()))
    for i in range(li[0],li[2]):
        for j in range(li[1],li[3]):
            arr[i][j]+=1

for i in range(101):
    for j in range(101):
        if arr[i][j] != 0:
            answer+=1
print(answer)

