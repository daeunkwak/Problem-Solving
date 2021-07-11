"""
author : Kwak Daeun
github : https://github.com/daeunkwak

title : 시각
정수 N이 입력되면 00시 00분 00초부터 N시 59분 59초까지의 모든 시각 중에서 3이 하나라도 포함되는 모든 경우의 수를 구하는 프로그램을 작성하시오.
예를들어 1을 입력했을 때 다음은 3이 하나라도 포함되어 있으므로 세어야 하는 시간이다.
"""

N = int(input())
time = ""
count = 0
for i in range (N + 1) :
    for j in range (60) :
        for k in range (60) :
            time += str(i)
            time += str(j)
            time += str(k)

            if '3' in time :
                count += 1
            time = ""
print(count)