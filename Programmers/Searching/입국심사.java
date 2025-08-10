"""
author : https://github.com/daeunkwak
date : 2024-06-30
title : 입국심사
"""

import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;

        // 심사시간 오름차순 정렬
        Arrays.sort(times);
        long min = 1;

        long max = (long) times[times.length - 1] * n;
        long mid = 0;
        long sum = 0;
        answer = max;

        // 이분 탐색
        while(min <= max) {
            sum = 0;
            mid = (min + max) / 2;

            for(int time : times) {
                sum += mid / time;
            }

            if(sum >= n) {
                answer = mid;
                max = mid - 1;
            }
            else {
                min = mid + 1;
            }
        }

        return answer;
    }
}
