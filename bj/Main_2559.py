'''
이것은 물구나무서서 봐도 누적합을 구하라는 것으로 보여진다.
'''
arrSize, seq = map(int,input().split())
arr = list(map(int,input().split()))

prefixSum = [0]*(arrSize+1)

for i in range(arrSize):
    prefixSum[i+1]=prefixSum[i]+arr[i]
maxSum = -999999999
for i in range(seq,len(prefixSum)):
    maxSum = max(maxSum, prefixSum[i]-prefixSum[i-seq])

print(maxSum)
