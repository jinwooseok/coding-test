'''
두 직사각형의 관계를 구해라
1. 겹치는 부분이 직사각형인 경우
2. 겹치는 부분이 선분인 경우
3. 겹치는 부분이 점인 경우
4. 겹치지 않는 경우
최적의 시간을 위해 각 케이스를 만족하는 경우를 준비했다.

면 : 직사각형의 x축 y축 둘 중 하나를 다른 직사각형이 관통하는 경우, 완전히 같은 경우
선 : 면에 포함되지 않지만 x,y축중 하나가 같고 그 길이가 0을 넘는 경우
점 : 선에 포함되지 않고 만나는 지점이 있는 경우
x : 그 외의 경우

입력값
1. 직사각형 1의 x1, y1, x2, y2, 직사각형 2의 x3, y3, x4, y4

'''
#풀이 1
import sys
string = ""
for i in range(4):
    x1, y1, x2, y2, x3, y3, x4, y4 = map(int,sys.stdin.readline().split())
    #겹침x
    if x1 > x4 or x2 < x3 or y1 > y4 or y2 < y3:
        string+='d\n'  # 겹치지 않음
    #면 : 완전히 동일한 경우
    elif x1==x3 and y1==y3 and x2==x4 and y2==y4:
        string+='a\n'
    #면 : 점 하나 이상을 포함하는 경우 (1직사각형의 아무 한점이 포함되는 경우) or (2직사각형의 아무 점이나 포함되는 경우)
    elif (x3<x1<x4 and y3<y1<y4) or (x3<x2<x4 and y3<y2<y4) or (x3<x2<x4 and y3<y1<y4) or (x3<x1<x4 and y3<y2<y4) or (x1<x3<x2 and y1<y3<y2) or (x1<x4<x2 and y1<y4<y2) or (x1<x3<x2 and y1<y4<y2) or (x1<x4<x2 and y1<y3<y2):
        string+='a\n'
    #면 : 내부에 점을 포함하지 않지만 면을 포함하는 경우
    elif ((x1<=x3 and x2>=x4) and (y3<=y1<y4 or y3<y2<=y4)) or ((x1>=x3 and x2<=x4) and (y1<=y3<y2 or y1<y4<=y2)):
        string+='a\n'
    #선 : 한 축은 같고 나머지 한축이 포함되는 경우. x가 같은 축이고 y가 다른 직사각형의 위에 있다면 그것은 선분.
    elif (x1 == x4 or x2 == x3) and (y1 < y4 and y3 < y2):
        string+='b\n'
    elif (y1 == y4 or y2 == y3) and (x1 < x4 and x3 < x2):
        string+='b\n'
    #점 : 한점에서 만나는 경우. 한점이 아닐수도 있지만 다른 경우의 수는 선분과 면에서 처리하여 상관없음
    elif (x1==x4 and y2==y3) or (x1==x4 and y1==y4) or (x2==x3 and y2==y3) or (x2==x3 and y1==y4):
        string+='c\n'
        
print(string.strip())

## 풀이 2
# import sys
# for i in range(4):
#     x1, y1, x2, y2, x3, y3, x4, y4 = map(int,sys.stdin.readline().split())
#     dots = [[x1,y1],[x2,y2],[x3,y3],[x4,y4]]

#     #면 : 완전히 동일한 경우
#     if dots[0][0]==dots[2][0] and dots[0][1]==dots[2][1] and dots[1][0]==dots[3][0] and dots[1][1]==dots[3][1]:
#         print('a')
#     #면 : 점 하나 이상을 포함하는 경우 (1직사각형의 아무 한점이 포함되는 경우) or (2직사각형의 아무 점이나 포함되는 경우)
#     elif (x3<x1<x4 and y3<y1<y4) or (x3<x2<x4 and y3<y2<y4) or (x3<x2<x4 and y3<y1<y4) or (x3<x1<x4 and y3<y2<y4) or (x1<x3<x2 and y1<y3<y2) or (x1<x4<x2 and y1<y4<y2) or (x1<x3<x2 and y1<y4<y2) or (x1<x4<x2 and y1<y3<y2):
#         print('a')
#     #면 : 점이 안에 없지만 선끼리 관통하는 경우
#     elif ((x3<=x1<x4 or x3<x2<=x4) and (y1<y4<=y2 or y1<=y3<y2)) or ((x1<=x3<x2 or x1<x4<=x2) and (y3<y2<=y4 or y3<=y1<y4)):
#         print('a')
#     #선 : 한 축은 같고 나머지 한축이 포함되는 경우. x가 같은 축이고 y가 다른 직사각형의 위에 있다면 그것은 선분. 면이 되는 경우도 있지만 그 경우는 이미 위에서 처리했기 때문에 패스
#     elif ((x1==x4 or x2==x3) and (y3<=y1<y4 or y3<y2<=y4 or y1<=y3<y2 or y1<y4<=y2)) or ((y1==y4 or y2==y3) and (x3<=x1<x4 or x3<x2<=x4 or x1<=x3<x2 or x1<x4<=x2)):
#         print('b')
#     #점 : 한점에서 만나는 경우. 한점이 아닐수도 있지만 다른 경우의 수는 선분과 면에서 처리하여 상관없음
#     elif (x1==x4 and y2==y3) or (x1==x4 and y1==y4) or (x2==x3 and y2==y3) or (x2==x3 and y1==y4):
#         print('c')
#     else: #위의 세가지 경우 이외에는 무조건 만나지 않음
#         print('d')