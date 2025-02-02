"""
5개의 구로 나눔.
가운데 구 선택이 중요함
시작지점은 랜덤 - 처음부터 끝까지 해보면 될듯
꺾는 것을 매번 해봄. 될때까지
dfs로 하고 한바퀴돌고 뭔점에 도달했을 때 체크
체크방식은 범위에 따라 1,2,3,4로 나누고 범위안에 들면 5로 취급해 더하기
like 디저트카페
400*400*20*20 (매우 넉넉하다는 뜻 20*19보다 훨씬적음 제약조건이 있기 때문)
2개의 길이를 추출하는데 2개 길이의 합은 N보다 작거나 같음

1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
6
100 2 2 2 2 1
 1  3 4 4 4 1
 1  2 3 3 3 1
 1  2 3 3 3 1
 1  2 3 3 3 1
 1  1 1 1 1 100
"""
def check5(x, y, r, c, d1, d2):
    x2 = x + d1 + d2
    y2 = y + d2 - d1
    if y - d1 <= c <= y + d2:
        if x + abs(y - c) <= r <= x2 - abs(y2 - c):
            return True
    return False

def check(arr, x, y, d1, d2):
    gu = [0] * 5  # 각 선거구의 인구 합을 저장할 리스트
    
    for r in range(N):
        for c in range(N):
            # 5번 선거구 체크
            if check5(x, y, r, c, d1, d2):
                gu[4] += arr[r][c]
            # 1번 선거구
            elif r < x + d1 and c <= y and (c < y - d1 or r < x + abs(y - c)):
                gu[0] += arr[r][c]
            # 2번 선거구
            elif r <= x + d2 and c > y and (c > y + d2 or r <= x + abs(y - c)):
                gu[1] += arr[r][c]
            # 3번 선거구
            elif r >= x + d1 and c < y - d1 + d2 and (c < y - d1 or r >= x + d1):
                gu[2] += arr[r][c]
            # 4번 선거구
            elif r > x + d2 and c >= y - d1 + d2 and (c > y + d2 or r > x + d2):
                gu[3] += arr[r][c]
    #print(gu)
    return max(gu) - min(gu)

def comb(arr, r, c):
    minDiff = float('inf')
    for i in range(1, N):
        if c - i < 0:
            break
        for j in range(1, N):
            if r + i + j >= N:
                break
            elif c + j >= N:
                break
            else:
                minDiff = min(minDiff, check(arr, r, c, i, j))
    return minDiff

N = int(input())
arr = [list(map(int, input().split())) for i in range(N)]

minDiff = float('inf')
for i in range(N):
    for j in range(N):
        minDiff = min(minDiff, comb(arr, i, j))

print(minDiff)