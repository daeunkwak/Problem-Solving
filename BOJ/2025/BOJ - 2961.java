package just.p_0805;

import java.util.*;

/**
 * title : 도영이가 만든 맛있는 음식
 * date : 2025-08-05
 */
public class boj2961 {
    private static long minDiff;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[][] cooking = new int[N][2];
        for (int i = 0; i < N; i++) {
            cooking[i][0] = sc.nextInt();
            cooking[i][1] = sc.nextInt();
        }

        minDiff = Integer.MAX_VALUE;
        dfs(cooking, 0, N, 1, 0);
        System.out.println(minDiff);
    }

    private static void dfs(int[][] cooking, int start, int N, long curS, long curB) {
        if (start == N) {
            if (curB != 0) {
                minDiff = Math.min(minDiff, Math.abs(curS - curB));
            }
            return;
        }

        dfs(cooking, start + 1, N, curS * cooking[start][0], curB + cooking[start][1]);
        dfs(cooking, start + 1, N, curS, curB);
    }
}
