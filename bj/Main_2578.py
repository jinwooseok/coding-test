num_map = {}
'''
빙고판에 숫자 삽입
매번 어느 숫자가 어느 위치에 있는지 찻는게 어려움
반대로 숫자 별로 위치를 삽입
'''
for i in range(5):
    row_nums=list(map(int,input().split()))
    for j in range(5):
        num_map[row_nums[j]]=(i,j)

'''
빙고인지 매번 25개의 배열을 순회하는 것보다는 
행과 열, 대각선에서 체크된 숫자의 개수를 세어 기록하는게 빠르다고 판단.
만약 value값이 5가 된 행,열,대각선이 있다면 그 때 리턴하기.
'''
bingo = {'row':{i:0 for i in range(5)}, 
         'col':{i:0 for i in range(5)}, 
         'digree':{i:0 for i in range(2)}}
#빙고인가 체크
def is_bingo(check_map:dict, bingo_cnt): 
    bingo_cnt = 0
    for val in check_map.values():
        for cnt in val.values():
            if cnt==5:
                bingo_cnt+=1
    if bingo_cnt>=3:
        return True
    else:
        return False

nums = []
for i in range(5):
    nums+=list(map(int,input().split()))

bingo_cnt = 0
for i in range(25):
    cur_num=num_map[nums[i]]
    bingo['row'][cur_num[0]]+=1
    bingo['col'][cur_num[1]]+=1

    if cur_num[0]==cur_num[1]:
        bingo['digree'][0]+=1
    
    if cur_num[0]==abs(4-cur_num[1]):
        bingo['digree'][1]+=1
    
    if is_bingo(bingo, cur_num):
        print(i+1)
        break
            