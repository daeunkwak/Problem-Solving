"""
author : https://github.com/daeunkwak
date : 2024-04-01
title : 올바른 괄호
"""

import java.util.*;

class Solution {
    boolean solution(String s) {

        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push('(');
            } else{
                if(stack.isEmpty()){
                    return false;
                } else{
                    stack.pop();
                }
            }
        }

        if(!stack.isEmpty()){
            return false;
        }

        return true;
    }
}