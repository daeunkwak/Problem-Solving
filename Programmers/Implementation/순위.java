"""
author : https://github.com/daeunkwak
date : 2024-04-09
title : 순위
"""

import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;

        char[][] graph = new char[n][n];
        // 모든 요소를 '0'으로 초기화
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = '0';
            }
        }

        // 승패관계 그래프에 넣기
        for(int i = 0; i < results.length; i++){
            graph[results[i][0] - 1][results[i][1] - 1] = 'W';
            graph[results[i][1] - 1][results[i][0] - 1] = 'L';
        }

        // 1은 2를 이긴다 -> 2는 3, 4를 이긴다 -> 1은 3, 4도 이긴다
        while(true){
            int flag = 0;     // 업데이트 여부 확인 변수
            for(int i = 0; i < n; i++){

                for(int j = 0; j < n; j++){

                    // ex) 1은 2를 이긴다
                    if(graph[i][j] != '0'){
                        char res = graph[i][j]; // W

                        // 2는 ??를 이긴다
                        List<Integer> wins = new ArrayList<>();
                        for(int k = 0; k < n; k++){
                            if(graph[j][k] == res){
                                wins.add(k);
                            }
                        }

                        // 따라서 1은 ??를 이긴다
                        for(int k : wins){
                            if(graph[i][k] != res){ // W, L 중 일치하는 결과만 저장
                                graph[i][k] = res;
                                flag = 1;
                            }
                        }

                    }
                }
            }
            // 더이상 업데이트할 승패관계가 없을 경우 종료
            if(flag == 0){
                break;
            }
        }

        for (int i = 0; i < n; i++) {
            int cnt = 0;
            for (int j = 0; j < n; j++) {
                if(i != j && graph[i][j] != '0'){
                    cnt++;
                }
            }
            if(cnt == (n - 1)){
                answer++;
            }
        }

        return answer;
    }

    // 0 W W L
    // W 0 W L
}
