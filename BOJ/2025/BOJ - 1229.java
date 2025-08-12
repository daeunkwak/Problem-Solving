package just.p_0812;

import java.util.*;

/**
 * title : 육각수
 * date : 2025-08-12
 */
public class boj1229 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        // n보다 작은 육각수 배열 저장
        // dp로 육각수 조합해서 n만들기

        // 육각수i = 육각수i-1 + (i * 4) - 3
        int[] hexNums = new int[146849];
        hexNums[0] = 0;
        hexNums[1] = 1;

        int hexIdx = 0;
        for (int i = 2; i < hexNums.length; i++) {
            hexNums[i] = hexNums[i - 1] + (i * 4) - 3;
            if (hexNums[i] > N) {
                hexIdx = i - 1;
                break;
            }
        }

        // 최소 육각수 개수 저장
        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 1; i <= hexIdx; i++) {
            for (int j = hexNums[i]; j <= N; j++) {
                if (dp[j - hexNums[i]] != Integer.MAX_VALUE) {
                    dp[j] = Math.min(dp[j], dp[j - hexNums[i]] + 1);
                }
            }
        }

        System.out.println(dp[N]);
    }
}
