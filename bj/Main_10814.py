import sys
N = int(sys.stdin.readline())
arr = []
for i in range(N):
    a,b=sys.stdin.readline().split()
    arr.append((int(a),i,b))

arr.sort()
string = ''
for i in range(N):
    string+=f'{arr[i][0]} {arr[i][2]}\n'
print(string)