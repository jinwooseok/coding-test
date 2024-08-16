'''
가로로 주어진 글자들을 세로로 공백없이 읽는 문제이다.
단어는 항상 5개이다.

설계
1. input으로 배열에 넣는다.
2. i,j j,i 탐색으로 세로을 기준으로 출력한다.
일단 대충 설계해두고 끼워맞추는 방식으로 간다.
'''
import sys
T = int(sys.stdin.readline())

for t in range(T):
    arr = [0]*5
    for i in range(5):
        arr[i] = sys.stdin.readline()

    string = ""
    i=0
    while True:
        tmp = ""
        for j in range(5):
            if len(arr[j])-1 < i:
                continue
            tmp += arr[j][i]
        if tmp == "":
            break
        string+=tmp
        i+=1
    print(f"#{t+1} {string}")