'''
n장 4<= n <=10
n장 중 k 장 선택
만들수있는 정수의 개수
중복순열 확정
가지치기. 
'''
N = int(input())
k = int(input())
arr = [0]*N
for i in range(N):
    arr[i]=input()
hashSet=set()
def perm(arr,result,visited,depth,k):
    if (depth==k):
        hashSet.add(''.join(result))
        return
    
    for i in range(0,N):
        #print(visited,i)
        if visited[i] is False:
            result[depth]=arr[i]
            visited[i] = True
            perm(arr, result, visited, depth+1, k)
            visited[i] = False


perm(arr, [0]*k, [False]*N, 0, k)

print(len(hashSet))