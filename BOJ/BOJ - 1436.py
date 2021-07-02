"""
author : Kwak Daeun
github : https://github.com/daeunkwak

"""
N = int(input())
cnt = 1
num = 666
while(cnt != N) :
    num += 1
    if '666' in str(num) :
        cnt += 1
    #print(num)
print(num)