"""
author : https://github.com/daeunkwak
date : 2024-03-27
title : 게임 맵 최단거리
"""

import java.util.*;

class Solution {

    int[] dx = {1, 0, -1, 0};
    int[] dy = {0, 1, 0, -1};
    int cnt = 0;
    public int solution(int[][] maps) {
        int answer = 0;

        int[][] visited = new int[maps.length][maps[0].length];
        bfs(maps, visited);

        answer = visited[maps.length - 1][maps[0].length - 1];

        return (answer>0) ? answer : -1 ;
    }

    public void bfs(int[][] maps, int[][] visited){
        int x = 0;
        int y = 0; // 현재 위치
        visited[x][y] = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while(!queue.isEmpty()){
            int[] current = queue.poll();
            int cX = current[0];
            int cY = current[1];

            for(int i = 0; i < 4; i++){
                int nX = cX + dx[i];
                int nY = cY + dy[i];

                // 여기 똑바로 해라
                if(nX < 0 || nY < 0 || nX >= maps.length || nY >= maps[0].length){
                    continue;
                }
                // 방문 안했고 && 갈 수 있는 길일 떄
                if(visited[nX][nY] == 0 && maps[nX][nY] == 1){
                    visited[nX][nY] = visited[cX][cY] + 1;
                    queue.add(new int[]{nX, nY});
                }
            }

        }
    }

}