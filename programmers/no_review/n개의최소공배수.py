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