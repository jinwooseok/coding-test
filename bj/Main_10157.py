'''
C * R 인데 방향이 맨 아래가 1임. 
즉, 인덱스가 1부터 시작하는것과 행열구조가 아닌 XY구조인것을 신경쓰기
1,1 위치부터 시계방향으로 이동 > 달팽이 숫자인데 시작점이 다르게 구성하기!
대기번호는 K가 100000000번까지 주어진다. 즉, 순회를 한번 이상 하면 안됨
그리고 달팽이 숫자와는 달리 해당위치가 어딘지만 알면됨. 즉, 한칸한칸 돌아다닐 필요가 없음
한바퀴 돌 때 숫자 : c,r에서 (c-1)*2+(r-1)*2 
두바퀴 돌 때 숫자 : c-2,r-2에서 ((c-2)-1)*2+((c-2)-1)*2
바퀴가 어느 바퀴인지 알았다면?
해당 바퀴의 시작점에서부터 위치 탐색
n바퀴에서 시작점 : n,n지점.
시작점으로부터 거리 체크. 시작점으로부터 거리가 k일 경우:
if k<=r-?:
n,n+k
if k<=r-?+c-?:
n,n+r
if k<=r-?+c-?+r-?



1. 배열의 크기 (C, R)
2. 대기번호 K

설계
1. 입력값 받기
2. 만약 배열의 크기보다 대기번호가 클 경우 0을 리턴
3. 몇번째 바퀴에 있는지 체크할 로직 구현
    - 필요 요소: width,height,k
4. 그 바퀴 내에서 세부적인 위치를 확인할 로직 구현 
    - 필요 요소: n 바퀴째의 width: width-n, n바퀴째의 height: height-n, n바퀴째의 시작점:n,n
'''
#몇번째 바퀴에 있는지 체크
def searchN(width, height, K, n, cnt): 
    alpha=(width-2*(n-1)-1)*2+(height-2*(n-1)-1)*2
    if (cnt+alpha>=K):
        return n, cnt+1
    return searchN(width, height, K, n+1, cnt+alpha)       

def searchLoc(nWidth, nHeight, nStart, n, K):
    term = K-nStart #시작점으로부터 간격
    if term<=nHeight-1:
        return [n,n+term]
    elif term<=nHeight+nWidth-2:
        return [n+(term-(nHeight-1)), n+nHeight-1]
    elif term<=nHeight*2+nWidth-3:
        return [n+nWidth-1,n+(nHeight-1)-(term-(nHeight-1)-(nWidth-1))]       
    else:
        return [n+(nWidth-1)-(term-(nHeight-1)-(nWidth-1)-(nHeight-1)),n]

import sys
width, height = map(int, sys.stdin.readline().split())
K = int(sys.stdin.readline())

#1. k>배열크기 : 0. 아닐경우 로직진행
if K > width*height:
    print(0)
else:
    n, startNum = searchN(width, height, K, 1, 0)
    result=searchLoc(width-2*(n-1), height-2*(n-1), startNum, n, K)
    print(result[0],result[1])