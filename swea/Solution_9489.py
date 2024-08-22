def find_max_len(arr, N, M):
    max_cnt = 0
    for i in range(N):
        cnt = 0
        for j in range(M):
            if (arr[i][j]==1):
                cnt+=1
            else: #1인 구간을 체크. 1이 아니게 될 경우 최대값 찾기 + 0으로 초기화
                max_cnt = max(cnt, max_cnt)
                cnt=0
        max_cnt = max(cnt, max_cnt) #마지막구간까지 1이 이어질 경우를 체크
    for i in range(M):
        cnt = 0
        for j in range(N):
            if (arr[j][i]==1):
                cnt+=1
            else: #1인 구간을 체크. 1이 아니게 될 경우 최대값 찾기 + 0으로 초기화
                max_cnt = max(cnt, max_cnt)
                cnt=0
        max_cnt = max(cnt, max_cnt) #마지막구간까지 1이 이어질 경우를 체크
    return max_cnt
#입력부
T = int(input())
for t in range(1, T+1):
    N, M = map(int, input().split())
    arr = [[0]*M for i in range(N)]
    for i in range(N):
        arr[i] = list(map(int, input().split()))

    #로직 + 출력부
    print(f"#{t} {find_max_len(arr, N, M)}")