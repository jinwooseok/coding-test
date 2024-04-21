#!/bin/python3

import math
import os
import random
import re
import sys
from datetime import datetime
#
# Complete the 'timeConversion' function below.
#
# The function is expected to return a STRING.
# The function accepts STRING s as parameter.
#

def timeConversion(s):
    hour = s[0:2]
    time = s[2:8]
    noon = s[8:10]
    if noon == 'AM':
        if hour == '12':
            hour = str(int(s[0:2])-12)
    else:
        if hour < '12':
            hour = str(int(s[0:2])+12)
    return "{:02d}".format(int(hour))+time
    

if __name__ == '__main__':
    fptr = open(os.environ['OUTPUT_PATH'], 'w')

    s = input()

    result = timeConversion(s)

    fptr.write(result + '\n')

    fptr.close()
