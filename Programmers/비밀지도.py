"""
author : Kwak Daeun
github : https://github.com/daeunkwak

title : 비밀지도
description : 구현
"""

def solution(n, arr1, arr2) :
    answer = []
    map1 = []
    map2 = []

    # 이진화 -> '0b'부분 슬라이싱으로 제거
    for i in range (n) :
        bin1 = bin(arr1[i])[2 : ]
        bin2 = bin(arr2[i])[2 : ]
        map1.append(bin1)
        map2.append(bin2)

    # 0을 추가해서 5칸 맞춰주기
    for i in range (n) :
        if len(map1[i]) != n :
            map1[i] = '0' * (n - len(map1[i])) + map1[i]
        if len(map2[i]) != n :
            map2[i] = '0' * (n - len(map2[i])) + map2[i]

    # 1 -> #, 0 -> 공백으로 변환
    for i in range (n) :
        map1[i] = map1[i].replace('1', '#').replace('0', ' ')
        map2[i] = map2[i].replace('1', '#').replace('0', ' ')
        answer.append("")
        for j in range (n) :
            if map1[i][j] == '#' or map2[i][j] == '#' :
                answer[i] += '#'
            else :
                answer[i] += ' '
    return answer

# n = 5
# arr1 = [9, 20, 28, 18, 11]
# arr2 = [30, 1, 21, 17, 28]
#
# print(solution(n, arr1, arr2))