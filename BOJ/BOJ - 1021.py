"""
author : Kwak Daeun
github : https://github.com/daeunkwak

title : 회전하는 큐
description : 자료구조, 덱
"""

import sys
from collections import deque

N, M = map(int, sys.stdin.readline().split())
arr = list(map(int, sys.stdin.readline().split()))
dq = deque([i for i in range(1, N + 1)])

cnt = 0
for num in arr :
    # print("num", num)
    while True :
        if dq[0] == num :
            dq.popleft()
            break
        else :
            if dq.index(num) < (len(dq) // 2 + 1) :
                dq.append(dq[0])
                dq.popleft()
                cnt += 1
            else :
                dq.appendleft(dq[-1])
                dq.pop()
                cnt += 1
    # print("cnt", cnt)
print(cnt)










