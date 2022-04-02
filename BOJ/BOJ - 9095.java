/**
 * author : Kwak Daeun
 * github : https://github.com/daeunkwak
 *
 * title : 1, 2, 3 더하기
 * description : 다이나믹 프로그래밍
 */

import java.util.Scanner;

public class BOJ_9095 {
    public static void main(String[] args) {
        int dp[] = new int[11];

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int j = 4; j <= 10; j++) { // 4부터 반복
            dp[j] = dp[j - 3] + dp[j - 2] + dp[j - 1]; // 점화식
        }

        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();

            System.out.println(dp[n]);

        }
    }
}
