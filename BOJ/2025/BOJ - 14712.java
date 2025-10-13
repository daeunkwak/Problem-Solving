package just.p_1013;

import java.util.*;

/**
 * title : 넴모넴모(Easy)
 * date : 2025-10-13
 */
public class boj14712 {
    static int cnt;
    static int N;
    static int M;
    static boolean[][] map;
    public static void main(String[] args) {
        // 2x2 사각형 없는 모든 배치의 가짓수

        Scanner sc= new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        cnt = 0;

        // true면 네모
        map = new boolean[N][M];

        search(0, 0);

        System.out.println(cnt);
    }

    private static void search(int r, int c) {
        if (r == N) {
            cnt++;
            return;
        }

        int nextR = (c == M - 1) ? r + 1 : r;
        int nextC = (c == M - 1) ? 0 : c + 1;

        search(nextR, nextC);

        map[r][c] = true;
        if (isNotNemo(r, c)) {
            search(nextR, nextC);
        }
        map[r][c] = false;
    }

    private static boolean isNotNemo(int r, int c) {
        int nemo = 0;

        if (map[r][c]) nemo++;
        if (isBound(r, c - 1) && map[r][c - 1]) nemo++;
        if (isBound(r - 1, c) && map[r - 1][c]) nemo++;
        if (isBound(r - 1, c - 1) && map[r - 1][c - 1]) nemo++;

        return (nemo <= 3);
    }

    private static boolean isBound(int r, int c) {
        return (r >= 0 && r < N && c >= 0 && c < M);
    }
}
