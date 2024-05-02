"""
author : https://github.com/daeunkwak
date : 2024-05-01
title : [PCCP 모의고사 #2] 3번 - 카페 확장
"""

import java.util.*;

class Solution {
    public int solution(int[] menu, int[] order, int k) {
        int answer = 0;

        // [입장시간, 나가는 시간] 배열 생성
        int[][] ppl = new int[order.length][2];
        ppl[0][0] = 0;
        ppl[0][1] = menu[order[0]];
        for(int i = 1; i < order.length; i++){
            ppl[i][0] = i * k;
            if(ppl[i-1][1] <= ppl[i][0]){
                ppl[i][1] = ppl[i][0] + menu[order[i]];
            } else{
                ppl[i][1] = ppl[i-1][1] + menu[order[i]];
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(ppl[0]);
        int time = 0;
        int idx = 1;
        int max = 0;
        int cnt = 1;

        // 무조건 queue.isEmpty()를 탈출 조건으로 작성하면 안 됨..
        while(true){
            if(time >= k){
                if(time % k == 0){
                    if(cnt < order.length){
                        queue.offer(ppl[idx]);
                        cnt++;
                    }
                    if(idx < order.length - 1){
                        idx++;
                    }
                }
            }

            // 나가는 시간이 되면 poll
            if(!queue.isEmpty()){
                int[] now = queue.peek();
                if(now[1] <= time){
                    queue.poll();
                    poll++;
                }
            }

            // 최대 인원수 갱신
            max = Math.max(max, queue.size());
            time++;

            // 마지막 손님까지 입장 > while문 탈출
            if(cnt >= order.length){
                break;
            }
        }

        return max;
    }
}
