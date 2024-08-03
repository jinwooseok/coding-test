N,M=map(int, input().split())
cut_cnt=int(input())

row = [0]
col = [0]
for i in range(cut_cnt):
    ex, num = map(int, input().split()) 
    if ex==0:
        row.append(num)
    elif ex==1:
        col.append(num)
'''
1. 행을 자르는 배열, 열을 자르는 배열 각각 생성
2. 0과 최대길이가 추가된 배열을 생성하고 정렬함.
3. 생성된 배열 : [0(시작점),자를 위치들,N(최종점)]
'''
row.sort()
col.sort()
row.append(M)
col.append(N)

max_size = 0
'''
1.각각 (현재 커팅지점)-(이전 커팅지점)으로 행과 열을 곱하기
2.0과 N을 추가했기 때문에 자연스럽게 for문 진행 가능
3.최대 너비 추출
'''
for i in range(1, len(row)):
    row_size = row[i]-row[i-1]
    for j in range(1, len(col)):
        col_size = col[j]-col[j-1]
        max_size=max(max_size,row_size*col_size)
print(max_size)