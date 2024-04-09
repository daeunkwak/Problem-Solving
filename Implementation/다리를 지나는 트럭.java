"""
author : https://github.com/daeunkwak
date : 2024-04-09
title : 다리를 지나는 트럭
"""

import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;

        Queue<Integer> queue = new LinkedList<>();
        int sum = 0; // 다리에 올라간 트럭 무게합

        for(int i = 0; i < truck_weights.length; i++){

            int truck = truck_weights[i];

            while(true) {

                // queue 비어있음 > 트럭 추가
                if(queue.isEmpty()) {
                    queue.add(truck);
                    sum += truck;
                    answer++;
                    break;

                    // queue 가득참 > 맨 앞 트럭 도착
                } else if(queue.size() == bridge_length) {
                    sum -= queue.poll();

                    // queue 빈공간 있음
                } else  {

                    // 무게 감당 가능한 경우
                    if(sum + truck <= weight) {
                        queue.add(truck);
                        sum += truck;
                        answer++;
                        break;

                        // 감당 불가 > 0 채워넣기
                    } else {
                        queue.add(0);
                        answer++;
                    }
                }
            }

        }

        return answer + bridge_length;
    }
}
