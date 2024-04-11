"""
author : https://github.com/daeunkwak
date : 2024-04-11
title : 의상
"""

import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;

        Map<String, List<String>> map = new HashMap<>();
        for (String[] cloth : clothes) {
            String clothType = cloth[1];
            String clothName = cloth[0];
            map.computeIfAbsent(clothType, key -> new ArrayList<>()).add(clothName);
        }

        for(String key : map.keySet()) {
            answer *= (map.get(key).size() + 1);
        }

        // 아무것도 안입는 경우만! 빼면 됨
        answer --;

        return answer;

    }

}
