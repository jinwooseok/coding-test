from collections import deque
def solution(n, k, cmd):
    answer = ['O']*n
    sheet = []
    for i in range(n):
        if i == 0:
            sheet.append([None, i+1])
        elif i == n-1:
            sheet.append([i-1, None])
        else:
            sheet.append([i-1,i+1])
    tmp = deque() # 삭제한 임시변수 하지만 여러번 z를 누를 경우가 있기 때문에 스택으로 구성하기
    command = deque(cmd)
    while command:
        exc = command.popleft()
        func = exc.split(" ")
        if func[0] == 'U': #D, U 수행
            move=int(func[1])
            for i in range(move):
                k=sheet[k][0]
        elif func[0] == 'D':
            move=int(func[1])
            for i in range(move):
                k=sheet[k][1]
        elif func[0] == 'C': #C, Z 수행
            tmp.append(k)
            if sheet[k][1] == None:
                sheet[sheet[k][0]][1] = sheet[k][1]
                k = sheet[k][0]
            elif sheet[k][0] == None:
                sheet[sheet[k][1]][0] = sheet[k][0]
                k = sheet[k][1]
            else:
                sheet[sheet[k][0]][1] = sheet[k][1]
                sheet[sheet[k][1]][0] = sheet[k][0]
                k = sheet[k][1]
        elif func[0] == 'Z':
            current=tmp.pop()
            if sheet[current][0] == None:
                sheet[sheet[current][1]][0] = current
            elif sheet[current][1] == None:
                sheet[sheet[current][0]][1] = current
            else:
                sheet[sheet[current][0]][1] = current
                sheet[sheet[current][1]][0] = current

    for i in tmp:
        answer[i] = 'X'

    return ''.join(answer)