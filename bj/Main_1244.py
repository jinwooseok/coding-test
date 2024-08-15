'''
문제
- 1부터 시작하는 인덱스이다.
- 남학생 : 스위치 번호가 자신의 배수이면 스위치의 상태를 바꾼다.
- 여학생 : 자신의 스위치를 중심으로 좌우가 대칭이면서 가장 많은 스위치를 포함하는 구간을 찾아 상태를 모두 바꿈

설계
- 입력값
    1. 스위치의 개수
    2. 스위치 현 상태 배열
    3. 학생 수
    4. [성별, 스위치 번호]

비트마스킹과 for문을 사용    
1. 입력받기
2. 남학생 : for문을 통해 배수의 스위치 변경 함수 만들기
3. 여학생 : for문을 통해 좌우로 탐색
4. 스위치 변경 함수 만들기

'''

def woman_change(cur_state, n, switch_num):
    i = 1
    cur_state^=(1<<n-switch_num)
    while True:
        #스위치번호가 1보다 작거나 최대길이보다 크면 리턴
        if (switch_num-i<1) or (switch_num+i>n):
            return cur_state
        
        #그렇지 않은 경우 값 비교 후 변경
        if ((cur_state >> n-(switch_num-i)) & 1) == ((cur_state >> n-(switch_num+i)) & 1):
            cur_state^=(1<<n-switch_num-i)
            cur_state^=(1<<n-switch_num+i)
            i+=1
        else:
            return cur_state

def man_change(cur_state, n, switch_num):
    for i in range(switch_num, n+1, switch_num):  # 1-based index를 0-based로 변환하여 처리
        cur_state ^= (1 << (n - i))
    return cur_state

import sys
switch_cnt = int(sys.stdin.readline())
input_bit = sys.stdin.readline().split()
switch_state = 0
#스위치 배열을 비트로 받기
for i in range(switch_cnt):
    if input_bit[i] == "1":
        switch_state |= 1<<(switch_cnt-i-1)

student_cnt = int(sys.stdin.readline())
#학생 수만큼 비트마스크에 로직을 실행
for i in range(student_cnt):
    s, num = map(int, sys.stdin.readline().split())
    if s == 1:
        switch_state = man_change(switch_state, switch_cnt, num)
    elif s == 2:
        switch_state = woman_change(switch_state, switch_cnt, num)
string = ""
for i in range(switch_cnt):
    if switch_state & 1<<(switch_cnt-i-1) == 0:
        string+='0'
    else:
        string+='1'
    
    if (i+1)%20==0:
        string+='\n'
    else:
        string+=' '

print(string.strip())