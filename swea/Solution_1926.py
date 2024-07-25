# 3,6,9를 문자로 바꾼 후 개수를 출력하는 함수
def sum_369(num):
    str_num = str(num)
    cnt = 0
    for i in range(len(str_num)):
        if str_num[i]=="3" or str_num[i]=="6" or str_num[i]=="9":
            cnt+=1
    return cnt    

N = int(input())
#답을 쌓아나갈 문자열
sb = ""
for i in range(1,N+1):
    result = sum_369(i)
    if result == 0: #3,6,9 포함x
        sb+=str(i)
        sb+=" "
    else: # 3,6,9 개수만큼 '-' 추가
        for i in range(result):
            sb+="-"
        sb+=" "
#마지막의 빈공간을 제거한 후 출력
print(sb[:-1])