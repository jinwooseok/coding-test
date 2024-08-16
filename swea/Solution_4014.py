'''
문제
- 활주로는 높이가 동일한 구간에서 건설이 가능
- 가로 세로 건설 가능
- 높이 1cm의 경사로를 설치하면 다른 높이라도 경사로 설치가 가능

입력 : 경사로의 길이 x, 절벽지대의 높이 정보
높이는 항상 1, 길이는 2~4
지형은 1~6
1. 총 테스트 개수 T
2. N : 한 변의 크기, X: 경사로의 길이
3. N*N 크기의 지형 정보

설계
활주로는 가로방향이 쭉 이어지거나 세로방향이 쭈욱 이어져야함
최대 20*20 이므로 사실 각각 브루트포스로 탐색해도 상관없을 듯하다.
한줄한줄 탐색하며 가능한지 여부를 알아볼 수 있을 것 같다.

경사로를 설치할떄 안되는 경우
1. 높이차이가 단번에 2개이상 나는 경우
2. 경사로가 지형 밖으로 벗어나는 경우
3. 경사로가 배열 밖으로 벗어나는 경우

'''
#한줄을 기준으로 검사하는 함수
def check(line, loadWidth):
    print(line)
    for i in range(len(line)-1):
        #높이차이가 단번에 2개이상
        if abs(line[i]-line[i+1])>1:
            return False
        #경사로 차이가 1임.
        elif line[i]-line[i+1]==1:
            if i+loadWidth>=len(line):
                return False
            #경사로가 지형밖으로 벗어남.
            if abs(line[i]-line[i+loadWidth])>1:
                return False
        elif line[i+1]-line[i]==1:
            if loadWidth>i:
                return False
            #경사로가 지형밖으로 벗어남.
            if abs(line[i]-line[i-loadWidth])>1:
                return False
        #높이차이가 0이거나 위의 조건을 통과한다면 return True
    return True 

#입력 받기
T = int(input())
for t in range(T):
    N, loadWidth = map(int,input().split())

    arr = [[0]*N for i in range(N)]
    for i in range(N):
        arr[i] = list(map(int,input().split()))

    cnt = 0
    #검사받을 한 줄을 선택(행)
    for i in range(N):
        cnt+=check(arr[i], loadWidth)

    new_arr = [0]*N
    #검사받을 한줄을 선택(열)
    for i in range(N):
        for j in range(N):
            new_arr[j] = arr[j][i]
        cnt+=check(new_arr, loadWidth)
    print(f"#{t+1} {cnt}")