'''
나누기 -> 음수를 양수로 바꾼 뒤 나눈 몫을 양수로 바꿈
중복순열을 통해 값을 구할 수 있을 것으로 보여짐
'''

#필요한 요소 - arr, total, depth, r
# 0: +, 1:- 2:* 3://
def sum(a,b):
    return a+b
def sub(a,b):
    return a-b
def mul(a,b):
    return a*b
def div(a,b):
    if (a*b<0):#파이썬은 결과 어케 나오는지 몰라서 걍 함
        return -(abs(a)//abs(b))
    else:
        return a//b
#딕셔너리 활용
calFunc = {
    0:sum,
    1:sub,
    2:mul,
    3:div
}
def perm(arr:list, total:int, start:int, depth:int, r:int, max_num:int, min_num:int, restrict:list):
    if depth == r: #끝까지 했을 때
        return max(total, max_num), min(total, min_num)
    if start>=len(arr)-1: #인덱스 초과 시
        return max_num, min_num
    for i in range(4):#중복순열은 i를 이어감. total에 이미 만들어둔 함수를 활용함.
        if restrict[i]>0: #해당 순서의 연산자의 사용횟수가 남았다면?
            restrict[i]-=1 #사용
            max_num, min_num=perm(arr, calFunc[i](total, arr[start+1]), start+1, depth+1, r, max_num, min_num, restrict)
            restrict[i]+=1 #백트래킹 복구
    return max_num, min_num #전달받은 max_num, min_num 리턴

import sys
N = int(sys.stdin.readline())
arr = list(map(int, sys.stdin.readline().split()))
restrict = list(map(int, sys.stdin.readline().split()))

max_num, min_num = perm(arr, arr[0], 0, 0, N-1, -1000000000, 1000000000, restrict)

print(max_num)
print(min_num)