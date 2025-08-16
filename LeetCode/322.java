
/**
 * title : Coin Change
 * date : 2025-08-16
 */
class Solution {
    public int coinChange(int[] coins, int amount) {
        // 가장 적은 동전 개수 반환
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }

        if (dp[amount] == amount + 1) {
            return -1;
        } else {
            return dp[amount];
        }
    }
}