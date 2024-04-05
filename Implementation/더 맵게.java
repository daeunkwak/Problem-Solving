"""
author : https://github.com/daeunkwak
date : 2024-04-05
title : 더 맵게
"""

import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;

        // 우선순위 큐 디폴트 > 작은 값이 우선순위
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        for (int i = 0; i < scoville.length; i++) {
            queue.add(scoville[i]);
        }

        while(queue.peek() < K) {
            // 만들 수 없는 경우
            if (queue.size() < 2) {
                return -1;
            }
            else {
                int first = queue.poll();
                int second = queue.poll();

                first += (second * 2);
                queue.offer(first);
                answer += 1;
            }
        }


        return answer;
    }
}
