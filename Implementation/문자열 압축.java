"""
author : https://github.com/daeunkwak
date : 2024-05-19
title : 문자열 압축
"""

import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = s.length();

        // N개 단위로 압축
        for (int i = 1; i <= s.length() / 2; i++) {

            StringBuilder result = new StringBuilder();
            String prev = "";
            int cnt = 1;

            // 문자열 내에서 N개씩 쪼개기
            for (int j = 0; j < s.length(); j += i) {
                int endIdx = Math.min(j + i, s.length());
                String curr = s.substring(j, endIdx);

                // 압축 가능 > cnt 증가
                if (curr.equals(prev)) {
                    cnt++;
                    // 압축 불가 > result값 만들기
                } else {
                    if (cnt > 1) {
                        result.append(cnt).append(prev);
                    } else {
                        result.append(prev);
                    }
                    prev = curr;
                    cnt = 1;
                }
            }

            // 남은 result값 만들기
            if (cnt > 1) {
                result.append(cnt).append(prev);
            } else {
                result.append(prev);
            }

            answer = Math.min(answer, result.length());
        }

        return answer;
    }
}
