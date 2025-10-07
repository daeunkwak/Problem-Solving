import java.util.*;

/**
 * title : 블록 이동하기
 * date : 2025-10-07
 */
class Solution {
    static int size;
    public int solution(int[][] board) {
        int answer = 0;
        size = board.length;

        // 한 칸 이동 or 90도 회전 = 1초
        // 이동하는데 필요한 최소 시간 구하기

        // BFS > 현재 위치에서 그냥 이동할 수 있는 경우, 회전할 수 있는 경우 모두 넣기 > offer 로봇의 좌표 두 칸
        // y좌표 작은 순 > x좌표 작은 순으로 넣기
        Queue<int[][]> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        int[][] first = new int[][]{{0, 0}, {0, 1}};
        queue.offer(first);
        visited.add(0 + " " + 0 + " " + 0 + " " + 1);

        int cnt = 0;
        while (!queue.isEmpty()) {

            int qsize = queue.size();
            for (int i = 0; i < qsize; i++) {
                int[][] cur = queue.poll();
                int r1 = cur[0][0];
                int c1 = cur[0][1];
                int r2 = cur[1][0];
                int c2 = cur[1][1];

                // 도착
                if ((r1 == size - 1 && c1 == size - 1) || (r2 == size - 1 && c2 == size - 1)) {
                    return cnt;
                }

                // 상
                int[][] moved = new int[][]{{r1 - 1, c1}, {r2 - 1, c2}};
                String movedStr = (r1 - 1) + " " + c1 + " " + (r2 - 1) + " " + c2;
                if (isBound(r1 - 1, c1, r2 - 1, c2) && board[r1 - 1][c1] == 0 && board[r2 - 1][c2] == 0 && !visited.contains(movedStr)) {
                    queue.offer(moved);
                    visited.add(movedStr);
                }

                // 하
                moved = new int[][]{{r1 + 1, c1}, {r2 + 1, c2}};
                movedStr = (r1 + 1) + " " + c1 + " " + (r2 + 1) + " " + c2;
                if (isBound(r1 + 1, c1, r2 + 1, c2) && board[r1 + 1][c1] == 0 && board[r2 + 1][c2] == 0 && !visited.contains(movedStr)) {
                    queue.offer(moved);
                    visited.add(movedStr);
                }

                // 좌
                moved = new int[][]{{r1, c1 - 1}, {r2, c2 - 1}};
                movedStr = (r1) + " " + (c1 - 1) + " " + (r2) + " " + (c2 - 1);
                if (isBound(r1, c1 - 1, r2, c2 - 1) && board[r1][c1 - 1] == 0 && board[r2][c2 - 1] == 0 && !visited.contains(movedStr)) {
                    queue.offer(moved);
                    visited.add(movedStr);
                }

                // 우
                moved = new int[][]{{r1, c1 + 1}, {r2, c2 + 1}};
                movedStr = (r1) + " " + (c1 + 1) + " " + (r2) + " " + (c2 + 1);
                if (isBound(r1, c1 + 1, r2, c2 + 1) && board[r1][c1 + 1] == 0 && board[r2][c2 + 1] == 0 && !visited.contains(movedStr)) {
                    queue.offer(moved);
                    visited.add(movedStr);
                }

                // 회전
                if (r1 == r2) {    // 가로일 때
                    // 축1 : 위로 회전
                    moved = new int[][]{{r1, c1}, {r1 - 1, c1}};
                    movedStr = (r1) + " " + (c1) + " " + (r1 - 1) + " " + (c1);
                    if (isBound(r2 - 1, c2, r1 - 1, c1) && board[r2 - 1][c2] == 0 && board[r1 - 1][c1] == 0 && !visited.contains(movedStr)) {
                        queue.offer(moved);
                        visited.add(movedStr);
                    }

                    // 축1 : 아래로 회전
                    moved = new int[][]{{r1, c1}, {r1 + 1, c1}};
                    movedStr = (r1) + " " + (c1) + " " + (r1 + 1) + " " + (c1);
                    if (isBound(r2 + 1, c2, r1 + 1, c1) && board[r2 + 1][c2] == 0 && board[r1 + 1][c1] == 0 && !visited.contains(movedStr)) {
                        queue.offer(moved);
                        visited.add(movedStr);
                    }

                    // 축2 : 위로 회전
                    moved = new int[][]{{r2, c2}, {r2 - 1, c2}};
                    movedStr = (r2) + " " + (c2) + " " + (r2 - 1) + " " + (c2);
                    if (isBound(r1 - 1, c1, r2 - 1, c2) && board[r1 - 1][c1] == 0 && board[r2 - 1][c2] == 0 && !visited.contains(movedStr)) {
                        queue.offer(moved);
                        visited.add(movedStr);
                    }

                    // 축2 : 아래로 회전
                    moved = new int[][]{{r2, c2}, {r2 + 1, c2}};
                    movedStr = (r2) + " " + (c2) + " " + (r2 + 1) + " " + (c2);
                    if (isBound(r1 + 1, c1, r2 + 1, c2) && board[r1 + 1][c1] == 0 && board[r2 + 1][c2] == 0 && !visited.contains(movedStr)) {
                        queue.offer(moved);
                        visited.add(movedStr);
                    }
                } else {    // 세로일 때
                    // 축1 : 왼쪽 회전
                    moved = new int[][]{{r1, c1 - 1}, {r1, c1}};
                    movedStr = (r1) + " " + (c1 - 1) + " " + (r1) + " " + (c1);
                    if (isBound(r2, c2 - 1, r1, c1 - 1) && board[r2][c2 - 1] == 0 && board[r1][c1 - 1] == 0 && !visited.contains(movedStr)) {
                        queue.offer(moved);
                        visited.add(movedStr);
                    }

                    // 축1 : 오른쪽 회전
                    moved = new int[][]{{r1, c1}, {r1, c1 + 1}};
                    movedStr = (r1) + " " + (c1) + " " + (r1) + " " + (c1 + 1);
                    if (isBound(r2, c2 + 1, r1, c1 + 1) && board[r2][c2 + 1] == 0 && board[r1][c1 + 1] == 0 && !visited.contains(movedStr)) {
                        queue.offer(moved);
                        visited.add(movedStr);
                    }

                    // 축2 : 왼쪽 회전
                    moved = new int[][]{{r2, c2 - 1}, {r2, c2}};
                    movedStr = (r2) + " " + (c2 - 1) + " " + (r2) + " " + (c2);
                    if (isBound(r2, c2 - 1, r1, c1 - 1) && board[r2][c2 - 1] == 0 && board[r1][c1 - 1] == 0 && !visited.contains(movedStr)) {
                        queue.offer(moved);
                        visited.add(movedStr);
                    }

                    // 축2 : 오른쪽 회전
                    moved = new int[][]{{r2, c2}, {r2, c2 + 1}};
                    movedStr = (r2) + " " + (c2) + " " + (r2) + " " + (c2 + 1);
                    if (isBound(r2, c2 + 1, r1, c1 + 1) && board[r2][c2 + 1] == 0 && board[r1][c1 + 1] == 0 && !visited.contains(movedStr)) {
                        queue.offer(moved);
                        visited.add(movedStr);
                    }

                }
            }
            cnt++;
        }

        return -1;
    }

    private static boolean isBound(int r1, int c1, int r2, int c2) {
        return (r1 >= 0 && r1 < size && c1 >= 0 && c1 < size
                && r2 >= 0 && r2 < size && c2 >= 0 && c2 < size);
    }
}
