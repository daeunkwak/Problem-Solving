import java.util.*;

/**
 * title : Unique Paths II
 * date : 2025-08-12
 */
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // down or right
        // 1 -> obstacle, 0 -> pass
        // grid[m-1][n-1]로 가는 유니크한 경로 개수

        int[][] dp = new int[obstacleGrid.length][obstacleGrid[0].length];
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == 0) {
                dp[i][0] = 1;
            } else {
                // 하나라도 장애물이 있으면 가로막힘
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 0) {
                dp[0][i] = 1;
            } else {
                break;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                // if (dp[i - 1][j] == '1') -> dp[i][j-1] 만 더해야함
                // 둘 다 장애물이면 더할 수 없음
                // if (obstacleGrid[i][j] == '1') -> pass
                if (obstacleGrid[i][j] == 1) {
                    continue;
                }
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}