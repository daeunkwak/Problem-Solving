package just.p_0814;

import java.util.*;

/**
 * title : 불!
 * date : 2025-08-14
 */
public class boj4179 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int R = sc.nextInt();
        int C = sc.nextInt();

        char[][] map = new char[R][C];
        List<int[]> fired = new ArrayList<>();
        int[] start = new int[2];

        for (int i = 0; i < R; i++) {
            String line = sc.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'F') {
                    fired.add(new int[]{i, j});
                } else if (map[i][j] == 'J') {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        // 불 확산 -> 지훈 이동
        boolean[][] fireVisited = new boolean[R][C];
        Queue<int[]> fireQueue = new LinkedList<>();
        for (int[] fire : fired) {
            fireQueue.add(fire);
            fireVisited[fire[0]][fire[1]] = true;
        }

        boolean[][] visited = new boolean[R][C];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;

        int cnt = 0;
        if (start[0] == 0 || start[0] == R - 1 || start[1] == 0 || start[1] == C - 1) {
            System.out.println(1);
            return;
        }

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        while (true) {
            cnt++;

            // 불 이동
            int size = fireQueue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = fireQueue.poll();
                for (int d = 0; d < 4; d++) {
                    int nY = cur[0] + dy[d];
                    int nX = cur[1] + dx[d];

                    if (nY >= 0 && nY <= R - 1 && nX >= 0 && nX <= C - 1
                            && !fireVisited[nY][nX] && map[nY][nX] != '#') {
                        fireQueue.add(new int[]{nY, nX});
                        fireVisited[nY][nX] = true;
                    }
                }
            }

            // 지훈 이동
            size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cur = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int nY = cur[0] + dy[d];
                    int nX = cur[1] + dx[d];

                    // 탈출
                    if (nY < 0 || nY >= R || nX < 0 || nX >= C) {
                        System.out.println(cnt);
                        return;
                    }

                    // *** 이런 조건 틀리지 않게 조심
                    if (nY >= 0 && nY <= R - 1 && nX >= 0 && nX <= C - 1
                            && !visited[nY][nX] && map[nY][nX] == '.' && !fireVisited[nY][nX]) {
                        queue.add(new int[]{nY, nX});
                        visited[nY][nX] = true;
                    }
                }
            }

            // 더이상 갈 곳이 없으면
            if (queue.isEmpty()) {
                System.out.println("IMPOSSIBLE");
                return;
            }
        }
    }
}
