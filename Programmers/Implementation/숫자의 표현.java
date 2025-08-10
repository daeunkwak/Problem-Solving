"""
author : https://github.com/daeunkwak
date : 2024-04-12
title : 숫자의 표현
"""

import java.util.*;

class Solution {
    public int solution(int n) {
        int answer = 0;

        int leftIdx = 0;
        int rightIdx = 0;
        int sum = 0;

        int[] arr = new int[n];  // 일단 배열로 만들어
        for(int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        // ** 슬라이딩 윈도우 **
        // rightIdx 한 칸씩 이동하면서 비교
        for(rightIdx = 0; rightIdx < n - 1; rightIdx++){
            sum += arr[rightIdx];

            if(sum == n) {
                answer++;
            }

            // 값을 다 채워놓고 넘치면 > leftIdx를 이동 > 값 빼주면서 비교
            else{
                while(sum >= n){
                    sum -= arr[leftIdx];
                    leftIdx++;
                    if(sum == n){
                        answer++;
                    }
                }
            }
        }
        return answer + 1;
    }
}
