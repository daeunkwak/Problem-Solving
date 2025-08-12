package just.p_0812;

import java.util.*;

/**
 * title : 보물섬
 * date : 2025-08-12
 */
public class boj2589 {
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) {
        // 육지 찾기 -> 육지 내에서 가장 먼 육지 두 곳 찾기
        // bfs -> 육지 찾고 탐색 시작점, 끝점 List<List<Integer>>에 저장
        // 최단거리 시간 갱신하면서 보물 찾기
        // -> 이렇게 풀면 틀리고 모든 육지를 다 검사해야함

        Scanner sc = new Scanner(System.in);
        int Y = sc.nextInt();
        int X = sc.nextInt();

        char[][] map = new char[Y][X];
        for (int i = 0; i < Y; i++) {
            map[i] = sc.next().toCharArray();
        }

        boolean[][] visited = new boolean[Y][X];
        Queue<int[]> queue = new LinkedList<>();
        List<List<int[]>> islands = new ArrayList<>();

        int maxDistance = 0;

        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                if (map[i][j] == 'L') {
                    int distance = bfs(map, i, j, Y, X);
                    maxDistance = Math.max(maxDistance, distance);
                }
            }
        }

        System.out.println(maxDistance);
    }

    static int bfs(char[][] map, int startY, int startX, int Y, int X) {
        boolean[][] visited = new boolean[Y][X];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{startY, startX, 0}); // [y, x, distance]
        visited[startY][startX] = true;

        int maxDist = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];
            int dist = cur[2];

            maxDist = Math.max(maxDist, dist);

            for (int d = 0; d < 4; d++) {
                int ny = y + dy[d];
                int nx = x + dx[d];

                if (nx >= 0 && nx < X && ny >= 0 && ny < Y) {
                    if (!visited[ny][nx] && map[ny][nx] == 'L') {
                        queue.offer(new int[]{ny, nx, dist + 1});
                        visited[ny][nx] = true;
                    }
                }
            }
        }

        return maxDist;
    }
}
