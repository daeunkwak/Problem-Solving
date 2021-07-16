"""
author : Kwak Daeun
github : https://github.com/daeunkwak

title : 섬의 개수
description : DFS, BFS
"""

import sys
sys.setrecursionlimit(100000)

def dfs(x, y) :
    if 0 <= x < h and 0 <= y < w :
        if graph[x][y] == 1:
            graph[x][y] = 0  # 방문처리

            dfs(x - 1, y - 1)
            dfs(x - 1, y)
            dfs(x - 1, y + 1)
            dfs(x, y - 1)
            dfs(x, y + 1)
            dfs(x + 1, y - 1)
            dfs(x + 1, y)
            dfs(x + 1, y + 1)
            return True

    return False


while True :
    w, h = map(int, sys.stdin.readline().split())
    if w == 0 and h == 0 :
        break

    graph = []
    for i in range(h):
        graph.append(list(map(int, sys.stdin.readline().split())))

    result = 0
    for i in range(h) :
        for j in range(w) :
            if dfs(i, j) is True :
                result += 1
    print(result)