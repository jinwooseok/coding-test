#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'minimumBribes' function below.
#
# The function accepts INTEGER_ARRAY q as parameter.
#

def minimumBribes(q):
    bribe_count = 0
    mlist = [sys.maxsize, sys.maxsize]
    for i in range(len(q),0,-1):
        #more than 2 bribe
        if q[i-1]-i>2:
            print("Too chaotic")
            return
        #less than 2 bribe and bribe 2 person
        if q[i-1] > mlist[0] and q[i-1] > mlist[1]:
            bribe_count+=2
        elif q[i-1] > mlist[0]:
            bribe_count+=1
            mlist[1] = q[i-1]
        elif q[i-1] > mlist[1]:
            bribe_count+=1
            mlist[0] = q[i-1]
        else:
            mlist = [min(mlist), q[i-1]]
    print(bribe_count)
    return

if __name__ == '__main__':
    t = int(input().strip())

    for t_itr in range(t):
        n = int(input().strip())

        q = list(map(int, input().rstrip().split()))
 
        minimumBribes(q)
