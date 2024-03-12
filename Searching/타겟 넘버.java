"""
author : https://github.com/daeunkwak
date : 2024-03-11
title : 타겟 넘버
"""

class Solution {

    int cnt = 0;

    public int solution(int[] numbers, int target) {
        int answer = 0;
        int depth = 0;
        int result = 0;

        dfs(numbers, depth, target, result);

        return cnt;
    }

    public void dfs(int[] numbers, int depth, int target, int result){
        if (depth == numbers.length){
            if (target == result){
                cnt++;
            }
            return;  // 재귀 탈출
        }

        int plus = result + numbers[depth];
        int minus = result - numbers[depth];

        dfs(numbers, depth + 1, target, plus);
        dfs(numbers, depth + 1, target, minus);
    }
}