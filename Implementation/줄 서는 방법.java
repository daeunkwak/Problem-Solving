"""
author : https://github.com/daeunkwak
date : 2024-05-21
title : 줄 서는 방법
"""

import java.util.*;

class Solution {
    public int[] solution(int n, long k) {

        int[] answer = new int[n];

        ArrayList<Integer> list = new ArrayList<>();
        long factorial = 1;
        int idx = 0;

        // 팩토리얼과 사람 리스트 구하기
        for (int i = 1; i <= n; i++) {
            factorial *= i;
            list.add(i);
        }

        // 인덱스를 맞추기 위해 k--
        k--;

        while (n > 0) {

            // 1, 2, 3, 4 순열 가짓수는 4! = 24
            // 첫번째 값을 1로 고정 > 2, 3, 4 순열 가짓수는 3! = 6
            // 6 x 4개의 경우의 수 > 6의 배수마다 첫번째 값이 바뀜
            factorial = factorial / n;  // 4! -> 3! -> 2! -> 1! (현재 남은 자리 수의 팩토리얼값)

            // 현재 자리에 올 숫자의 인덱스
            // ex) factorial이 6이고 k가 17이면 val = 17 / 6 = 2 > 첫번째 자리 1, 2를 건너뛰고 3으로 가는식
            int val = (int) (k / factorial);

            // 정답 배열에 숫자 삽입
            answer[idx] = list.get(val);
            list.remove(val);

            // 다음 자리수를 구하기 위해 k값 변경
            k %= factorial;
            idx++;
            n--;
        }
        return answer;
    }
}
