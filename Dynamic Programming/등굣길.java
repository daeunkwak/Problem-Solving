"""
author : https://github.com/daeunkwak
date : 2024-04-12
title : 등굣길
"""

import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int mod = 1000000007;

        // (1,1) 기준으로 하기 위해 배열 크게 만들기
        int[][] board = new int[n + 1][m + 1];
        for(int i = 0; i < puddles.length; i++) {
            board[puddles[i][1]][puddles[i][0]] = -1;  // 웅덩이 표시
        }

        board[1][1] = 1;
        // 최단거리 개수 구하기 공식..
        for(int i = 1; i < n + 1; i++) {
            for(int j = 1; j < m + 1; j++) {
                if(board[i][j] == -1) continue;
                if(board[i - 1][j] > 0) board[i][j] += board[i - 1][j] % mod;
                if(board[i][j - 1] > 0) board[i][j] += board[i][j - 1] % mod;
            }
        }
        return board[n][m] % mod;
    }
}
