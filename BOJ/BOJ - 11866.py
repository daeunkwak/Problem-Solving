"""
author : Kwak Daeun
github : https://github.com/daeunkwak

title : 요세푸스 문제 0
description : 구현, 자료구조, 큐
"""

from collections import deque

# N명, K번째 사람 제거
N, K = map(int, input().split())

dq = deque(x for x in range (1, N + 1))

cnt = 1
res = []

while dq :
    if cnt % K == 0 :
        cnt += 1
        res.append(dq[0])
        dq.popleft()
    else :
        cnt += 1
        dq.append(dq[0])
        dq.popleft()

print('<', end='')
for i in range(len(res)-1):
    print(res[i], end=', ')
print(res[-1], end='>')






















