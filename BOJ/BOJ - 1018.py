"""
author : Kwak Daeun
github : https://github.com/daeunkwak

title : 체스판 다시 칠하기
description : 브루트포스 알고리즘
"""

import sys

# 세로N, 가로M
N, M = map(int, sys.stdin.readline().split())

inputt = [list(input()) for x in range (N)]

arr_black = ["BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB",
             "BWBWBWBW", "WBWBWBWB", "BWBWBWBW", "WBWBWBWB"]
arr_white = ["WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW",
             "WBWBWBWB", "BWBWBWBW", "WBWBWBWB", "BWBWBWBW"]

def check_black(arr) :
    cnt = 0
    for i in range (8) :
        for j in range (8) :
            if arr[i][j] != arr_black[i][j] :
                cnt += 1
    return cnt

def check_white(arr) :
    cnt = 0
    for i in range (8) :
        for j in range (8) :
            if arr[i][j] != arr_white[i][j] :
                cnt += 1
    return cnt

minn = 64
for i in range (N - 7) :
    for j in range (M - 7) :
        arr = []
        for k in range (8) :
            strr = inputt[i + k][j : j + 8]
            arr.append(strr)

        black = check_black(arr)
        white = check_white(arr)
        if black < minn :
            minn = black
        if white < minn :
            minn = white
print(minn)











