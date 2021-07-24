"""
author : Kwak Daeun
github : https://github.com/daeunkwak

title : 숫자 카드2
description : 이분 탐색, 딕셔너리
"""
import sys

input = sys.stdin.readline

N = int(input())
N_list = list(map(int, input().split()))

M = int(input())
M_list = list(map(int, input().split()))

dicts = {}

for nums in N_list:
    if (nums in dicts):
        dicts[nums] += 1
    else:
        dicts[nums] = 1

print(' '.join(str(dicts[target]) if target in dicts else '0' for target in M_list))



















