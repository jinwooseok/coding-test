#코드를 입력하세요.
N = int(input())
arr = [input().split() for i in range(N)]
cctv = [[0]*N for i in range(N)]
teachers = []
students = []
checked = False
def comb(arr, result, idx, depth, r, n, rowterm, colterm):
    global checked
    if checked:
        return
    if depth == r:
        checked = check(result, rowterm, colterm, n)
        return

    if idx >= n*n:
        return
    if arr[idx//n][idx%n] not in ('S', 'T'):
        result[depth] = idx
        comb(arr, result, idx + 1, depth + 1, r, n, rowterm, colterm)
        comb(arr, result, idx + 1, depth, r, n, rowterm, colterm)
    else:
        comb(arr, result, idx + 1, depth, r, n, rowterm, colterm)
    return

#추출한 구간들을 3개로 막을 수 있는지 학인
def check(result, rowterm, colterm, n):
    for row, terms in rowterm.items():
        for term in terms:
            for res in result:
                if row == res//n and term[0] < res%n < term[1]:
                    break
            else:
                return False

    for col, terms in colterm.items():
        for term in terms:
            for res in result:
                if col == res%n and term[0] < res//n < term[1]:
                    break
            else:
                return False
    return True

for i in range(N):
    for j in range(N):
        if arr[i][j] == "T":
            teachers.append((i,j))
        elif arr[i][j] == "S":
            students.append((i,j))
from collections import defaultdict
rowterm = defaultdict(list)
colterm = defaultdict(list)
for s in students:
    for t in teachers:
        if s[0] == t[0]:
            rowterm[s[0]].append((min(s[1], t[1]), max(s[1], t[1])))
        elif s[1] == t[1]:
            colterm[s[1]].append((min(s[0], t[0]), max(s[0], t[0])))

comb(arr, [0]*3, 0, 0, 3, N, rowterm, colterm)

if checked:
    print("YES")
else:
    print("NO")
