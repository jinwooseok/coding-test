import sys
input = sys.stdin.readline
T = int(input())
for t in range(T):
    cnt, string = input().split()
    result = []
    for word in string:
        result.append(word*int(cnt))
    print(''.join(result))
