from itertools import product
def solution(users, emoticons):
    discounts = list(product([0.1,0.2,0.3,0.4],repeat=len(emoticons)))
    answer = [0,0]
    for discount in discounts:
        prices = [0]*len(users)
        temp = [0,0]
        for i in range(len(discount)):
            for j in range(len(users)):
                if users[j][0]/100<=discount[i]:
                    prices[j]+=emoticons[i]*(1-discount[i])
        for i, price in enumerate(prices):
            if price//users[i][1]>0:
                temp[0]+=1
            else:
                temp[1]+=price

        if temp[0]>answer[0]:
            answer[0] = temp[0]
            answer[1]=temp[1]
        elif temp[0]==answer[0] and temp[1]>answer[1]:
            answer[1]=temp[1]

    return answer