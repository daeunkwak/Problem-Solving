package just.p_0815;

import java.util.*;

/**
 * title : 동전 뒤집기
 * date : 2025-08-15
 */
public class boj1285 {
    static char [][] board;
    static int N;
    static int minCnt;
    public static void main(String[] args) {

        // 우선순위큐 > T개수 많은 줄부터 뒤집기
        // 특정 줄을 뒤집어서 T개수가 변한 줄 > (T >= H)인 경우 Queue 탐색 대상
        // 종료 조건은 어떻게 설정하지

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        board = new char[N][N];

        for (int i = 0; i < N; i++) {
            String line = sc.next();
            for (int j = 0; j < N; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        minCnt = Integer.MAX_VALUE;
        dfs(0, N);
        System.out.println(minCnt);
    }

    private static void dfs(int idx, int N) {
        if (idx == N) {
            minCnt = Math.min(minCnt, countMin());
            return;
        }

        dfs(idx + 1, N);

        flipRow(idx, N);
        dfs(idx + 1, N);
        flipRow(idx, N);
    }

    static int countMin() {
        int sum = 0;
        for (int c = 0; c < N; c++) {
            int tails = 0;
            for (int r = 0; r < N; r++) {
                if (board[r][c] == 'T') {
                    tails++;
                }
            }
            // *** 열 그대로 두기 / 뒤집기 비교 ??
            sum += Math.min(tails, N - tails);
        }
        return sum;
    }

    private static void flipRow(int r, int N) {
        for (int c = 0; c < N; c++) {
            if (board[r][c] == 'T') {
                board[r][c] = 'H';
            } else {
                board[r][c] = 'T';
            }
        }
    }
}
