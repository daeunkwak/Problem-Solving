"""
author : Kwak Daeun
github : https://github.com/daeunkwak

title : 골드바흐의 추측
description : 수학, 정수론, 소수 판정, 에라토스테네스의 체
"""

import sys

prime_list = [False, False] + [True]*10002

for i in range(2, 10002):
    if prime_list[i]:
        for j in range(2*i, 10002, i):
            prime_list[j] = False

T = int(sys.stdin.readline())

for i in range(T):
    N = int(sys.stdin.readline())
    mid = N // 2
    midd = mid
    while mid > 0:
        if prime_list[mid] and prime_list[midd]:
            print(mid, midd)
            break
        else:
            # 차이가 가장 적어야 하니까 차이0부터 벌려가며 탐색
            mid -= 1
            midd += 1