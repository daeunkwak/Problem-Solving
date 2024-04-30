"""
author : https://github.com/daeunkwak
date : 2024-04-30
title : [PCCP 모의고사 #2] 2번 - 신입사원 교육
"""

import java.util.*;

class Solution {
    public int solution(int[] ability, int number) {
        int answer = 0;

        // 오름차순 정렬 우선순위 큐(힙) 생성 > ability값 넣기
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < ability.length; i++){
            pq.add(ability[i]);
        }

        // 가장 작은 값 2개 > 합으로 바꿔주기
        for(int i = 0; i < number; i++){
            int sum = pq.poll() + pq.poll();
            pq.add(sum);
            pq.add(sum);
        }

        while(!pq.isEmpty()){
            answer += pq.poll();
        }

        return answer;
    }
}
