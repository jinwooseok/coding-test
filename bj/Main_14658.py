'''
L*L 크기의 트램펄린
최대한 많은 별똥별을 튕겨내야함.
N,M 배열 가로 세로
L K : 트램펄린 한변, K는 별똥별의 수
트램펄린의 시작지점을 찾아야함. 배열로는 크기가 너무 커서 안될듯
시작지점이 x,y일때, x<=_<=x+l and y<=_<=y+l
이 시작점을 고르는 방법
100 * 4 * 100

  0 1 2 3 4 5 6 7 8  9 10 11
0     
1
2         O  
3   O 
4               O   
5             O  
6
7       O
8             
9
10
11
12                      O   
'''

import sys
input = sys.stdin.readline
N,M,L,K = map(int,input().split())
arr = []
for i in range(K):
    arr.append(list(map(int,input().split())))

max_num = 0
for i in range(K):
    x = arr[i][0]
    y = arr[i][1]
    for j in range(K):
        trampolines = [0]*4
        for m in range(K):
            if x<=arr[m][0]<=x+L and y<=arr[m][1]<=y+L:
                trampolines[0]+=1
            if x<=arr[m][0]<=x+L and y-L<=arr[m][1]<=y:
                trampolines[1]+=1
            if x-L<=arr[m][0]<=x and y<=arr[m][1]<=y+L:
                trampolines[2]+=1
            if x-L<=arr[m][0]<=x and y-L<=arr[m][1]<=y:
                trampolines[3]+=1
    
        max_num = max(max_num, max(trampolines))
        
for i in range(K):
    for j in range(i+1,K):
        x1 = arr[i][0]
        y1 = arr[i][1]
        x2 = arr[j][0]
        y2 = arr[j][1]
        for k in (x1,x2):
            for l in (y1,y2):
                x=k
                y=l
                trampolines = [0]*4
                for m in range(K):
                    if x<=arr[m][0]<=x+L and y<=arr[m][1]<=y+L:
                        trampolines[0]+=1
                    if x<=arr[m][0]<=x+L and y-L<=arr[m][1]<=y:
                        trampolines[1]+=1
                    if x-L<=arr[m][0]<=x and y<=arr[m][1]<=y+L:
                        trampolines[2]+=1
                    if x-L<=arr[m][0]<=x and y-L<=arr[m][1]<=y:
                        trampolines[3]+=1
                #print(trampolines
                max_num = max(max_num, max(trampolines))
    
print(K-max_num)