"""
author : Kwak Daeun
github : https://github.com/daeunkwak

"""
import math
M, N = map(int, input().split())

prime_check = [True for _ in range(N+1)]
prime_check[0] = False
prime_check[1] = False
sqt = int(math.sqrt(N))

for i in range (2, sqt+1) :
    for j in range (i * 2, N+1, i) :
        prime_check[j] = False
for i in range (M, N+1) :
    if prime_check[i] is True :
        print(i)
