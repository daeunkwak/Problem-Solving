"""
author : https://github.com/daeunkwak
date : 2024-04-01
title : 괄호 회전하기
"""

import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            String rotated = s.substring(i) + s.substring(0, i);
            if (isRight(rotated)) {
                answer++;
            }
        }

        return answer;
    }

    // 올바른 괄호 문자열인지 확인
    public boolean isRight(String s){

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if(c == '(' || c == '{' || c == '[' ){
                stack.push(c);
            } else {
                if(stack.isEmpty()){
                    return false;
                } else if(c == ')'){
                    if(stack.pop() != '('){
                        return false;
                    }
                } else if(c == '}'){
                    if(stack.pop() != '{'){
                        return false;
                    }
                } else if(c == ']'){
                    if(stack.pop() != '['){
                        return false;
                    }
                }
            }
        }

        return stack.isEmpty();
    }
}