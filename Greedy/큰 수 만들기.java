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
            // 맨 앞자리수가 최대한 커야함, 근데 뒤에 n개는 확보해야 함
            // k + i > 제거할 문자 개수 + 이미 선택한 문자 개수
            for(int j = idx; j <= k + i; j++) {
                if(max < number.charAt(j) - '0') {
                    max = number.charAt(j) - '0';
                    idx = j + 1;  // 선택한 수의 다음 인덱스부터 탐색
                }
            }
            sb.append(max);
        }

        return sb.toString();
    }
}