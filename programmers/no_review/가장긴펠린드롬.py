def solution(s):
    answer = 1
    for i in range(0,len(s)-1):
        if answer > (len(s)-i)*2:
            return answer
        
        if s[i] == s[i+1]:
            result1 = search(s, i, i, 1, 1)
            result2 = search(s, i, i+1, 1, 2)
            result = max(result1, result2)
        else:
            result = search(s, i, i, 1, 1)
        if result > answer:
            answer = result
    return answer

def search(s, mid1, mid2, term, tmp):
    while (mid1-term>=0 and mid2+term<len(s)):
            if s[mid1-term]!=s[mid2+term]:
                break
            else:
                term+=1
                tmp+=2
    return tmp