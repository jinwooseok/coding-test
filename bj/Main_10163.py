import sys
cnt=int(sys.stdin.readline())
paper_cnt=[0]*(cnt+1)
arr=[[0]*1001 for i in range(1001)]
for i in range(cnt):
    x,y,w,h=map(int,sys.stdin.readline().split())
    for j in range(x,x+w):
        arr[j][y:y+h]=[i+1]*h

for i in range(1001):
    for j in range(1001):
        paper_cnt[arr[i][j]]+=1

for count in paper_cnt[1:]:
    print(count,end=" ")
        