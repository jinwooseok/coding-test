#후보지를 찾는다. 배열을 돌아다니며 8방탐색
MOVE = [(-1,0),(1,0),(0,-1),(0,1),(-1,-1),(-1,1),(1,-1),(1,1)]
def find_candidate(arr, N, M):
    candidate_cnt = 0
    for cur_r in range(N):
        for cur_c in range(M):
            cnt = 0
            for m_r, m_c in MOVE:
                next_r = cur_r+m_r
                next_c = cur_c+m_c
                
                if next_r<0 or next_c<0 or next_r>=N or next_c>=M:
                    continue
                if arr[next_r][next_c]<arr[cur_r][cur_c]:
                    cnt+=1
                    
            if cnt>= 4:
                candidate_cnt+=1
    return candidate_cnt
#입력
T = int(input())
for t in range(1,T+1):
    N, M = map(int, input().split())
    arr = [[0]*M for i in range(N)]
    for i in range(N):
        arr[i] = list(map(int, input().split()))
    print(f"#{t} {find_candidate(arr, N, M)}")
