from itertools import combinations

T = int(input())
for i in range(T):
    N, min_num = map(int, input().split())
    arr = list(map(int,input().split()))
    min_count = float('inf')
    for j in range(1, N+1):
        for subset in combinations(arr, j):
            count = sum(subset)
            if  count >= min_num and min_count > count:
                min_count = count 
    print(f"#{i+1} {min_count-min_num}")
    