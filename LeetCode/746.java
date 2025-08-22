
/**
 * title : Min Cost Climbing Stairs
 * date : 2025-08-20
 */
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        // one or two steps 이전으로부터 -> 현재위치
        int[] dp = new int[cost.length + 1];
        dp[0] = cost[0];
        dp[1] = cost[1];

        // cost : 1 100 1 1 1 100 1 1 100 1
        // dp : 1 100 2 3 3 103 4 5 104 6
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 2], dp[i - 1]) + cost[i];
        }

        // *** 맨 끝 처리
        return Math.min(dp[cost.length - 1], dp[cost.length - 2]);
    }
}