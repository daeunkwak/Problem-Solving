"""
author : https://github.com/daeunkwak
date : 2024-03-30
title : 택배상자
"""

import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;

        Stack<Integer> st = new Stack<Integer>();
        int idx = 0;    // order 배열의 인덱스
        int n = 1;      // 1-n번 상자
        while(n <= order.length && idx < order.length){

            // 순서와 order 일치
            if(order[idx] == n){
                answer++;
                idx++;
                n++;
                if(n > order.length){
                    n--;
                }

                // 원래 순서보다 order가 더 큰 경우 > 보조 벨트 보관
            } else if(order[idx] > n){
                st.add(n);
                n++;

                // 보조 컨테이너 비어있지 않음 && 찾는 값과 일치
            } else if(!st.isEmpty() && st.peek() == order[idx]){
                answer++;
                st.pop();
                idx++;

                // 보조 컨테이너 비어있지 않음 && 찾는 값 아님
            } else if (!st.isEmpty() && st.peek() != order[idx]) {
                st.push(n);
                n++;

                // 컨테이너가 비어있다면
            } else{
                st.push(n);
                idx++;
            }
        }

        return answer;
    }
}