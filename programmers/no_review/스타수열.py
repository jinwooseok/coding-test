from collections import defaultdict
def solution(a):
    answer = 0
    a_dic=defaultdict(list)
    for i in range(len(a)):
        a_dic[a[i]]+=[i]
        
    for v in a_dic.values():
        l = 0
        tmp=0
        for i in range(len(v)):
            if i == len(v)-1:
                if l < v[i] or v[i] < len(a)-1:
                    tmp+=1
                break
            #i보다 작은 수가 있다면 포함
            if l < v[i]:
                tmp+=1
                l=v[i]+1
            #v보다 작은 수가 없지만 i와 i+1 사이에 포함할 수가 있는 경우
            elif l==v[i]:
                if v[i]+1 < v[i+1]:
                    tmp+=1
                    l=v[i]+2
                elif v[i]+1 == v[i+1]:
                    l=v[i+1]
                    
        if answer < tmp*2:
            answer=tmp*2
                
    return answer
#스타 수열의 길이가 짝수인지
#교집합이 존재하는지
#요소가 다른지