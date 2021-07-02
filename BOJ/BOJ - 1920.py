"""
author : Kwak Daeun
github : https://github.com/daeunkwak

"""
N = int(input())
N_list = list(map(int, input().split()))
N_list.sort()

M = int(input())
M_list = list(map(int, input().split()))
# start, end, mid 변수를 사용해서 좀 더 직관적으로 짜야할듯
# 인덱스를 start, end, mid로 설정해서 N_list[mid] 이런식으로 넣어야 한다!!!

start = 0
end = N - 1
flag = True

for i in M_list :
    start = 0
    end = N - 1
    flag = True
    while start <= end :
        mid = (start + end) // 2
        if N_list[mid] < i:
            start = mid + 1
        elif N_list[mid] > i:
            end = mid - 1
        else:
            print(1)
            flag = False
            break
    if flag is True:
        print(0)
