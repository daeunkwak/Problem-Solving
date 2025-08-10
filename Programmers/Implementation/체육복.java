"""
author : https://github.com/daeunkwak
date : 2024-03-11
title : 체육복
"""

import java.util.*;

class Solution {

    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = n - lost.length;

        // 값 비교하기 쉽도록 정렬
        Arrays.sort(lost);
        Arrays.sort(reserve);

        // 여분을 갖고 있는데 도난당했을 경우
        for(int i = 0; i < lost.length; i++){
            for(int j = 0; j < reserve.length; j++){
                if(lost[i] == reserve[j]){
                    answer += 1;
                    lost[i] = -1;
                    reserve[j] = -1; // 더이상 비교할 필요 없는 값 표시
                    break;
                }
            }
        }

        // 빌릴 수 있는 경우 찾기
        for(int i = 0; i < lost.length; i++){
            for(int j = 0; j < reserve.length; j++){
                if(lost[i]-1 == reserve[j] || lost[i]+1 == reserve[j]){
                    answer += 1;
                    reserve[j] = -1; // lost[i]-1 or lost[i]+1은 굳이 표시 안해줘도 됨 대신 break
                    break;
                }
            }
        }
        return answer;
    }
}