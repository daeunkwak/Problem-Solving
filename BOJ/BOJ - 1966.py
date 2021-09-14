"""
author : Kwak Daeun
github : https://github.com/daeunkwak
"""

from collections import deque

T = int(input())
for j in range (T) :
    N, M = map(int, input().split())
    print_list = input().split(' ')
    count = 1
    dq = deque(print_list)
    for i in range(len(print_list)):
        print_list[i] = int(print_list[i])
    while(True) :
        if dq[0] != max(dq) :
            dq.append(dq[0])
            dq.popleft()
            if M == 0 :
                M = len(dq) - 1
            else :
                M -= 1
        else :
            dq.popleft()
            if M == 0 :
                print(count)
                break
            else :
                count += 1
                M -= 1