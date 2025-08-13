import java.util.*;

/**
 * title : House Robber
 * date : 2025-08-11
 */
class Solution {
    public int rob(int[] nums) {
        // 각 집엔 돈이 쌓여있음
        // 경찰 -> 같은 날 인접한 두 개의 집은 안 됨
        // 배열엔 집마다 쌓인 돈의 양 -> 경찰 없이 훔치는 돈 max

        // 선택지가 훔치기 / 건너뛰기
        // [훔쳤을 때 최대, 건너뛰었을 때 최대]
        int[][] dp = new int[nums.length][2];
        dp[0][0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            // 훔칠거면 -> 이전 집을 건너뛰어야함
            dp[i][0] = dp[i - 1][1] + nums[i];
            // 건너뛸거면 -> (이전 집에서 훔쳐도 되고, 안훔쳐도 됨)
            dp[i][1] = Math.max(dp[i - 1][0], dp[i - 1][1]);
        }

        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }
}