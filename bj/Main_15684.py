'''
N개의 세로선과 M개의 가로선
가로선의 개수를 추가해서 i->i 를 구현해라
부분집합으로  적은것부터 추가.
그냥 냅다 선택 후, 내부의 위치까지 선택
'''
N, M, H = map(int, input().split())
arr = [[0]*N for i in range(M+2)]

for i in range(M):
    a,b=map(int,input().split())
    arr[a][b-1] = 1
    arr[a][b] = 1

print(arr)
#사다리를 놓을 집합 탐색. 0~6. 그리고 사다리 테스트. 매번 자리에 놓아봄.
flag = 0
def comb(visited, depth, idx, R):
    if flag != 0:
        return
    if idx>=N:
        simulation()
        return
    if depth>R:
        return
    comb(visited, depth, idx+1, R)
    comb(visited|(1<<idx), depth+1, idx+1, R)
    
def simulation(visited):
    for i in range(N):
        if visited & 1<<i:
            for j in range(M):
                arr[j][i-1]=1
                arr[j][i]=1



#사다리를 놓은 가로번호 선택
print(H)
comb(0, 0, 0, H)
