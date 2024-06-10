from itertools import combinations 
def solution(n, count):
    answer = 0
    array = [[0]*(n+1) for i in range(n+1)]
    array[1][1]=1
    for row in range(2,n+1):
        for col in range(1,row+1):
            array[row][col]=(array[row-1][col-1]+array[row-1][col]*(row-1)*2) % 1000000007
    return array[n][count]