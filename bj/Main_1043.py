#코드를 입력하세요.
'''
진실 vs 과장
되도록이면 과장해서 말함
몇몇 사람들은 이야기의 진실을 알고 있음
구라치는걸 아는 사람이 있으면 진싦나 말해야함
유니온파인드가 적합할듯. 집합처럼 진실을 아는사람이 파티에 껴있으면 불어나기 때문
'''
def find(parents, x):
    if parents[x] == x:
        return x
    parents[x] = find(parents, parents[x])
    return parents[x]

def union(parents, known, a, b):
    parentA = find(parents, a)
    parentB = find(parents, b)
    if parentA != parentB:
        parents[parentB] = parentA

N, M = map(int, input().split())

known = set(list(map(int, input().split()))[1:])
parties = []
for i in range(M):
    parties.append(list(map(int, input().split()))[1:])

parents = [i for i in range(N+1)]

for i in range(M):
    for j in range(len(parties[i])-1):
        union(parents, known, parties[i][j], parties[i][j+1])
for i in range(N+1):
    find(parents, i)
#진실분류
result = [False]*(N+1)
for i in known:
    num = parents[i]
    for j in range(N+1):
        if parents[j] == num:
            result[j] = True
#print(parents, result)
cnt = 0
for i in range(M):
    if not result[parties[i][0]]:
        cnt+=1

print(cnt)