"""
author : https://github.com/daeunkwak
date : 2024-04-08
title : 요격 시스템
"""

import java.util.*;

class Solution {
    public int solution(int[][] targets) {

        // 미사일 끝난 지점 오름차순 정렬
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);

        // 첫 미사일 기준 설정
        int intercept = targets[0][1];
        int answer = 1;

        // (이전 요격 지점 <= 다음 미사일 시작 지점) > 새로운 요격지점 설치
        for (int i = 1; i < targets.length; i++) {
            if (targets[i][0] >= intercept) {
                answer++;
                intercept = targets[i][1];
            }
        }

        return answer;
    }
}