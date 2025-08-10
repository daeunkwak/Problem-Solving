"""
author : https://github.com/daeunkwak
date : 2024-04-10
title : 구명보트
"""

import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);

        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 0; i < people.length; i++){
            deque.addLast(people[i]);
        }

        // 가장 무거운 사람 + 가장 가벼운 사람 > limit인 경우, 가장 무거운 사람은 혼자 타야함
        while(!deque.isEmpty()){
            if(deque.size() >= 2 && deque.peekFirst() + deque.peekLast() <= limit){
                deque.removeFirst();
                deque.removeLast();
            } else{
                deque.removeLast();
            }
            answer++;
        }

        return answer;
    }
}
