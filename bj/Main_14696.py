'''
무조건 별이 많은쪽이 이김
별 > 동그라미 > 네모 > 세모 순서
'''
def a_is_winner(cur_result):
    for i in range(3,-1,-1):
        if cur_result[0][i] > cur_result[1][i]:
            return 1 #true(win)
        elif cur_result[0][i] < cur_result[1][i]:
            return 0 #false(lose)
    return -1 #null(draw)

#game수
N = int(input())
#game수*2인*4가지경우
game_set = [[[0]*4 for i in range(2)] for i in range(N)]
#결과채우기
for i in range(N):
    a=list(map(int,input().split()))
    b=list(map(int,input().split()))
    max_cnt=max(a[0],b[0])
    min_cnt=min(a[0],b[0])
    for j in range(1,max_cnt+1):
        if j<=a[0]:
            game_set[i][0][a[j]-1]+=1
        if j<=b[0]:
            game_set[i][1][b[j]-1]+=1

for i in range(N):
    result = a_is_winner(game_set[i])
    if result==1:
        print('A')
    elif result==0:
        print('B')
    else:
        print('D')