'''
총감독관은 1명만됨
부감독관은 여러명됨
필요한 감독관 수는?
총감독관은 필수. 부감독관은 되는대로.
'''
map_cnt = int(input())
student_list = list(map(int,input().split()))
manager1, manager2 = map(int,input().split())
cnt = 0
for student_cnt in student_list :
    #총감독관 까기
    student_cnt-=manager1
    cnt+=1
    if student_cnt<=0: #총감독관으로 충분한 경우 pass
        continue
    else: #부족한 경우 부감독관을 채워야 함
        if student_cnt%manager2 == 0:
            cnt+=student_cnt//manager2
        else:
            cnt+=student_cnt//manager2+1
print(cnt)