"""
author : Kwak Daeun
github : https://github.com/daeunkwak

title : 수 정렬하기 3
description : 정렬
"""

import sys

N = int(sys.stdin.readline())
num = []
res = [0] * 1001

for i in range (N) :
    num = int(sys.stdin.readline())
    res[num] += 1

for i in range (len(res)) :
        for j in range (res[i]) :
            print(i)







