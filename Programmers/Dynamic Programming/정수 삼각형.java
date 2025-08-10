"""
author : https://github.com/daeunkwak
date : 2024-04-12
title : 정수 삼각형
"""

import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        int height = triangle.length;

        int[][] dp = new int[height][height];

        // 가장 왼쪽 > 누적합 미리 저장 (경우의수 하나뿐)
        dp[0][0] = triangle[0][0];
        for(int i = 1; i < height; i++) {
            dp[i][0] = dp[i-1][0] + triangle[i][0];
        }

        // 꼭대기 > 1층까지 내려가며 최대값 구하기
        for(int i = 1; i < height; i++) {
            for(int j = 1; j < triangle[i].length; j++) {
                // 왼쪽 위에서 내려와 더했을 때, 오른쪽 위에서 내려와 더했을 때 비교
                dp[i][j] = Math.max(dp[i-1][j-1] + triangle[i][j], dp[i-1][j] + triangle[i][j]);
            }
        }

        // 바닥에서 가장 큰 값 구하기
        for(int i = 0; i < height; i++) {
            answer = Math.max(dp[height - 1][i], answer);
        }

        return answer;
    }
}
