'''
투포인터를 사용해 원소가 k개 이하인지 검사하자
투포인터를 했을 때 끊겼다면 다음꺼를 수용할 수 있을만큼 앞에거를 빼고 계속해본다.
그렇게 하면 최장거리를 구할 수 있을 듯
연속 수열이 끊긴다고 다음것으로 새로시작하면 그 이전의 데이터가 잘 반영되지 않을듯
for문안에 while문이 있지만 cnt의 크기가 업데이트 되면서 start도 계속 커지면서 한번만 순회됨. 그러므로 2*N으로 O(N)일듯함
'''
N, K = map(int, input().split())
arr = list(map(int, input().split()))
nums = [0]*100001 # 수의 개수를 저장할 거임

maxCnt = 0
cnt = 0
for i in range(N):
    nums[arr[i]]+=1
    if nums[arr[i]]>K:
        maxCnt = max(cnt, maxCnt)
        start = i-cnt
        while nums[arr[i]]>K:
            nums[arr[start]]-=1
            cnt-=1
            start+=1
    cnt+=1
print(max(maxCnt,cnt))