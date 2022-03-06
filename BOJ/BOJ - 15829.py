"""
author : Kwak Daeun
github : https://github.com/daeunkwak

title : Hashing
description : 문자열, 해싱
"""

N = int(input())
hash = input()
ans = 0
for i in range (len(hash)) :
    num = ord(hash[i]) - 96
    ans += num * (31 ** i)

print(ans % 1234567891)










