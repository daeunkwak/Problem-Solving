
import java.util.*;

/**
 * title : 수식 표현
 * date : 2025-07-31
 */
public class boj1955 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        // n을 만드는 최소 1의 개수 저장
        int[] dp = new int[10001];
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
        }
        dp[0] = 0;
//        if (n > 0) {
//            dp[1] = 1;
//        }

        int[] fac = new int[9];
        fac[0] = 1;
        for (int i = 1; i < 9; i++) {
            fac[i] = fac[i - 1] * i;
            if (fac[i] > n) break;
        }

//        // e! 채우기
//        long facto = 1;
//        for (int f = 1; f <= i; f++) {
//            facto *= f;
//            if (facto > n) break;
//        }
//
//        //  System.out.println("facto : " + facto + " i : " + i);
//        if (facto <= n && facto > 0) {
//            dp[(int) facto] = Math.min(dp[(int)facto], dp[i]);
//        }
        // }

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i], dp[i - 1] + 1);
            // 인수 찾기
            // System.out.println("------------------");
            List<int[]> find = new ArrayList<>();
            for (int j = 2; j <= i / 2; j++) {
                if (i % j == 0) {
                    find.add(new int[]{j, (i / j)});
                }
            }

            // 찾은 인수들 비교하기
            int minCnt = Integer.MAX_VALUE;
            for (int[] arr : find) {
                // System.out.println(arr[0] + " " + arr[1]);
                int add = dp[arr[0]] + dp[arr[1]];
                minCnt = Math.min(minCnt, add);
            }

            // dp 갱신
            dp[i] = Math.min(dp[i], minCnt);
//            dp[i] = Math.min(dp[i], dp[i - 1] + 1);
            for (int j = 1; j <= (i - 1); j++) {
                dp[i] = Math.min(dp[i], dp[i - j] + dp[j]);
            }

            for (int j = 2; j < 9; j++) {
                if (i == fac[j]) {
                    dp[i] = Math.min(dp[i], dp[j]);
                    //break;
                }
            }
        }
        System.out.println(dp[n]);
    }
}
