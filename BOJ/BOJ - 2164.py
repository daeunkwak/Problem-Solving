"""
author : Kwak Daeun
github : https://github.com/daeunkwak

title : 카드2
description : 자료구조, 덱
"""

import collections

N = int(input())
deqq = collections.deque()
for i in range (N) :
    deqq.append(i + 1)
for i in range (N - 1) :
    deqq.popleft()
    deqq.append(deqq[0])
    deqq.popleft()
print(deqq[0])

