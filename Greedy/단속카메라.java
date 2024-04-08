"""
author : https://github.com/daeunkwak
date : 2024-04-08
title : 단속카메라
"""

import java.util.*;

class Solution {
    public int solution(int[][] routes) {

        // 차량이 나간 지점 기준 오름차순 정렬
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);

        // 첫 차량 기준 설정
        int camera = routes[0][1];
        int answer = 1;

        // 나간 지점 기준 오름차순 > 다음 차량의 진입 지점이 카메라보다 작은지만 확인하면 됨
        // (나간 지점은 카메라보다 큼을 보장하므로)
        for (int i = 1; i < routes.length; i++) {
            if (routes[i][0] > camera) {    // 진입 지점이 카메라보다 큼 > 새로운 카메라 설치
                answer++;
                camera = routes[i][1];
            }
        }

        return answer;
    }
}