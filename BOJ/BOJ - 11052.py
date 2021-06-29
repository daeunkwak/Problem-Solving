N = int(input())
N_list = list(map(int, input().split()))
N_list = [0] + N_list
dp = [0] * 1001

for i in range (N + 1) :
    for j in range(i + 1) :
        # 최대값을 찾기 -> j증가시키기 전 가격( dp[i] ) & j+1된 가격 max로 비교하여 갱신
        # (j+1된 가격 -> N_list[j] + dp[i - j])
        dp[i] = max(dp[i], N_list[j] + dp[i - j])
print(max(dp))