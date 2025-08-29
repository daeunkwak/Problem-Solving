
/**
 * title : Best Time to Buy and Sell Stock with Cooldown
 * date : 2025-08-23
 */
class Solution {
    public int maxProfit(int[] prices) {
        // buy -> sell -> cooldown
        // sell -> cooldown one day

        // 1 2 3 0 2
        // 완탐) n일차에 buy -> m일차에 sell -> k일동안 cooldown -> 다시 buy -> .. 계산불가

        // ith day : buy, sell, cooldown(최소 1일), buy하고 안팔기
        // 필요한 것 : 누적 profit, 직전 buy price
        // dp[n][3][2] -> 0 : buy, 1 : sell, 2 : cooldown

        // dp[n][0] = max (dp[n-1][1][0], dp[n-1][2][0])
        // dp[n][1] = max (dp[n-1][0][0] + profit, dp[n-1][3][0] + profit)
        // dp[n][2] = max (dp[n-1][1], dp[n-1][2])

        // 3차원일 필요가 있을까 ??? price를 바로 빼고 더하면 될 듯
        // dp[n][0] = max (이전 buy hold, cooldown - price[n])
        // dp[n][1] = (이전 buy sell)
        // dp[n][2] = max (이전 sell hold, 이전 cooldown hold)

        int[][] dp = new int[prices.length][3];

        // 어차피 i-1값 기준으로 계산 -> 초기화 필요 없음
//        for (int i = 0; i < prices.length; i++) {
//            Arrays.fill(dp[i], Integer.MIN_VALUE);
//        }

        dp[0][0] = prices[0] * -1;
        dp[0][1] = 0;
        dp[0][2] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }

        return Math.max(dp[prices.length - 1][1], dp[prices.length - 1][2]);

        // 1 2 3 0 2
        // (-1, 0, 0) (-1, 1, 0) (-1, 2, 1) (1, -1, 2) (1, 3, 2) -> max(dp[i][1], dp[i][2]) -> 3
    }
}