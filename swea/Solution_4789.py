'''
정리 : 기립 박수 치는 사람의 인덱스 i
i번째 사람이 박수를 치려면 그 전 인덱스까지 i-1명이 있어야함.
로직
for문을 한번 돌면서 박수친 사람의 개수를 센다. 
그 때 index-1보다 작다면 그때 고용할 사람 수를 더한다.
1. 각 인덱스를 셀 때 박수친 사람이 적다면
 => if clap_cnt < i :
2. 사람을 고용한다.
 => 고용한 사람수 += 부족한 사람수(i-clap_cnt)
3. 다음 인덱스를 세기 위해 현재 위치의 사람수를 더한다.
 => 기상한 사람수 += 현재 사람수(arr[i])
'''

T = int(input())
for t in range(T):
    arr = list(input())
    add_cnt = 0 #추가 고용한 알바 수
    clap_cnt = 0 #박수친 사람 수
    for i in range(len(arr)):
        if clap_cnt < i: #박수친 사람이 적다면
            add_cnt+=i-clap_cnt
            clap_cnt+=i-clap_cnt
        clap_cnt+=int(arr[i])
    print(f"#{t+1} {add_cnt}")
