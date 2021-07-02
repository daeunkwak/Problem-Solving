"""
author : Kwak Daeun
github : https://github.com/daeunkwak

"""
from collections import Counter
import sys

N = int(sys.stdin.readline())
N_list = [int(sys.stdin.readline()) for _ in range(N)]

if N == 1:  # 예외가 많음
    avg, center, frq = N_list[0], N_list[0], N_list[0]
    rng = 0
else:
    avg = round(sum(N_list) / N)
    sorted_list = sorted(N_list)  # sorted -> 새로 정렬된 리스트 반환, 원래 리스트 영향 x
    center = sorted_list[len(sorted_list) // 2]

    count_list = Counter(N_list)
    count_list = count_list.most_common()
    #print(count_list)

    sort_list = sorted(count_list, key=lambda x: (-x[1], x[0]))
    #print(sort_list)
    if sort_list[0][1] == sort_list[1][1]:
        frq = sort_list[1][0]
    else:
        frq = sort_list[0][0]
    rng = max(N_list) - min(N_list)
print(avg, center, frq, rng, sep='\n')
