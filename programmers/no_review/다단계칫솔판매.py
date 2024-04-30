from collections import defaultdict
import math
def solution(enroll, referral, seller, amount):
    answer = defaultdict(int)
    parents = {}
    #트리구조. 자식노드에서 아빠노드로 올라가면서
    for i, member in enumerate(referral):
        if member == "-":
            parents[enroll[i]] = None
        else:
            parents[enroll[i]] = member
    
    for member, count in zip(seller, amount):
        parent = member
        price = count*100
        while parent:
            if price*0.1<1:
                answer[parent]+=price
                break
            else:
                answer[parent]+=math.ceil(price*0.9)
                price-=math.ceil(price*0.9)
                parent = parents[parent]

    li=[0]*len(enroll)
    for i in range(len(enroll)):
        li[i] = int(answer[enroll[i]])
    return li