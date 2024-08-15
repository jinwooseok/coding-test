'''
배열 주변을 돌면서 여러 지점 사이에 최단 거리를 구하는 문제
입력걊
1. 블록의 가로, 세로 길이 (width, height)
2. 상점의 개수 (shopCnt)
3. 한줄마다 상점의 위치 (블록기준 동서남북 loc, 거리 dist)

로직
서남쪽을 0,0. 동북쪽을 width, height 으로 해서 좌표에 표현한 후
0을 중심으로 할때 거리 = 동근이의x + 동근이의 y + 상점의x+상점의 y
width,height를 중심으로 할때 거리 = width-동근x+height-동근y + width-상점x+height-상점y
이렇게 하면 반대방향과 정방향의 거리를 구할 수 있다.
이것에 max를 하기!
좀 더 생각해보니 좌표에 표현할 필요가 없어보인다. 입력하면서 즉시 각 지점간 거리를 구하자.

추가) 기존 로직엔 하나 누락되어 있었다. 0이나 끝부분을 거치지 않는 경우도 존재하는 것이다.
바로 동근과 상점이 남동쪽에 함께 위치하거나 서북쪽에 함께 위치하는 경우
당연히 바로 가는게 더 빠르다.
'''
#정보를 받아 거리를 저장하는 함수. 남북은 왼쪽이 dist 기준이고 동서는 위쪽이 dist기준임을 명심하자.
#arr[0] : 0을 기준으로 거리. arr[1] : 1을 기준으로 거리
def inputLoc(arr, loc, dist, width, height):
    if loc == 1:
        arr[0] = dist + height
        arr[1] = width-dist
        arr[2] = loc
        arr[3] = (dist, height)
    elif loc == 2:
        arr[0] = dist
        arr[1] = width-dist+height
        arr[2] = loc
        arr[3] = (dist, 0)
    elif loc == 3:
        arr[0] = height-dist
        arr[1] = dist+width
        arr[2] = loc
        arr[3] = (0, height-dist)
    elif loc == 4:
        arr[0] = height-dist+width
        arr[1] = dist
        arr[2] = loc
        arr[3] = (width, height-dist)
import sys
width, height = map(int,sys.stdin.readline().split())
shopCnt = int(input())
shops = [[0,0,0,(0,0)] for i in range(shopCnt)]
donggeun = [0,0,0,(0,0)]
for i in range(shopCnt):
    loc, dist = map(int,sys.stdin.readline().split())
    inputLoc(shops[i], loc, dist, width, height)
    
loc, dist = map(int,sys.stdin.readline().split())
inputLoc(donggeun, loc, dist, width, height)

#이제부터 상점을 하나하나 돌면서 0까지의 거리합이 최소인지 오른쪽 위부분까지의 거리합이 최소인지 판단해 더하기
maxSum = 0
for i in range(shopCnt):
    #같은 부분에 있는 경우
    if (donggeun[2] in [2,4]) and (shops[i][2] in [2,4]):
        maxSum+=abs(donggeun[3][0]-shops[i][3][0])+abs(donggeun[3][1]-shops[i][3][1])
    elif (donggeun[2] in [1,3]) and (shops[i][2] in [1,3]):
        maxSum+=abs(donggeun[3][0]-shops[i][3][0])+abs(donggeun[3][1]-shops[i][3][1])
    else:
        maxSum+=min(shops[i][0]+donggeun[0],shops[i][1]+donggeun[1])
    
print(maxSum)