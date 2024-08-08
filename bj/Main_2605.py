import sys
N = int(sys.stdin.readline())
line = list(map(int, sys.stdin.readline().split()))
line_map={i:i-1 for i in range(1,N+1)}
line_list=[i for i in range(1,N+1)]
for i, l in enumerate(line):
    line_list.pop(line_map[i+1])
    line_list.insert(line_map[i+1]-l,i+1)
    line_map[i+1]-=l
for line in line_list:
    print(line,end=" ")