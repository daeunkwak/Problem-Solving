import java.util.*;

/**
 * title : 경주로 건설
 * date : 2025-10-10
 */
class Solution {
    // 상 하 좌 우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int n;
    public int solution(int[][] board) {
        n = board.length;

        // r, c, 방향, 현재까지 비용
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[board.length][board[0].length];
        int[][][] minBoard = new int[n][n][4];  // **** [r][c][방향]

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(minBoard[i][j], Integer.MAX_VALUE);
            }
        }

        queue.offer(new int[]{0, 0, -1, 0});  // 시작은 방향 -1
        visited[0][0] = true;

        int minCost = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cR = cur[0];
            int cC = cur[1];
            int direc = cur[2];
            int cost = cur[3];

            if (cR == n - 1 && cC == n - 1) {
                minCost = Math.min(minCost, cost);
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int nR = cR + dr[d];
                int nC = cC + dc[d];

                if (isBound(nR, nC) && board[nR][nC] == 0) {
                    int newCost;
                    if (direc == -1) {
                        newCost = 100;
                    } else if (direc == d) {
                        newCost = cost + 100;
                    } else {
                        newCost = cost + 600;
                    }

                    if (newCost < minBoard[nR][nC][d]) {  // 해당 방향의 최소값과 비교
                        queue.offer(new int[]{nR, nC, d, newCost});
                        minBoard[nR][nC][d] = newCost;
                    }
                    visited[nR][nC] = true;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(Arrays.stream(minBoard[i][j]).min().getAsInt());
                sb.append(" ");
            }
            System.out.println(sb.toString());
        }

        return minCost;
    }

    private static boolean isBound(int r, int c) {
        return (r >= 0 && r < n && c >= 0 && c < n);
    }
}