
"""
author : Kwak Daeun
github : https://github.com/daeunkwak

title : 자물쇠와 열쇠
고고학자인 튜브는 고대 유적지에서 보물과 유적이 가득할 것으로 추정되는 비밀의 문을 발견하였습니다.
그런데 문을 열려고 살펴보니 특이한 형태의 자물쇠로 잠겨 있었고 문 앞에는 특이한 형태의 열쇠와 함께 자물쇠를 푸는 방법에 대해 다음과 같이 설명해주는 종이가 발견되었습니다.
잠겨있는 자물쇠는 격자 한 칸의 크기가 1 x 1인 N x N 크기의 정사각 격자 형태이고 특이한 모양의 열쇠는 M x M 크기인 정사각 격자 형태로 되어 있습니다.
자물쇠에는 홈이 파여 있고 열쇠 또한 홈과 돌기 부분이 있습니다. 열쇠는 회전과 이동이 가능하며 열쇠의 돌기 부분을 자물쇠의 홈 부분에 딱 맞게 채우면 자물쇠가 열리게 되는 구조입니다.
자물쇠 영역을 벗어난 부분에 있는 열쇠의 홈과 돌기는 자물쇠를 여는 데 영향을 주지 않지만, 자물쇠 영역 내에서는 열쇠의 돌기 부분과 자물쇠의 홈 부분이 정확히 일치해야 하며
열쇠의 돌기와 자물쇠의 돌기가 만나서는 안 됩니다. 또한 자물쇠의 모든 홈을 채워 비어있는 곳이 없어야 자물쇠를 열 수 있습니다.
열쇠를 나타내는 2차원 배열 key와 자물쇠를 나타내는 2차원 배열 lock이 매개변수로 주어질 때, 열쇠로 자물쇠를 열 수 있으면 true를,
열 수 없으면 false를 return 하도록 solution 함수를 완성해주세요.
0 -> 홈 1 -> 돌기
description : 구현
"""


def rotate(arr):
    tmp = [[0] * len(arr) for _ in range(len(arr))]

    y = 0
    for i in range(len(arr)):
        x = len(tmp) - 1
        for j in range(len(arr)):
            tmp[i][j] = arr[x][y]
            x -= 1
        y += 1

    return tmp


def check(arr):
    lock_length = len(arr) // 3

    for i in range(lock_length, 2 * lock_length):
        for j in range(lock_length, 2 * lock_length):
            if arr[i][j] != 1:
                return False
    return True


def solution(key, lock):
    N = len(lock) * 3

    tmp = [[0] * N for _ in range(N)]  # 0으로 빈 배열 생성
    for i in range(len(lock)):
        for j in range(len(lock)):
            tmp[i + len(lock)][j + len(lock)] = lock[i][j]
            # 한 칸씩 밀려나게 tmp에 값을 저장해줌
    print(tmp)

    for _ in range(4):
        key = rotate(key)  # 90도 회전

        start_x, start_y = 0, 0
        while (True):
            if start_x + len(key) > len(tmp):
                break

            idx_x = 0
            for i in range(start_x, start_x + len(key)):
                idx_y = 0
                for j in range(start_y, start_y + len(key)):
                    tmp[i][j] += key[idx_x][idx_y]  # 더한다
                    idx_y += 1
                idx_x += 1
            for i in range(len(tmp)):
                print(tmp[i])

            if check(tmp):
                return True

            # True가 아닌 경우
            idx_x = 0
            for i in range(start_x, start_x + len(key)):
                idx_y = 0
                for j in range(start_y, start_y + len(key)):
                    tmp[i][j] -= key[idx_x][idx_y]  # 더했던거 뺀다
                    idx_y += 1
                idx_x += 1

            start_y += 1
            if start_y + len(key) > len(tmp):
                start_y = 0
                start_x += 1

    return False


key = [[0, 0, 0], [1, 0, 0], [0, 1, 1]]
lock = [[1, 1, 1], [1, 1, 0], [1, 0, 1]]
print(solution(key, lock))