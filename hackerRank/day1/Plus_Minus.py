#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'plusMinus' function below.
#
# The function accepts INTEGER_ARRAY arr as parameter.
#

def plusMinus(arr):
    # positive, negative, 0
    length = len(arr)
    positive, negative, zero = 0, 0, 0
    for i in range(length):
        if arr[i] > 0:
            positive+=1
        elif arr[i] < 0:
            negative+=1
        else:
            zero+=1
    print(round(positive/length,6))
    print(round(negative/length,6))
    print(round(zero/length,6))
    
    
if __name__ == '__main__':
    n = int(input().strip())

    arr = list(map(int, input().rstrip().split()))

    plusMinus(arr)
