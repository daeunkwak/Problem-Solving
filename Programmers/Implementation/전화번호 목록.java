"""
author : https://github.com/daeunkwak
date : 2024-04-11
title : 전화번호 목록
"""

import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < phone_book.length; i++) {
            map.put(phone_book[i], i);
        }

        // 모든 전화번호에 대하여
        for (int i = 0; i < phone_book.length; i++) {
            // 모든 접두어 경우의 수 확인
            for (int j = 0; j < phone_book[i].length(); j++) {
                if (map.containsKey(phone_book[i].substring(0,j))) {
                    return false;
                }
            }
        }

        return true;
    }
}
