"""
author : https://github.com/daeunkwak
date : 2024-06-22
title : 롤케이크 자르기
"""

import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        int size = topping.length;

        // 중복 제거 HashSet<>, HashMap<토핑종류, 토핑개수>
        HashSet<Integer> yb = new HashSet<>();
        HashMap<Integer, Integer> ob = new HashMap<>();

        yb.add(topping[0]);
        for (int i = 1; i < size; i++) {
            ob.put(topping[i], ob.getOrDefault(topping[i], 0) + 1);
        }

        for (int i = 1;i < size; i++) {
            yb.add(topping[i]);
            // HashSet에서 하나씩 제거하는 법 > HashMap으로 만들어서 세기
            ob.put(topping[i], ob.get(topping[i]) - 1);
            if (ob.get(topping[i]) == 0) {
                ob.remove(topping[i]);
            }
            if (yb.size() == ob.size()){
                answer++;
            }
        }

        return answer;
    }
}