"""
author : https://github.com/daeunkwak
date : 2024-04-11
title : 완주하지 못한 선수
"""

import java.util.HashMap;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> map = new HashMap<>();

        // getOrDefault() : 찾는 키가 존재한다면 찾는 키의 값을 반환하고, 없다면 기본 값을 반환
        for (String name : participant){
            map.put(name, map.getOrDefault(name, 0) + 1);
        }

        for (String name : completion){
            map.put(name, map.get(name) - 1);
        }

        for (String key : map.keySet()) {
            if (map.get(key) != 0){
                answer = key;
            }
        }
        return answer;
    }
}

