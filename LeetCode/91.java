
/**
 * title : Decode Ways
 * date : 2025-08-22
 */
class Solution {
    public int numDecodings(String s) {
        // 디코딩 가능한 조합 개수 구하기 (1 ~ 26)

        // 완탐 -> 경우의수가 나뉘는 경우 재귀 -> 최악 O(2^100)
        // 1 -> 11 or  1, 1 -> 11 + 1 or 1 + 11 or 1 + 1 + 1 ....

        // 특정 숫자까지 decode -> 1개단위 / 이전 숫자랑 2개단위
        // dp[][] i번째 숫자가 1개단위 / 2개단위로 묶이는 경우의수 저장

        // 11106
        // dp[0][0] = 1, dp[0][1] = 0
        // dp[1][0] = dp[0][0] + 1, dp[1][1] = 1

        // 묶이는 조건 확인 -> dp갱신
        // dp[2][0] = dp[1][0] + dp[1][1], dp[2][1] = dp[0][0] + dp[0][1]
        // (2, 1)
        // dp[3][0] = 2 + 1 = 3, dp[3][1] = 1 + 1 = 2

        if (s.charAt(0) == '0') return 0;
        if (s.length() == 1) return 1;

        int[][] dp = new int[s.length()][2];
        dp[0][0] = 1;
        dp[0][1] = 0;

        dp[1][0] = (s.charAt(1) == '0') ? 0 : 1;
        if ((s.charAt(0) == '1') || (s.charAt(0) == '2' && s.charAt(1) - '0' <= 6)) {
            dp[1][1] = 1;
        }

        for (int i = 2; i < s.length(); i++) {
            dp[i][0] = (s.charAt(i) == '0') ? 0 : dp[i - 1][0] + dp[i - 1][1];

            if ((s.charAt(i - 1) == '1') || (s.charAt(i - 1) == '2' && s.charAt(i) - '0' <= 6)) {
                dp[i][1] = dp[i - 2][0] + dp[i - 2][1];
            }
        }

        return (dp[s.length() - 1][0] + dp[s.length() - 1][1]);
    }
}