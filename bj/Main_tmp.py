n=int(input())
list1=list(map(int,input().split()))

list2=list(map(int,input().split()))

list1.sort()

list2.sort()

sumN=0

for i in range(len(list1)):

    sumN+=list1[i]*list2[n-i-1]

    

print(sumN)
