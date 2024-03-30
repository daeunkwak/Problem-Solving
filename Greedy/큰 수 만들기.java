"""
author : https://github.com/daeunkwak
date : 2024-03-30
title : 큰 수 만들기
"""

import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int target = number.length() - k;
        int idx = 0;
        int max = 0;

        for(int i = 0; i < target; i++) {
            max = 0;

            // k + i > 제거할 문자 개수 + 이미 선택한 문자 개수
            for(int j = idx; j <= k + i; j++) {
                if(max < number.charAt(j) - '0') {
                    max = number.charAt(j) - '0';
                    idx = j + 1;
                }
            }
            sb.append(max);
        }

        return sb.toString();
    }
}