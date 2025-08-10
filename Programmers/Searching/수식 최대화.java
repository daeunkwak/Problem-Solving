"""
author : https://github.com/daeunkwak
date : 2024-03-13
title : 수식 최대화
"""

import java.util.*;

class Solution {
    public long solution(String expression) {
        long answer = 0;

        List<Long> nums = new ArrayList();
        List<Character> opers = new ArrayList();

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < expression.length(); i++){
            char ch = expression.charAt(i);
            if (Character.isDigit(ch)) {
                sb.append(ch);  // 숫자 -> sb에 추가
            } else {
                nums.add(Long.parseLong(sb.toString())); // nums에 추가
                sb.setLength(0); // sb 초기화
                opers.add(ch); // 연산자를 리스트에 추가
            }
        }
        nums.add(Long.parseLong(sb.toString()));
        System.out.println("opers : " + opers.size());

        char[][] signs =
                {{'+', '-', '*'}, {'+', '*', '-'},
                        {'-', '+', '*'}, {'-', '*', '+'},
                        {'*', '+', '-'}, {'*', '-', '+'}};

        long res = 0;
        long oper_res = 0;
        long max = 0;

        for(int i = 0; i < 6; i++){  // 우선순위 경우의수
            ArrayList<Long> copiedNums = new ArrayList<>(nums);
            ArrayList<Character> copiedOpers = new ArrayList<>(opers);

            res = 0;
            for(int s = 0; s < 3; s++){  // 연산자 개수
                for(int j = 0; j < copiedOpers.size(); j++){   // 계산하기
                    if(signs[i][s] == copiedOpers.get(j)){
                        oper_res = getOperation(copiedNums.get(j), copiedNums.get(j+1), copiedOpers.get(j));
                        //res += oper_res;

                        copiedNums.remove(j);
                        copiedNums.remove(j);
                        copiedOpers.remove(j);

                        copiedNums.add(j, oper_res);
                        j--;
                    }
                    // System.out.println("res :: " + oper_res);
                    // max = Math.max(max, Math.abs(copiedNums.get(0)));
                    // oper_res = 0;
                }
            }
            System.out.println("res :: " + oper_res);
            max = Math.max(max, Math.abs(copiedNums.get(0)));
            oper_res = 0;
        }

        return max;
    }

    public long getOperation(long num1, long num2, char oper){
        if(oper == '-'){
            return (num1 - num2);
        } else if(oper == '+'){
            return (num1 + num2);
        } else{
            return (num1 * num2);
        }
    }
}

