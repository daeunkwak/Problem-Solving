"""
author : https://github.com/daeunkwak
date : 2024-04-02
title : 기능개발
"""

import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {

        int size = progresses.length;
        int[] done = new int[size];
        for(int i = 0; i < size; i++){
            int days = ((100 - progresses[i]) / speeds[i]);
            if(((100 - progresses[i]) % speeds[i]) == 0){
                done[i] = days;
            } else{
                done[i] = days + 1;
            }
        }

        List<Integer> result = new ArrayList<>();
        int cnt = 1;
        int prev = done[0];
        for (int i = 1; i < size; i++) {
            if (done[i] <= prev) {
                cnt++;
            } else {
                result.add(cnt);
                cnt = 1;
                prev = done[i];
            }
        }
        result.add(cnt);

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
