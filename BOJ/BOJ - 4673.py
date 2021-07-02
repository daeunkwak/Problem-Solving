"""
author : Kwak Daeun
github : https://github.com/daeunkwak

"""

check_list = [True] * 10001
for i in range (10000) :
    num = str(i)
    res = i
    for j in range (len(num)) :
        res += int(num[j])
    if res <= 10000 :
        check_list[res] = False
idx = 0
for i in range (len(check_list)) :
    if check_list[i] == True :
        print(idx)
    idx += 1