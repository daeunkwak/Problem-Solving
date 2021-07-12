
"""
author : Kwak Daeun
github : https://github.com/daeunkwak

title : 뱀
'Dummy' 라는 도스게임이 있다. 이 게임에는 뱀이 나와서 기어다니는데, 사과를 먹으면 뱀 길이가 늘어난다. 뱀이 이리저리 기어다니다가 벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다.
게임은 NxN 정사각 보드위에서 진행되고, 몇몇 칸에는 사과가 놓여져 있다. 보드의 상하좌우 끝에 벽이 있다. 게임이 시작할때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1 이다. 뱀은 처음에 오른쪽을 향한다.
뱀은 매 초마다 이동을 하는데 다음과 같은 규칙을 따른다.
먼저 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
만약 이동한 칸에 사과가 있다면, 그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다.
만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다. 즉, 몸길이는 변하지 않는다.
사과의 위치와 뱀의 이동경로가 주어질 때 이 게임이 몇 초에 끝나는지 계산하라.
description : 구현
"""

from collections import deque

def Graph(graph, Time, Step) :
    queue = deque()
    d = 90
    time = 0
    index = 0

    queue.append((1, 1))
    x, y = 1, 1
    while queue :
        # 1. 방향 결정하기
        if time in Time :  # 바뀌는 시간이 되었을 때
            if Step[index] == 'L' :
                d -= 90  # 왼쪽으로 90 회전
            else :
                d += 90  # 오른쪽으로 90 회전
            if abs(d) == 360 :  # abs -> 절대값 함수
                d = 0
            index += 1

        # 2. 방향에 따라 이동하기
        if d == 90 or d == -270 :
            x += 1
        elif d == 180 or d == -180 :
            y += 1
        elif d == 270 or d == -90 :
            x -= 1
        else :
            y -= 1
        time += 1

        # 3. 벽에 부딪히면 종료, 자기 몸과 겹치면 종료
        if x <= 0 or x > N or y <= 0 or y > N or (y, x) in queue :
            break

        if graph[y][x] == 1 :
            graph[y][x] = 0
            queue.appendleft((y, x))
            # 사과와 부딪힌 경우 -> 꼬리 그대로 -> pop 안함
        else :
            queue.appendleft((y, x))
            queue.pop()
    return time

N = int(input())
K = int(input())

graph = [[0 for _ in range (N + 1)] for _ in range (N + 1)]
Time = []  # 몇초마다 방향이 바뀌는지 저장
Step = []  # 방향 저장
for i in range (K) :
    A, B = map(int, input().split())
    graph[A][B] = 1
    # 사과위치 받아와서 graph 0 -> 1 저장

L = int(input())
for i in range (L) :
    A, B = map(str, input().split())
    Time.append(int(A))
    Step.append(B)
    # 방향, 몇초후에 받아와서 각각 저장

print(Graph(graph, Time, Step))
# 사과 위치가 담긴 보드, 방향, 바꾸는 시간을 매개변수로 전달