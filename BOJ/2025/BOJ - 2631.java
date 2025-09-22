package just.p_0922;

import java.util.*;

/**
 * title : 줄세우기
 * date : 2025-09-22
 */
public class boj2631 {
    public static void main(String[] args) {
        // 정렬 최소횟수 구하기
        // -> 최장 증가 부분 수열 구하기

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] child = new int[N];
        for (int i = 0; i < N; i++) {
            child[i] = sc.nextInt();
        }

        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        int maxLength = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (child[j] < child[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    maxLength = Math.max(maxLength, dp[i]);
                }
            }
        }

        System.out.println(N - maxLength);
    }
}
