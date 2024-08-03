N,M=map(int, input().split())
cut_cnt=int(input())

row = [0]
col = [0]
for i in range(cut_cnt):
    ex, num = map(int, input().split()) 
    if ex==0:
        row.append(num)
    elif ex==1:
        col.append(num)
row.sort()
col.sort()
row.append(M)
col.append(N)

max_size = 0
for i in range(1, len(row)):
    row_size = row[i]-row[i-1]
    for j in range(1, len(col)):
        col_size = col[j]-col[j-1]
        max_size=max(max_size,row_size*col_size)
print(max_size)