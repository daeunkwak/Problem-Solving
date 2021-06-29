N = int(input())
cost = []
for i in range(N) :
    cost.append(list(map(int, input().split())))
print(cost)
for i in range(1, N) :
    # 0, 1, 2는 각각 R, G, B
    # i번째 집을 0으로 칠하면, i-1번째 집은 1 or 2
    cost[i][0] = cost[i][0] + min(cost[i - 1][1], cost[i - 1][2])
    cost[i][1] = cost[i][1] + min(cost[i - 1][0], cost[i - 1][2])
    cost[i][2] = cost[i][2] + min(cost[i - 1][1], cost[i - 1][0])

print(min(cost[N - 1][0], cost[N - 1][1], cost[N - 1][2]))


