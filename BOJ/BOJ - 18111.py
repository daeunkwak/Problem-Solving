"""
author : Kwak Daeun
github : https://github.com/daeunkwak

title : 마인크래프트
description : 구현, 브루트포스 알고리즘
"""

import sys


N, M, B = map(int, sys.stdin.readline().split())

arr = [list(map(int, sys.stdin.readline().split())) for _ in range(N)]


height = 0
minn = 1000000000000000000000000000000
for i in range (257) :
    up = 0
    down = 0
    for j in range (N) :
        for k in range (M) :
            if arr[j][k] < i :
                up += i -arr[j][k]
            else :
                down += arr[j][k] - i
    inven = down + B
    if inven < up :
        continue
    time = down * 2 + up
    if time <= minn :
        minn = time
        height = i
print(minn, height)






