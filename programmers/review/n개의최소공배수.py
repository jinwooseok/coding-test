'''
문제: n개의 수들이 담긴 배열 arr이 주어질 때, 이 수들의 최소공배수를 반환하시오.
해결 과정
1. 주어진 배열을 오름차순으로 정렬한다.
2. 가장 큰 수의 배수를 구하면서, 나머지 수들로 나누어 떨어지는지 확인한다.
    - 공배수가 되기 위해서는 모든 수들로 나누어 떨어져야 한다. 그러므로 가장 큰 수의 배수를 구하면서 확인하는 것이 가장 효율적이다.

IF 나머지 수들로 나누어 떨어진다. THEN 배수를 반환한다.
ELSE 가장 큰 수의 배수를 증가시키며 다시 확인한다.
'''
def solution(arr):
    arr.sort()
    mul=1
    while True:
        for i, num in enumerate(arr[-2::-1]):
            if (arr[-1]*mul) % num != 0:
                mul+=1
                break
                
            if i==len(arr)-2:
                return arr[-1]*mul