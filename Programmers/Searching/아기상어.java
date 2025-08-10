"""
author : https://github.com/daeunkwak
date : 2024-07-21
title : BOJ) 아기 상어
"""

import java.util.*;

// 물고기 위치, 거리 객체 정의
class Fish {

    int x;
    int y;
    int dist;

    Fish(int x, int y, int dist) {
        this.x = x;
        this.y = y;
        this.dist = dist;
    }
}



public class 아기상어 {
    static final int dx[] = {0, 0, 1, -1};
    static final int dy[] = {1, -1, 0, 0};
    static int[][] map;   // 입력 배열 저장

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Queue<Fish> queue = new LinkedList<>();
        int n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        // 입력 배열 저장, 물고기 위치 queue에 push
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 아기상어 위치 찾기
                if (map[i][j] == 9) {
                    queue.add(new Fish(i, j, 0));
                    map[i][j] = 0;
                }
            }
        }

        int shark_size = 2; // 아기상어 사이즈
        int eat = 0;    // 물고기 먹은 횟수 (크기만큼 먹으면 초기화)
        int result = 0; // 정답

        while (true) {
            ArrayList<Fish> eatFish = new ArrayList<>();
            int[][] dist = new int[n][n];

            // BFS코드
            while (!queue.isEmpty()) {
                Fish fish = queue.poll();
                int x = fish.x;
                int y = fish.y;

                // 최단거리 구하기
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    // N x N 범위 내에 속하는 경우
                    if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                        // 아기상어보다 작거나 같은 경우 (지나갈 수 있는 경우)
                        if (dist[nx][ny] == 0 && map[nx][ny] <= shark_size) {
                            // 거리 +1, queue에 탐색 대상으로 넣기
                            dist[nx][ny] = dist[x][y] + 1;
                            queue.add(new Fish(nx, ny, dist[nx][ny]));
                            // 먹을 수 있는 물고기라면 지나가면서 먹는다
                            if (1 <= map[nx][ny] && map[nx][ny] <= 6 && map[nx][ny] < shark_size) {
                                eatFish.add(new Fish(nx, ny, dist[nx][ny]));
                            }
                        }
                    }
                }
            }

            if (eatFish.size() == 0) {
                System.out.println(result);
                return;
            }

            // 먹을 수 있는 물고기 중 가장 가까운 물고기 선택
            // 거리 -> 가장 위에있는(x) -> 가장 왼쪽(y)
            eatFish.sort(Comparator
                    .comparingInt((Fish f) -> f.dist)
                    .thenComparingInt(f -> f.x)
                    .thenComparingInt(f -> f.y));

            Fish nowFish = eatFish.get(0);


            // 먹은 물고기 위치 0으로 바꾸고 결과값 더하기
            map[nowFish.x][nowFish.y] = 0;
            result += nowFish.dist;
            eat++;

            // 아기상어 크기만큼 물고기를 먹음 > 사이즈 +1, 카운트 초기화
            if (shark_size == eat) {
                shark_size++;
                eat = 0;
            }

            queue.add(nowFish); // 현재 위치부터 다시 시작
        }


    }
}