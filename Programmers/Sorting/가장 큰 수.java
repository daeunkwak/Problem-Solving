"""
author : https://github.com/daeunkwak
date : 2024-03-14
title : 가장 큰 수
"""

import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";

        // numbers string 변환
        String[] num_str = new String[numbers.length];
        for(int i = 0; i < numbers.length; i++){
            num_str[i] = String.valueOf(numbers[i]);
        }

        // compareTo() -> 문자열을 붙여서 판단 후 내림차순 정렬
        Arrays.sort(num_str, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));

        if (num_str[0].equals("0")) {
            return "0";
        }

        return  String.join("", num_str);
    }
}