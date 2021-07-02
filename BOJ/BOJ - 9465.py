"""
author : Kwak Daeun
github : https://github.com/daeunkwak

"""
T = int(input())
for _ in range (T) :
    st = []
    n = int(input())
    st.append(list(map(int, input().split())))
    st.append(list(map(int, input().split())))

    st[0][1] = st[0][1] + st[1][0]
    st[1][1] = st[1][1] + st[0][0]

    for i in range(2, n) :
        st[0][i] = st[0][i] + max(st[1][i - 1], st[1][i - 2])
        st[1][i] = st[1][i] + max(st[0][i - 1], st[0][i - 2])

    print(max(st[0][n - 1], st[1][n - 1]))

