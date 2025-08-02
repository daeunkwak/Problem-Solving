
import java.util.*;

/**
 * title : 지름길
 * date : 2025-08-02
 */
public class boj1446 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int D = sc.nextInt();

        Map<Integer, List<int[]>> shortcuts = new HashMap<>();

        for (int i = 0; i < N; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int length = sc.nextInt();
            if (end <= D) {
                if (shortcuts.containsKey(start)) {
                    shortcuts.get(start).add(new int[]{end, length});
                } else {
                    List<int[]> list = new ArrayList<>();
                    list.add(new int[]{end, length});
                    shortcuts.put(start, list);
                }
            }
        }

        int[] dp = new int[D + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < D; i++) {
            dp[i + 1] = Math.min(dp[i] + 1, dp[i + 1]);

            // 지름길이 있다면 같이 갱신
            if (shortcuts.containsKey(i)) {
                for (int[] shortcut : shortcuts.get(i)) {
                    // System.out.println("지름길 : " + i + ' ' + shortcut[0] + " " + shortcut[1]);
                    dp[shortcut[0]] = Math.min(dp[shortcut[0]], dp[i] + shortcut[1]);
                }
            }
        }

        System.out.println(dp[D]);
    }
}
