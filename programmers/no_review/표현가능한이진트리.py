def solution(numbers):
    answer = []
    for number in numbers:
        nodes=bin(number)[2:]
        #더미노드를 확장한다
        i=0
        while 2**i-1<len(nodes):
            i+=1
        nodes = (2**i-1-len(nodes))*'0'+nodes

        answer.append(int(dfs(nodes)))    
    return answer

def dfs(binary):
    if len(binary) == 1:
        return True
    
    if binary[len(binary)//2] == '0':
        if binary[len(binary)//2-(len(binary)//4+1)]=='1' or binary[len(binary)//2+(len(binary)//4+1)]=='1':
            return False
        
    left = dfs(binary[:len(binary)//2])
    right = dfs(binary[len(binary)//2+1:])
    
    if left and right:
        return True
    else:
        return False