"""
author : https://github.com/daeunkwak
date : 2024-05-12
title : 파괴되지 않은 건물
"""

import java.util.*;

class Solution {

    public static int[][] sums;

    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int r = board.length;
        int c = board[0].length;

        // 새로운 배열 생성
        sums = new int[r+1][c+1];

        // 새로운 배열에 값 넣어주기
        for(int[] s:skill){
            int type = s[0];
            if(type == 1){  // 공격
                sums[s[1]][s[2]] -= s[5];
                sums[s[3]+1][s[2]] += s[5];
                sums[s[1]][s[4]+1] += s[5];
                sums[s[3]+1][s[4]+1] -= s[5];
            } else {    // 회복
                sums[s[1]][s[2]] += s[5];
                sums[s[3]+1][s[2]] -= s[5];
                sums[s[1]][s[4]+1] -= s[5];
                sums[s[3]+1][s[4]+1] += s[5];
            }
        }


        // 위 -> 아래
        for(int j=0;j<c+1;j++){
            for(int i=0;i<r;i++){
                sums[i+1][j] += sums[i][j];
            }
        }


        // 왼 -> 오른
        for(int i=0;i<r+1;i++){
            for(int j=0;j<c;j++){
                sums[i][j+1] += sums[i][j];
            }
        }


        for(int i=0;i<r;i++){
            for(int j=0;j<c;j++){
                if(board[i][j] + sums[i][j] > 0) answer++;
            }
        }

        return answer;
    }

}