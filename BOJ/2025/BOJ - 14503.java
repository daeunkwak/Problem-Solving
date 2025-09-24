package just.samsung;

import java.util.*;

/**
 * title : 로봇 청소기
 * date : 2025-09-22
 */
public class boj14503 {
    public static void main(String[] args) {
        // 청소X > 현재 칸 청소
        // 주변 4칸 모두 청소 > 한 칸 후진 or 후진못하면 멈추기
        // 청소X 주변칸 있으면 > 반시계 90도 회전 > 한 칸 전진
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        int r = sc.nextInt();   // 현재 y
        int c = sc.nextInt();   // 현재 x
        int d = sc.nextInt();   // 방향

        // 0북 1동 2남 3서 ******
        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};

        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 1: 벽, 0: 청소
//        Queue<int[]> queue = new LinkedList<>();
//        queue.add(new int[]{r, c});

        int cnt = 0;
        while (true) {
            // 현재 칸 청소
            if (map[r][c] == 0) {
                map[r][c] = 2;
                cnt++;
            }

            boolean flag = false;
            for (int i = 0; i < 4; i++) {
                int nd = (d + 3) % 4;
                int nx = c + dx[nd];
                int ny = r + dy[nd];

                // 청소전인 빈칸 찾기
                if (nx >= 0 && nx < M && ny >= 0 && ny < N && map[ny][nx] == 0) {
                    d = nd;
                    c = nx;
                    r = ny;
                    flag = true;
                    break;
                } else {
                    d = nd;
                }
            }

            // 후진해야 하는 경우
            if (!flag) {
                int backD = (d + 2) % 4;
                int backX = c + dx[backD];
                int backY = r + dy[backD];

                if (backX >= 0 && backX < M && backY >= 0 && backY < N && map[backY][backX] != 1) {
                    c = backX;
                    r = backY;
                } else {
                    break;
                }
            }
        }

        System.out.println(cnt);
    }
}
