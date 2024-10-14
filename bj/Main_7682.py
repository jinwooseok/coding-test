import sys

input = sys.stdin.readline
while True:
    state = input()
    #print(state)
    if state[0:3] == "end":
        break
    tictacto = list(state)[:-1]
    #print(tictacto)
    finished = 0
    dot, x, o = 0, 0, 0
    for i in range(3):
        for j in range(3):
            if tictacto[3*i+j] == 'O':
                o+=1
            elif tictacto[3*i+j] == 'X':
                x+=1
            else:
                dot+=1
    winner = set()
    for i in range(3):
        if tictacto[3*i] == tictacto[3*i+1] == tictacto[3*i+2] and tictacto[3*i] in ['O', 'X']:
            winner.add(tictacto[3 * i])
            finished += 1
            break
        if tictacto[i] == tictacto[3+i] == tictacto[6+i] and tictacto[i] in ['O', 'X']:
            winner.add(tictacto[i])
            finished += 1
    if tictacto[0] == tictacto[4] == tictacto[8] and tictacto[0] in ['O', 'X']:
        winner.add(tictacto[0])
        finished += 1
    if tictacto[2] == tictacto[4] == tictacto[6] and tictacto[6] in ['O', 'X']:
        winner.add(tictacto[2])
        finished += 1

    if len(winner) <= 1:
        #승자가 없는 경우
        if dot == 0 and x == 5 and o == 4 and len(winner) == 0:
            print("valid")
        #승자가 x인 경우
        elif len(winner) == 1 and finished >= 1 and x-o == 1 and winner.pop() == 'X':
            print("valid")
        # 승자가 o인 경우
        elif len(winner) == 1 and finished >= 1 and x-o == 0 and winner.pop() == 'O':
            print("valid")
        else:
            print("invalid")
    else:
        print("invalid")