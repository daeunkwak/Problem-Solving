
import java.util.*;

/**
 * title : 개근상
 * date : 2025-07-21
 */
public class BOJ_1563 {
    static int MOD = 1000000;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[][][] dp = new int[N + 1][2][3];
        dp[0][0][0] = 1;

        // N일 동안
        for (int i = 1; i <= N; i++) {

            // 지각이 0개 또는 1개일 때
            for (int l = 0; l < 2; l++) {

                // 연속 결석이 0회 또는 1회 또는 2회일 때
                for (int a = 0; a < 3; a++) {
                    dp[i][l][0] = (dp[i][l][0] + dp[i - 1][l][a]) % MOD;    // ** 출석 > 연속결석 초기화

                    if (l == 0) {
                        dp[i][l + 1][0] = (dp[i][l + 1][0] + dp[i - 1][l][a]) % MOD;  // 지각 > 연속결석 초기화
                    }

                    if (a < 2) {
                        dp[i][l][a + 1] = (dp[i][l][a + 1] + dp[i - 1][l][a]) % MOD;  // 결석
                    }
                }
            }
        }

        int sum = 0;
        for (int l = 0; l < 2; l++) {
            for (int a = 0; a < 3; a++) {
                sum = (sum + dp[N][l][a]) % MOD;
            }
        }
        System.out.println(sum);

    }
}
