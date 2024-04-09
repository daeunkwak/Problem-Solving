"""
author : https://github.com/daeunkwak
date : 2024-04-09
title : 주식가격
"""

import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer[]> stack = new Stack<>();

        for(int i = 0; i < prices.length; i++){
            answer[i] = answer.length - 1 - i; // 최대기간으로 세팅
            Integer[] arr = {i, prices[i]};  // *** 인덱스, 가격 동시에 push

            // 가격이 떨어졌다 > 꺼내서 answer값 생성
            while(!stack.empty() && stack.peek()[1] > prices[i]){

                // 이미 쌓여있다는건 가격이 떨어지지 않음을 보장함 > pop해도 다음 값에 영향 x
                Integer[] price = stack.pop();
                answer[price[0]] = i - price[0];

            }

            stack.push(arr);
        }

        return answer;
    }
}