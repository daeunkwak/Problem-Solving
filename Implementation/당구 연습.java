"""
author : https://github.com/daeunkwak
date : 2024-04-12
title : 당구 연습
"""

import java.util.*;

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for (int i = 0; i < balls.length; i++) {
            int targetX = balls[i][0];
            int targetY = balls[i][1];

            int now = 0;
            int result = Integer.MAX_VALUE;

            // (0, y)
            if (!(startY == targetY && startX >= targetX)) {
                now = distance(startX, startY, targetX * (-1), targetY);
                result = Math.min(now, result);
            }

            // (m, y)
            if (!(startY == targetY && startX <= targetX)) {
                now = distance(startX, startY, m + (m - targetX), targetY);
                result = Math.min(now, result);
            }

            // (x, n)
            if (!(startX == targetX && startY <= targetY)) {
                now = distance(startX, startY, targetX, n + (n - targetY));
                result = Math.min(now, result);
            }

            // (x, 0)
            if (!(startX == targetX && startY >= targetY)) {
                now = distance(startX, startY, targetX, targetY * (-1));
                result = Math.min(now, result);
            }

            answer[i] = result;
        }

        return answer;
    }

    // 거리 구하기
    public int distance(int sx, int sy, int tx, int ty) {
        return (int) (Math.pow(sx - tx, 2) + Math.pow(sy - ty, 2));
    }

}
