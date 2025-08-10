"""
author : https://github.com/daeunkwak
date : 2024-04-10
title : 소수 찾기
"""

import java.util.*;

class Solution {

    static Set<Integer> set;  // 중복 제거 Set
    static boolean[] visited = new boolean[7];

    public int solution(String numbers) {
        int answer = 0;
        set = new HashSet<>();
        perm(numbers, "", 0);

        for (Integer num : set) {
            if (isPrime(num)) {
                answer++;
            }
        }
        return answer;
    }

    public static void perm(String numbers, String s, int depth) {
        // numbers 의 끝까지 다 탐색했다면 종료
        if (depth > numbers.length()) {
            return;
        }

        for (int i = 0; i < numbers.length(); i++) {
            if(!visited[i]) {
                visited[i] = true;
                set.add(Integer.parseInt(s + numbers.charAt(i)));
                perm(numbers ,s + numbers.charAt(i), depth + 1);
                visited[i] = false;
            }
        }
    }

    // 에라토스테네스의 체
    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
