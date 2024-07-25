#배열을 회전하는 함수
def rotate(arr):
    new_arr = [[0]*N for i in range(N)]
    for i in range(N):
        for j in range(N):
            new_arr[j][N-i-1] = arr[i][j]
    return new_arr

T = int(input())
for t in range(1, T+1):
    N = int(input())
    arr = []
    for i in range(N): 
        arr.append(list(map(int, input().split())))
        
    result = []
    
    #배열 회전을 3회 진행
    for i in range(3):
        arr = rotate(arr)
        result.append(arr)
    
    #출력
    print(f'#{t}')
    for i in range(N):
        string = ""
        for j in range(3):
            for k in range(N):
                string+=str(result[j][i][k])
            string += " "
        print(string[:-1])