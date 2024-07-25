#m칸만큼 파리의 개수를 셈
def kill(arr,r,c,m):
    count = 0
    for i in range(m):
        for j in range(m):
            count+=arr[r+i][c+j]
    return count
T = int(input())
for t in range(1,T+1):
    n, m = map(int, input().split())
    arr = []
    for i in range(n): 
        arr.append(list(map(int, input().split())))
    #처음부터 n-m+1칸까지를 파리채의 시작부분으로 잡고 때리기 시작
    max_num = 0 #계속해서 max_num 변경
    for i in range(n-m+1):
        for j in range(n-m+1):
            num = kill(arr, i, j, m)
            if max_num < num:
                max_num = num
    print(f"#{t} {max_num}")