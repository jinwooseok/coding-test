#!/bin/python3

import math
import os
import random
import re
import sys

#
# Complete the 'caesarCipher' function below.
#
# The function is expected to return a STRING.
# The function accepts following parameters:
#  1. STRING s
#  2. INTEGER k
#

def caesarCipher(s, k):
    result = ''
    if k == 0:
        return s
    
    for word in s:
        if word>='a' and word<='z':
            result+=chr(((ord(word)-ord('a')+k)%26)+ord('a'))
        
        elif word>='A' and word<='Z':
            result+=chr(((ord(word)-ord('A')+k)%26)+ord('A'))
        else:
            result+=word
    return result
if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    n = int(input().strip())

    s = input()

    k = int(input().strip())

    result = caesarCipher(s, k)

    fptr.write(result + '\n')

    fptr.close()
