"""
author : https://github.com/daeunkwak
date : 2024-03-14
title : 문자열 내림차순으로 배치하기
"""

import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";

        Character[] chars = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) {
            chars[i] = s.charAt(i);
        }

        // 내림차순 정렬
        Arrays.sort(chars, Comparator.reverseOrder());

        // 문자열로 변환
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c);
        }

        return sb.toString();
    }
}