"""
author : https://github.com/daeunkwak
date : 2024-06-22
title : 2개 이하로 다른 비트
"""

import java.util.*;

class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for(int i = 0; i < numbers.length; i++){
            answer[i] = getNextNumber(numbers[i]);
        }

        return answer;
    }

    private long getNextNumber(long num) {
        if (num % 2 == 0) {
            // 짝수인 경우 가장 작은 홀수로 변환
            return num + 1;

        } else {
            // 홀수인 경우
            // 가장 작은 0 비트를 1로 변환하고, 그 다음 비트를 0으로 변환
            long bitMask = 1;
            while ((num & bitMask) != 0) {
                bitMask <<= 1;
            }
            return (num | bitMask) & ~(bitMask >> 1);
        }
    }
}