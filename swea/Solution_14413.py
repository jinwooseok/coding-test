'''
문제
# : 격자판을 검은색으로 칠함
? : 검은색 흰색 자율
. : 흰색으로 칠함

설계
1. ##, ..인 경우 무조건 불가능하므로 False 리턴
2. 현재위치에 ?이 있을 경우 이 ?는 항상 최선의 해로 바꾼다. (예시:이전문자가 .일경우 무조건 #이 되도록)
  - 왜냐하면 ?는 . 혹은 #으로 밖에 못 변하고, False가 안뜨기 위해서는 무조건 둘중하나를 골라야한다. 그리디 알고리즘으로 진행했을때 ..이나 ##이 뜨게 된다면 이것은 어차피 못바꾸는 것이다.
4. 예외 케이스: 시작지점에 ??인 경우 체크가 안되는데 이 경우 항상 true가 되기 때문에 pass

'''

def is_possible(arr, N, M):
    MOVE = [(0,1),(1,0)]
    for i in range(N):
        for j in range(M):
            #둘 다 .인 경우 false
            for move_r, move_c in MOVE:
                if i+move_r<0 or j+move_c<0 or i+move_r>=N or j+move_c>=M:
                    continue
                if arr[i][j] == '.': 
                    if arr[i+move_r][j+move_c] == '.':
                        return False
                    elif arr[i+move_r][j+move_c] == '?':
                        arr[i+move_r][j+move_c] = '#'
                elif arr[i][j] == '#': 
                    if arr[i+move_r][j+move_c] == '#':
                        return False
                    elif arr[i+move_r][j+move_c] == '?':
                        arr[i+move_r][j+move_c] = '.'
                elif arr[i][j]=='?': 
                    if arr[i+move_r][j+move_c] == '.':
                        arr[i][j]='#'
                    elif arr[i+move_r][j+move_c]=='#':
                        arr[i][j]='.'
                        
    return True
                
T = int(input())
for t in range(1,T+1):
    N, M = map(int, input().split())
    result = False
    arr = [[0]*M for i in range(N)]
    for i in range(N):
        arr[i] = list(input())
    
    if is_possible(arr, N, M) is False:
        print(f"#{t} impossible")
    else:
        print(f"#{t} possible")         
                        
    