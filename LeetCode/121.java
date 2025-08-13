
/**
 * title : Best Time to Buy and Sell Stock
 * date : 2025-08-10
 */
class Solution {
    public int maxProfit(int[] prices) {
        // 7 1 5 3 6 4
        // 7 -> 1 (x)
        // 1 -> 5 (가능)
        // 1 -> 3 (가능, 갱신x)
        // 1 -> 6 (가능, 갱신o)

        // 1 2 4 2 5 7 2 4 9 0 9

        int start = 0;
        int end = 1;
        int profit = 0;

        for (int i = 1; i < prices.length; i++) {
            int diff = prices[end] - prices[start];
            if (diff <= 0) {
                // *****
                start = end;
                end++;
            } else {
                if (profit < diff) {
                    profit = diff;
                }
                end++;
            }
        }

        return profit;
    }
}