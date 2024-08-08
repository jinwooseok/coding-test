def combination(arr,results,result,startIndex, depth):
    if len(result)==depth:
        if sum(result)==100:
            sorted_result = sorted(result)
            results.append(sorted_result)
    else:
        for i in range(startIndex, len(arr)):
            result[depth] = arr[i]
            combination(arr,results,result,i+1,depth+1)
    return results

import sys
arr=[0]*9
for i in range(9):
    arr[i]=int(sys.stdin.readline())
results= combination(arr,[],[0]*7, 0, 0)

for i in results[0]:
    print(i)