package just.p_0826;

import java.util.*;

/**
 * title : 토끼가 정보섬에 올라온 이유
 * date : 2025-08-28
 */
public class boj17130 {
    public static void main(String[] args) {
        // 오른쪽으로 계속 이동 가능
        // R토끼 C당근 O쪽문 .빈공간 #벽
        // 각 칸에 도달하기 위해 3방향에서 올 수 있음
        // 벽, 격자초과, 토끼 초기위치 고려해야함

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        char[][] map = new char[N][M];
        int[] rabbit = new int[2];
        for (int i = 0; i < N; i++) {
            map[i] = sc.next().toCharArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'R') {
                    rabbit[0] = i;
                    rabbit[1] = j;
                }
            }
        }

        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);  // ** 도달 불가능
        }

        dp[rabbit[0]][rabbit[1]] = 0;

        int[] di = {-1, 0, 1};
        int maxSize = Integer.MIN_VALUE;
        for (int j = rabbit[1]; j < M - 1; j++) {
            for (int i = 0; i < N; i++) {
                if (dp[i][j] == -1) continue;

                for (int d = 0; d < 3; d++) {
                    int ni = i + di[d];
                    int nj = j + 1;

                    if (ni >= 0 && ni < N && map[ni][nj] != '#') {
                        int carrot = (map[ni][nj] == 'C') ? 1 : 0;
                        dp[ni][nj] = Math.max(dp[ni][nj], dp[i][j] + carrot);

                        if (map[ni][nj] == 'O') maxSize = Math.max(maxSize, dp[ni][nj]);
                    }
                }
            }
        }
        System.out.println((maxSize == Integer.MIN_VALUE) ? -1 : maxSize);
    }
}
