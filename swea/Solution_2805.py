T = int(input())
for t in range(T):
    arr_size = int(input())
    start, end = arr_size//2, arr_size//2
    cnt = 0
    for i in range(arr_size):
        row=input()
        range_str = row[start:end+1]
        for j in range_str:
            cnt+=int(j)
        if i<(arr_size//2):
            start-=1
            end+=1
        else:
            start+=1
            end-=1
    print(f"#{t+1} {cnt}")