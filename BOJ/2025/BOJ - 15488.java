package just.p_0901;

import java.util.*;

/**
 * title : 나이트가 체스판을 벗어나지 않을 확률
 * date : 2025-09-02
 */
public class boj15488 {
    public static void main(String[] args) {
        // K번 이동한 후 체스판 위에 있을 확률
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int K = sc.nextInt();

        // K번 이동 -> bfs -> 밖으로 안나가는 경우의수 count -> 마지막에 확률 계산 -> 안됨
        int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
        int[] dy = {2, 1, -1, -2, -2, -1, 1, 2};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        int[] counts = new int[K];

        // dfs -> 8^K = 8^50 -> 안됨

        // dp -> K번 반복해서 도달 가능성 갱신 ?
        double[][][] dp = new double[N][N][K + 1];
        dp[x - 1][y - 1][0] = 1.0;

        for (int k = 0; k < K; k++) {
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (dp[r][c][k] > 0) {
                        for (int d = 0; d < 8; d++) {
                            int nr = r + dx[d];
                            int nc = c + dy[d];
                            if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
                                dp[nr][nc][k + 1] += dp[r][c][k] / 8.0;
                            }
                        }
                    }
                }
            }
        }

        double ans = 0.0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                ans += dp[r][c][K];
            }
        }

        System.out.printf("%.10f\n", ans);
    }
}
