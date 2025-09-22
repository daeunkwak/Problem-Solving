package just.samsung;

import java.util.*;

/**
 * title : 연구소
 * date : 2025-09-22
 */
public class boj14502 {
    private static List<int[][]> combs;
    public static void main(String[] args) {
        // 안전 영역 크기의 최댓값 구하기
        // 0: 빈칸, 1: 벽, 2: 바이러스
        // 완탐이 가장 좋아보임
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();  // 세로
        int M = sc.nextInt();  // 가로

        int[][] map = new int[N][M];
        List<int[]> empty = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
                if (map[i][j] == 0) empty.add(new int[]{i, j});
            }
        }

        // DFS로 벽 3개 세울 빈공간 조합 구하기
        combs = new ArrayList<>();
        int[][] cur = new int[3][2];
        dfs(0, 0, cur, empty);

        // 모든 조합에 대해 안전 영역 크기 구하기
        int maxArea = 0;
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};

        for (int[][] comb : combs) {
            // 벽으로 만들기
            // int[][] clone = map.clone();
            // *** 깊은 복사
            int[][] clone = new int[N][M];
            for (int i = 0; i < N; i++) {
                clone[i] = map[i].clone();
            }

            for (int[] area : comb) {
                int y = area[0];
                int x = area[1];
                clone[y][x] = 1;
            }

            // BFS로 영역 구하기
            int virus = 0;
            int wall = 0;
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[N][M];

            // 초기 바이러스 추가
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (clone[i][j] == 2) {
                        queue.offer(new int[]{i, j});
                        visited[i][j] = true;
                    }
                    if (clone[i][j] == 1) wall++;
                }
            }

            while (!queue.isEmpty()) {
                int[] spot = queue.poll();
                int cX = spot[1];
                int cY = spot[0];
                virus++;

                for (int d = 0; d < 4; d++) {
                    int nX = cX + dx[d];
                    int nY = cY + dy[d];
                    if (nX >= 0 && nX < M && nY >= 0 && nY < N
                            && clone[nY][nX] == 0 && !visited[nY][nX]) {
                        clone[nY][nX] = 2;
                        queue.offer(new int[]{nY, nX});
                        visited[nY][nX] = true;
                    }
                }
            }
            int safe = (N * M) - virus - wall;
            maxArea = Math.max(maxArea, safe);
        }

        System.out.println(maxArea);
    }

    private static void dfs(int depth, int start, int[][] cur, List<int[]> empty) {

        if (depth == 3) {
            // *** 깊은 복사
            int[][] copy = new int[3][2];
            for (int i = 0; i < 3; i++) {
                copy[i][0] = cur[i][0];
                copy[i][1] = cur[i][1];
            }
            combs.add(copy);
            return;
        }

        // 복사하니까 백트래킹 필요 x
        for (int i = start; i < empty.size(); i++) {
            cur[depth][0] = empty.get(i)[0];
            cur[depth][1] = empty.get(i)[1];
            dfs(depth + 1, i + 1, cur, empty);
        }

    }
}
