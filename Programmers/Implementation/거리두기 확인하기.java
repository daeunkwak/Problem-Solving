"""
author : https://github.com/daeunkwak
date : 2024-05-19
title : 거리두기 확인하기
"""

import java.util.*;

class Solution {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean isAvailable = true;

    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        // 5x5 대기실 5개
        // 맨허튼 거리 2 이하 xx 파티션은 ㄱㅊ
        // 한명이라도 안지키면 0 리턴, 다 지키면 1

        // 25  x 25 x 25

        for(int i = 0; i < 5; i++){

            // 2차원 배열 형태로 변환
            char[][] graph = new char[5][5];
            for (int j=0; j<5; j++) {
                graph[j] = places[i][j].toCharArray();
            }

            isAvailable = true;

            for(int r = 0; r < 5; r++){
                for(int c = 0; c < 5; c++){
                    // 사람이 앉아있으면 탐색 시작
                    if(graph[r][c] == 'P'){
                        boolean[][] visited = new boolean[5][5];
                        dfs(0, r, c, visited, graph);
                        if(!isAvailable){
                            break;
                        }
                    }
                }
                if(!isAvailable){
                    break;
                }
            }
            if(isAvailable){
                answer[i] = 1;
            } else{
                answer[i] = 0;
            }
        }


        return answer;
    }

    static void dfs(int depth, int r, int c, boolean[][] visited, char[][] graph) {

        if (depth >= 2){
            return;
        }

        visited[r][c] = true;
        for (int i = 0; i < 4; i++) {
            int nr = r + dx[i];
            int nc = c + dy[i];

            // 범위 확인
            if (nr < 0 || nr >= 5 || nc < 0 || nc >= 5 || visited[nr][nc]){
                continue;
            }

            // 테이블인 경우 > 그 옆자리까지 탐색
            if (graph[nr][nc] == 'O') {
                dfs(depth + 1, nr, nc, visited, graph);
            }
            // 사람인 경우 > 거리두기 실패
            else if (graph[nr][nc] == 'P') {
                isAvailable = false;
                return;
            }
            // 벽인 경우 > 탐색 종료
            else if (graph[nr][nc] == 'X') {
                continue;
            }

        }
    }
}
