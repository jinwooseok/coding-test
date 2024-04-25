def solution(p):
    return cycle(p)

def cycle(string):
    #1. 빈 문자열인 경우, 빈 문자열 반환
    if string == '':
        return ''
    #2, if 균형잡힌 문자열 = u,v로 분리 처음 만족하는 균형잡힌 괄호 문자열 > (과)의 개수가 같아야함
    u, v = '', ''
    opened, closed = 0, 0
    for i, word in enumerate(string):
        if word == '(': opened+=1
        else: closed+=1
        
        if opened == closed: 
            u, v = string[:i+1], string[i+1:]
            break
    #3. u가 올바른 괄호 문자열인지 검증
    opened, closed = 0, 0
    for i, word in enumerate(u):
        if word == '(': opened+=1
        else: closed+=1
        
        if opened < closed or u[-1]=='(':
            new_string = '('
            #v를 1단계부터 수행한 결과 이어붙임
            new_string+=cycle(v)+')'
            for i in u[1:len(u)-1]:
                if i =='(':
                    new_string+=')'
                else:
                    new_string+='('
            return new_string
        
    return u+cycle(v)
            
    