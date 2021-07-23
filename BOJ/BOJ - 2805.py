"""
author : Kwak Daeun
github : https://github.com/daeunkwak

title : 나무 자르기
description : 이분 탐색
"""

import sys

N, M = map(int, sys.stdin.readline().split())
wood = list(map(int, sys.stdin.readline().split()))
idx = 0

wood.sort()

start = 0
end = wood[-1]
max_wood = 0

while start <= end :
    get = 0
    mid = (start + end) // 2
    if mid != 0 :
        for i in range (N) :
            if wood[i] - mid >= 0 :
                get += wood[i] - mid
    if get >= M :
        max_wood = max(max_wood, mid)
        start = mid + 1
    else :
        end = mid - 1

print(max_wood)