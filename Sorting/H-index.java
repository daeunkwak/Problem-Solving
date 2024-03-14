"""
author : https://github.com/daeunkwak
date : 2024-03-14
title : H-index
"""

import java.util.*;

class Solution {
    public int solution(int[] citations) {

        Arrays.sort(citations);

        int answer = 0;
        int hidx = 0;
        int cnt = 0;
        for(int i = 0; i < citations.length; i++){
            hidx = citations.length - i;
            if(citations[i] >= hidx){
                answer = hidx;
                break;
            }
        }

        return answer;
    }
}