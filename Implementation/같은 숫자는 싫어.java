"""
author : https://github.com/daeunkwak
date : 2024-05-21
title : 같은 숫자는 싫어
"""

import java.util.*;

public class Solution {
    public int[] solution(int []arr) {

        Stack<Integer> stack = new Stack<>();
        for(int k : arr){
            if(!stack.isEmpty()){
                if(stack.peek() != k){
                    stack.push(k);
                }
            } else{
                stack.push(k);
            }
        }

        int[] answer = new int[stack.size()];
        int idx = 0;
        while(!stack.isEmpty()){
            answer[idx] = stack.pop();
            idx++;
        }

        reverseArray(answer);
        return answer;
    }

    public static void reverseArray(int[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            // 왼쪽과 오른쪽의 값을 교환
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;

            // 인덱스 이동
            left++;
            right--;
        }
    }
}
