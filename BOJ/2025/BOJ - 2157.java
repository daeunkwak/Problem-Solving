package just.p_0801;

import java.util.*;

/**
 * title : 여행
 * date : 2025-08-03
 */
public class boj2157 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int K = sc.nextInt();

        int[][] roads = new int[K][3];
        for (int i = 0; i < K; i++) {
            roads[i][0] = sc.nextInt();
            roads[i][1] = sc.nextInt();
            roads[i][2] = sc.nextInt();
        }

        int[][] dp = new int[N + 1][M + 1];

        // *** 도달 불가능 표현 ***
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }
        dp[1][1] = 0;

        // 따로 만들 필요 없이 dp를 -1로 초기화해서 표현하면 됨
        // boolean[] available = new boolean[N + 1];
//        available[1] = true;
//        for (int i = 0; i < K; i++) {
//            if (roads[i][0] == 1) {
//                available[roads[i][1]] = true;
//            }
//        }

        Arrays.sort(roads, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            } else {
                return a[0] - b[0];
            }
        });

        for (int i = 0; i < K; i++) {
            int start = roads[i][0];
            int end = roads[i][1];
            int cost = roads[i][2];

            // 단방향 이동
            if (start >= end) continue;

            // 이래도 되나..?
            for (int j = 1; j < M; j++) {
                if (dp[start][j] != -1) {
                    dp[end][j + 1] = Math.max(dp[end][j + 1], dp[start][j] + cost);
                }
            }
        }

        int max = 0;
        for (int i = 1; i <= M; i++) {
            max = Math.max(max, dp[N][i]);
        }
        System.out.println(max);

    }
}
