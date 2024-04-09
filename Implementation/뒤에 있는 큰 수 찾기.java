"""
author : https://github.com/daeunkwak
date : 2024-03-30
title : 뒤에 있는 큰 수 찾기
"""

import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {

        int[] answer = new int[numbers.length];

        // 가장 크면서 가장 가까이 있는 수 찾기 > 후입선출 Stack
        Stack<Integer> stack = new Stack<>();

        for(int i = numbers.length - 1; i >= 0; i--){
            // 스택이 비어있지 않음 > 빌 때까지 꺼내서 비교
            while(!stack.isEmpty()){
                if(stack.peek() > numbers[i]){
                    answer[i] = stack.peek();
                    break;
                }else{
                    // *** 어차피 더 크고 가까운 수가 push될 예정 > pop해도 다음 결과값에 영향 없음
                    stack.pop();
                }
            }

            if(stack.isEmpty()){
                answer[i] = -1;
            }

            stack.push(numbers[i]);
        }
        return answer;

    }
}