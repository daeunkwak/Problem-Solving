"""
author : https://github.com/daeunkwak
date : 2024-03-12
title : 모의고사
"""

import java.util.*;

class Solution {
    public int[] solution(int[] answers) {

        int[] first = {1, 2, 3, 4, 5};
        int[] second = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] third = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        int[] result = new int[3];

        for(int i = 0; i < answers.length; i++){
            if(first[i%5] == answers[i]){
                result[0]++;
            }
            if(second[i%8] == answers[i]){
                result[1]++;
            }
            if(third[i%10] == answers[i]){
                result[2]++;
            }
        }

        int max = 0;
        for(int i = 0; i < result.length; i++){
            if(result[i] >= max){
                max = result[i];
            }
        }

        // Array -> list 편한대로
        List<Integer> answ = new ArrayList<Integer>();
        for(int i=0; i<result.length; i++) if(max == result[i]) answ.add(i+1);

        int[] answer = new int[answ.size()];
        for(int i=0; i<answ.size(); i++){
            answer[i] = answ.get(i);
        }

        return answer;
    }
}