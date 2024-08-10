import sys
N=sys.stdin.readline()
arr=list(map(int,sys.stdin.readline().split()))

st = 0
max_cnt = 0

for i in range(0,len(arr)-1):
    if arr[i]>arr[i+1]:
        max_cnt=max(max_cnt,i+1-st)
        st=i+1
max_cnt=max(max_cnt,len(arr)-st)
st = 0
for i in range(0,len(arr)-1):
    if arr[i]<arr[i+1]:
        max_cnt=max(max_cnt,i+1-st)
        st=i+1
max_cnt=max(max_cnt,len(arr)-st)
print(max_cnt)