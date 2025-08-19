
/**
 * title : Best Time to Buy and Sell Stock
 * date : 2025-08-10
 */
class Solution {
    public int maxProfit(int[] prices) {
        // 샀다가 -> 판다
        // 가격 차이가 가장 큰 두 날 (작 -> 큰)

        // 이중for문 완탐 > O(n^2)

        // 7 1 5 3 6 4
        // buyPrice 기억 -> 이후 값들과 비교
        // buyPrice 더 작은 값이 나오는 순간 기억할 필요 x
        // -> O(n)

        int buyIdx = 0;
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < prices[buyIdx]) {
                buyIdx = i;
            }
            profit = Math.max(profit, prices[i] - prices[buyIdx]);
        }
        return profit;
    }
}