n = int(input())
D = []
for _ in range(n):
    D.append(list(map(int, input().split())))
#print(D)
for i in range(1, n) :
    for j in range(len(D[i])) :
        if j == 0 :
            D[i][j] = D[i][j] + D[i - 1][j]
        elif j == i :
            D[i][j] = D[i][j] + D[i - 1][j - 1]
        else :
            D[i][j] = D[i][j] + max(D[i - 1][j - 1], D[i - 1][j])

print(max(D[n - 1]))
