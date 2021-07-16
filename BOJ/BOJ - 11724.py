"""
author : Kwak Daeun
github : https://github.com/daeunkwak

title :  연결요소의 개수
description : DFS, BFS
"""

import sys
sys.setrecursionlimit(10000000)

def dfs(graph, v, visited) :
    visited[v] = True

    for i in graph[v] :
        if visited[i] is False :
            dfs(graph, i, visited)

N, M = map(int, sys.stdin.readline().split())
visited = [False] * (N + 1)
connect = [[] for _ in range (N + 1)]

for i in range (M) :
    u, v = map(int, sys.stdin.readline().split())
    connect[u].append(v)
    connect[v].append(u)

count = 0
for i in range (1, N + 1) :
    if visited[i] == False :
        count += 1
        dfs(connect, i, visited)

print(count)


