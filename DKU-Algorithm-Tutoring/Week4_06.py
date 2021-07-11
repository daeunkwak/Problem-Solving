"""
author : Kwak Daeun
github : https://github.com/daeunkwak

title : 만들 수 없는 금액
동네 편의점의 주인인 동빈이는 N개의 동전을 가지고 있습니다. 이때 N개의 동전을 이용하여 만들 수 없는 양의 정수 금액 중 최소값을 구하는 프로그램을 작성하시오.
예를 들어, N = 5이고, 각 동전이 각각 3원, 2원, 1원, 1원, 9원짜리(화폐 단위) 동전이라고 가정합시다. 이때 동빈이가 만들 수 업슨 양의 정수 금액 중 최솟값은 8원입니다.
또 다른 예시로, N = 3이고, 각 동전이 각각 3원, 5원, 7원짜리 동전이라고 가정합시다. 이때 동빈이가 만들 수 없는 양의 정수 금액중 최솟값은 1원입니다.
description : 그리디 알고리즘
"""

from itertools import combinations

N = int(input())
coins = list(map(int, input().split()))


state = 0
num = 1
comb_sum = []
for j in range (sum(coins)) :
    for i in range (1, N) :
        comb = list(combinations(coins, i))
        for k in range(len(comb)) :
            comb_sum.append(sum(comb[k]))
        if num in comb_sum :
            num += 1
            state = 1
    if state == '0' :
        break

print(num)