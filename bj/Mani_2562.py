max_num = [-1,-1]
for i in range(9):
    num = int(input())
    if max_num[1]<num:
        max_num[0] = i+1
        max_num[1] = num

print(max_num[1])
print(max_num[0])