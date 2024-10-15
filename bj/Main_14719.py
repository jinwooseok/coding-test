'''
튀어나온 부분 찾기.
첫부분과 끝부분 처리
'''
H, W = map(int, input().split())
arr = list(map(int, input().split()))

cnt = 0
for i in range(H):
    min_idx = W+1
    max_idx = 0
    for j in range(W):
        if arr[j] > i:
            min_idx = min(min_idx, j)
            max_idx = max(max_idx, j)

    for j in range(min_idx, max_idx+1):
        if arr[j] <= i:
            cnt += 1

print(cnt)