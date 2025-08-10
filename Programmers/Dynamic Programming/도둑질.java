"""
author : https://github.com/daeunkwak
date : 2024-04-12
title : 도둑질
"""

import java.util.*;

class Solution {
    public int solution(int[] money) {
        int answer = 0;

        int[] first = new int[money.length];  // 첫 번째 집을 터는 경우
        int[] second = new int[money.length];  // 두 번째 집을 터는 경우

        first[0] = money[0];
        first[1] = money[0];

        second[0] = 0;
        second[1] = money[1];

        for (int i = 2; i < money.length; i++) {
            // 이 집을 터는 경우 or 건너뛰고 다음 집을 터는 경우
            first[i] = Math.max(first[i - 1], money[i] + first[i - 2]);
            second[i] = Math.max(second[i - 1], money[i] + second[i - 2]);
        }

        // 첫 집을 터는 경우 > 마지막 집을 못 터니까 len - 2
        return Math.max(first[money.length - 2], second[money.length - 1]);
    }
}