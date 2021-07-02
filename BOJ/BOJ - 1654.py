"""
author : Kwak Daeun
github : https://github.com/daeunkwak

"""
import sys
K, N = map(int, input().split())
K_list = [int(sys.stdin.readline()) for _ in range(K)]
start, end = 1, max(K_list)

while start <= end :
    mid = (start + end) // 2
    lines = 0
    for i in K_list :
        lines += i // mid  # 딱 중간부터 시작해서 잘라본다
    if lines >= N :
        start = mid + 1  # 더 길게 자를 수도 있으니까
    else :
        end = mid - 1  # 더 짧게 잘라야 하니까
print(end)