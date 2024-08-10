'''
y인것만 골라서 끄는 알고리즘 구상
예)2번 스위치부터 끄기로 설정
>절대 1번 스위치를 끌수없음
>그러므로 1번 스위치를 무조건 꺼야함
예)1011000101 이라면
>1번스위치 작업을 건너뛴다면 1번스위치는 절대 끌 수 없음
>그러므로 작은 것부터 끄는 작업이 필수임
스위치가 y이면 끄는 방식으로 시도해보자. 생각보다 쉬울듯함.
이렇게 한다면 BFS같이 순회는 필요없음. 최대 횟수는 1000(바꿀 Y스위치선택)*(1~1000)(배수에 맞춰 변경)임
'''
def switch(switches):
    n = len(switches)
    binary = 0
    
    # 초기 상태를 binary로 표현
    for i in range(n):
        if switches[i] == 'Y':
            binary |= (1 << (n - i - 1))
    
    cnt = 0
    for i in range(n):
        if binary & (1 << (n - i - 1)): #스위치가 y라면?
            # 배수 처리 방식
            st=n-i-1
            while st>=0:
                binary ^= (1 << st)
                st-=i+1
            cnt += 1
    return cnt if binary == 0 else -1 #비트마스크가 0이면 cnt리턴
# 입력 처리NNYNYYNNN 
import sys
input = sys.stdin.readline
switches = list(input().strip())
print(switch(switches))


# def switch_bulbs(bulbs):
#     n = len(bulbs)
#     count = 0
    
#     for i in range(n):
#         if bulbs[i] == 'Y':
#             count += 1
#             for j in range(i, n, i+1):
#                 bulbs[j] = 'Y' if bulbs[j] == 'N' else 'N'
    
#     return count if all(bulb == 'N' for bulb in bulbs) else -1

# # 입력 처리
# bulbs = list(input().strip())

# # 결과 출력
# print(switch_bulbs(bulbs))