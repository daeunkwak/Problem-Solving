"""
author : https://github.com/daeunkwak
date : 2024-03-19
title : 네트워크
"""

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(computers, i, visited);
                answer++;  // dfs 한 번 호출 > 네트워크 1개 검출
            }
        }

        return answer;
    }

    boolean[] dfs(int[][] computers, int i, boolean[] visited) {
        visited[i] = true;

        for (int j = 0; j < computers.length; j++) {
            // 자기자신 아니고 && 연결되어있는데 && 아직 미방문
            if (i != j && computers[i][j] == 1 && visited[j] == false) {
                visited = dfs(computers, j, visited);
            }
        }
        return visited;
    }
}