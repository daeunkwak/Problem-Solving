package just.p_0822;

import java.util.*;

/**
 * title : 출근
 * date : 2025-08-22
 */
public class boj13903 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int R = sc.nextInt();
        int C = sc.nextInt();
        int[][] grid = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                grid[i][j] = sc.nextInt();
            }
        }

        int N = sc.nextInt();
        int[][] rules = new int[N][2];
        for (int i = 0; i < N; i++) {
            rules[i][0] = sc.nextInt(); // dr
            rules[i][1] = sc.nextInt(); // dc
        }

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];

        for (int i = 0; i < C; i++) {
            if (grid[0][i] == 1) {
                queue.add(new int[]{0, i});
                visited[0][i] = true;
            }
        }

        int cnt = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean found = false;

            for (int j = 0; j < size; j++) {
                int[] cur = queue.poll();
                int cR = cur[0];
                int cC = cur[1];

                if (cR == R - 1) {
                    System.out.println(cnt);
                    return;
                }

                for (int k = 0; k < N; k++) {
                    int nR = cR + rules[k][0];
                    int nC = cC + rules[k][1];

                    if (nR >= 0 && nR < R && nC >= 0 && nC < C) {
                        if (!visited[nR][nC] && grid[nR][nC] == 1) {
                            queue.add(new int[]{nR, nC});
                            visited[nR][nC] = true;
                        }
                    }
                }
            }
            cnt++;
        }

        System.out.println(-1);
    }
}
