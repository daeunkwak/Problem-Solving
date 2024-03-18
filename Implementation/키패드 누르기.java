"""
author : https://github.com/daeunkwak
date : 2024-03-18
title : 키패드 누르기
"""

import java.util.*;

class Solution {
    public String solution(int[] numbers, String hand) {
        String answer = "";

        int right = 12;
        int left = 10;
        int right_dist = 0;
        int left_dist = 0;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < numbers.length; i++){
            // 0 -> 11로 치환하여 비교
            if(numbers[i] == 0){
                numbers[i] = 11;
            }

            if(numbers[i] % 3 == 0){
                sb.append('R');
                right = numbers[i];
            } else if(numbers[i] % 3 == 1){
                sb.append('L');
                left = numbers[i];
            } else{ // 2, 5, 8, 0
                right_dist = Math.abs((right - 1) / 3 - (numbers[i] - 1) / 3) + Math.abs((right - 1) % 3 - (numbers[i] - 1) % 3);
                left_dist = Math.abs((left - 1) / 3 - (numbers[i] - 1) / 3) + Math.abs((left - 1) % 3 - (numbers[i] - 1) % 3);

                // 왼, 오 거리 비교
                if(left_dist < right_dist){
                    sb.append('L');
                    left = numbers[i];
                } else if(left_dist > right_dist){
                    sb.append('R');
                    right = numbers[i];
                } else{
                    if(hand.equals("right")){
                        sb.append('R');
                        right = numbers[i];
                    } else {
                        sb.append('L');
                        left = numbers[i];
                    }
                }
            }
        }

        return sb.toString();
    }
}