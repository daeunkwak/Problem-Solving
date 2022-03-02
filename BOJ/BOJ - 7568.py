"""
author : Kwak Daeun
github : https://github.com/daeunkwak
title : 덩치
description : 구현, 브루트포스 알고리즘
"""

N = int(input())
ppl = []
res = []

for _ in range (N) :
    ppl.append(int, list(input().split()))

for i in range (len(ppl)) :
    cnt = 0
    for j in range (len(ppl)) :
        if ppl[i][0] < ppl[j][0] :
            if ppl[i][1] < ppl[j][1] :
                cnt += 1
    res.append(cnt + 1)

for cnt in res :
    print(cnt, end = " ")


