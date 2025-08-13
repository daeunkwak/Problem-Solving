import java.util.*;

/**
 * title : Unique Paths
 * date : 2025-08-12
 */
class Solution {
    public int uniquePaths(int m, int n) {
        // [0,0]에서 로봇 출발 -> [m-1][n-1] 도착
        // 로봇이 [m-1][n-1]에 도착하는 유니크한 경로 개수 구하기
        // 한 번 이동할 때 down or right만 가능

        // 각 칸까지 도달하는 유니크한 경로의 수 저장
        int[][] dp = new int[m][n];
        dp[0][0] = 1;

        // 현재 위치 기준으로 왼쪽, 위쪽에서 올 수 있음
        // 근데 모든 칸에서 ?? -> 아님. 테두리들은 왼쪽 or 위쪽에서만 계속 올 수 있음 -> 따라서 먼저 1로 싹 채워주기
        // x축, y축 전부 초기화
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // m : 세로길이, n : 가로길이
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}