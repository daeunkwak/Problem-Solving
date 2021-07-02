"""
author : Kwak Daeun
github : https://github.com/daeunkwak

"""
n = int(input())
w = [0]
for i in range(n) :
    w.append(int(input()))
dp = [0]
dp.append(w[1])

if n > 1 :
    dp.append(w[1] + w[2])
for i in range(3, n + 1) :
    dp.append(max(dp[i-1], dp[i-3] + w[i-1] + w[i], dp[i-2] + w[i]))
    # dp[i-1] -> i번째 와인을 안먹는 경우 최대
    # dp[i-3] + w[i-1] + w[i] -> i번째 와인을 먹고, i-1번째 와인을 먹는 경우
    # dp[i-2] + w[i] -> i번째 와인을 먹고, i-1번째 와인을 안먹는 경우

print(dp[n])