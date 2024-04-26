def solution(s):
    opened, closed = 0, 0
    if s[0] != '(' or s[-1] !=')':
        return False
    
    for word in s:
        if word == '(':
            opened+=1
        else:
            closed+=1
            
        if closed > opened:
            return False
    
    if opened != closed:
        return False
    else:
        return True
        