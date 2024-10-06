"""
1~N
1~N
1~p개 반전
실제 x층
1~N중에 되는 수 찾기
k자리 수
X를 기준으로 바꿀수있는것고르기
"""

N, K, P, X = map(int,input().split())
dist = [[0,4,3,3,4,3,2,3,1,2],
        [4,0,5,3,2,5,6,1,5,4],
        [3,5,0,2,5,4,3,4,2,3],
        [3,3,2,0,3,2,3,2,2,1],
        [4,2,5,3,0,3,4,3,3,2],
        [3,5,4,2,3,0,1,4,2,1],
        [2,6,3,3,4,1,0,5,1,2],
        [3,1,4,2,3,4,5,0,4,3],
        [1,5,2,2,3,2,1,4,0,1],
        [2,4,3,1,2,1,2,3,1,0]]
# X를 K자리 숫자 배열로 변환
numarr = [int(d) for d in str(X).zfill(K)]

# print(numarr, narr)
#현재 숫자 배열, 현재 인덱스, 반전횟수, 자리수 K, 최대숫자 N, 최대반전횟수 P
def dfs(number, startIndex, cnt):
    #선택
    
    if startIndex == K:
        num = int(''.join(map(str, number)))
        if 1<= num <= N:
            return 1
        else:
            return 0
    #첫번째자리부터 변환할 예정(첫번째자리를 변환->cnt+dist[number[i]][i])
    #어떤 숫자로 변환할건지? 자기자신도 포함.
    cntSum=0
    tmp = number[startIndex]
    for i in range(0,10):
        if cnt+dist[tmp][i]<=P:
            number[startIndex] = i
            cntSum+=dfs(number, startIndex+1, cnt+dist[tmp][i])
    number[startIndex] = tmp
            
    return cntSum

print(dfs(numarr, 0, 0)-1)