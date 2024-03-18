"""
author : https://github.com/daeunkwak
date : 2024-03-18
title : 카펫
"""

import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        List<Integer> answer = new ArrayList<>(Arrays.asList(0, 0));

        // yellow의 약수 구하기
        List<List<Integer>> divisors = findDivisorPairs(yellow);
        for(List<Integer> pair : divisors){
            // (yellow의 가로길이 + 2) * (yellow의 세로길이 + 2) - yellow = brown
            if(((pair.get(0)+2) * (pair.get(1)+2) - yellow) == brown){
                answer.set(0, pair.get(0) + 2);
                answer.set(1, pair.get(1) + 2);
            }
        }

        Collections.sort(answer, Collections.reverseOrder());
        int[] answer_list = new int[2];
        answer_list[0] = answer.get(0);
        answer_list[1] = answer.get(1);

        return answer_list;
    }

    public static List<List<Integer>> findDivisorPairs(int n) {
        List<List<Integer>> pairs = new ArrayList<>();

        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                int divisor1 = i;
                int divisor2 = n / i;

                List<Integer> pair = new ArrayList<>();
                pair.add(divisor1);
                pair.add(divisor2);
                pairs.add(pair);

                if (divisor1 != divisor2) {
                    pair = new ArrayList<>();
                    pair.add(divisor2);
                    pair.add(divisor1);
                    pairs.add(pair);
                }

            }
        }

        return pairs;
    }
}