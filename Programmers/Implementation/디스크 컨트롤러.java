"""
author : https://github.com/daeunkwak
date : 2024-04-05
title : 디스크 컨트롤러
"""

import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        int end = 0;
        int jobsIdx = 0;
        int count = 0;

        // 1. 먼저 요청순 정렬
        Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

        // 2. 수행시간 짧은순 정렬을 위한 우선순위 큐(힙)
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

        while (count < jobs.length) {

            // 하나의 작업이 끝날 때 까지 중간에 있는 작업들을 큐에 넣음
            while (jobsIdx < jobs.length && jobs[jobsIdx][0] <= end) {
                pq.add(jobs[jobsIdx++]);
            }

            // 큐가 비어있다면 > 하나의 작업 끝 > 수행시간 이동
            if (pq.isEmpty()) {
                end = jobs[jobsIdx][0];

                // 수행시간이 짧은 요청부터 수행
            } else {
                int[] temp = pq.poll();
                // 총 소요시간 = 소요시간 + 시작시간 - 요청시점
                answer += temp[1] + end - temp[0];
                end += temp[1];
                count++;
            }
        }

        // Math.floor() > 소수점 버림
        return (int) Math.floor(answer / jobs.length);
    }
}
