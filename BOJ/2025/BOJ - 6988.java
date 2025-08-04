
import java.util.*;

/**
 * title : 타일 밟기
 * date : 2025-08-03
 */
public class boj6988 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] tiles = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            tiles[i] = sc.nextInt();
        }

        Map<Integer, Long>[] dp = new HashMap[N + 1];
        for (int i = 1; i <= N; i++) {
            Map<Integer, Long> map = new HashMap<>();
            dp[i] = map;
        }

        long max = 0;
        for (int i = 2; i <= N; i++) {
            for (int j = i - 1; j >= 1; j--) {
                int diff = tiles[i] - tiles[j];

                // 현재 타일의 이전 타일이 null인 경우 -> 이어주면 2개 -> max변수 갱신 불가
                if (dp[j].get(diff) == null) {
                    dp[i].put(diff, (long) tiles[j] + tiles[i]);
                } else {
                    // 현재 타일의 이전 타일이 null이 아닌 경우 -> 이어주면 3개 이상 -> max변수 갱신해주기
                    long v = dp[j].get(diff) + tiles[i];
                    dp[i].put(diff, v);
                    max = Math.max(max, v);
                }
            }
        }

        System.out.println(max);
    }
}
