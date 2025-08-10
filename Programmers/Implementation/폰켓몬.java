"""
author : https://github.com/daeunkwak
date : 2024-04-11
title : 폰켓몬
"""

import java.util.*;

class Solution {
    public int solution(int[] nums) {

        HashSet<Integer> set = new HashSet<>();

        for(int i : nums){
            set.add(i);
        }

        if(set.size() >= nums.length / 2){
            return nums.length / 2;
        } else{
            return set.size();
        }

    }
}
