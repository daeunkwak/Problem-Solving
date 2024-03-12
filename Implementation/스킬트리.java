"""
author : https://github.com/daeunkwak
date : 2024-03-11
title : 스킬트리
"""

import java.lang.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {

        int answer = skill_trees.length;
        char[] charArray;
        int cnt = 0;
        char[] skillArray = skill.toCharArray();

        for(int i = 0; i < skill_trees.length; i++){
            charArray = skill_trees[i].toCharArray();
            cnt = 0;

            for(int j = 0; j < charArray.length; j++){

                if(skill.contains(String.valueOf(charArray[j]))){
                    // System.out.println("contains!!!!!");
                    if(skillArray.length > cnt){
                        if(charArray[j] == skillArray[cnt]){
                            cnt++;
                            // System.out.println("continue : cnt : " + cnt);
                            continue;

                        }else{
                            answer--;
                            break;
                        }
                    }
                }
            }
        }


        return answer;
    }
}