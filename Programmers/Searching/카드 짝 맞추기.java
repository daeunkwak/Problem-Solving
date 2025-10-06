import java.util.*;

/**
 * title : 카드 짝 맞추기
 * date : 2025-10-06
 */
class Solution {
    List<List<Integer>> perms;
    Set<Integer> cards;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int solution(int[][] board, int r, int c) {
        int answer = Integer.MAX_VALUE;

        Map<Integer, List<int[]>> map = new HashMap<>();
        cards = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 0) {
                    cards.add(board[i][j]);
                    map.computeIfAbsent(board[i][j], k -> new ArrayList<>()).add(new int[]{i, j});
                }
            }
        }

        // 시작점, 방문 순서에 따라 최소횟수가 달라짐 > 조합 다 구해놓고 순서대로 BFS
        // 두 카드 중에서 어딜 먼저 탐색할지 > 이것도 다 완탐 휴리스틱XX
        perms = new ArrayList<>();
        List<Integer> cardList = new ArrayList<>(cards);
        getPerms(cardList, new ArrayList<>());

        for (List<Integer> perm : perms) {
            int totalMoves = simulate(board, r, c, perm, map);
            answer = Math.min(answer, totalMoves);
        }

        return answer;
    }

    // 특정 순서로 카드를 제거할 때의 최소 이동 횟수 계산
    private int simulate(int[][] board, int startR, int startC, List<Integer> order, Map<Integer, List<int[]>> map) {
        int[][] tempBoard = copyBoard(board);
        int curR = startR;
        int curC = startC;
        int totalMoves = 0;

        for (int cardNum : order) {
            List<int[]> positions = map.get(cardNum);
            int[] pos1 = positions.get(0);
            int[] pos2 = positions.get(1);

            int move1 = bfs(tempBoard, curR, curC, pos1[0], pos1[1]);
            int move2 = bfs(tempBoard, pos1[0], pos1[1], pos2[0], pos2[1]);
            int case1 = move1 + move2 + 2;

            int move3 = bfs(tempBoard, curR, curC, pos2[0], pos2[1]);
            int move4 = bfs(tempBoard, pos2[0], pos2[1], pos1[0], pos1[1]);
            int case2 = move3 + move4 + 2;

            if (case1 <= case2) {
                totalMoves += case1;
                curR = pos2[0];
                curC = pos2[1];
            } else {
                totalMoves += case2;
                curR = pos1[0];
                curC = pos1[1];
            }

            // 카드 제거
            tempBoard[pos1[0]][pos1[1]] = 0;
            tempBoard[pos2[0]][pos2[1]] = 0;
        }

        return totalMoves;
    }

    // BFS로 최단 거리 계산 (Ctrl 이동 포함)
    private int bfs(int[][] board, int startR, int startC, int endR, int endC) {
        if (startR == endR && startC == endC) return 0;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[4][4];
        queue.offer(new int[]{startR, startC, 0});
        visited[startR][startC] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];
            int dist = cur[2];

            for (int i = 0; i < 4; i++) {
                // 일반 이동
                int nr = r + dx[i];
                int nc = c + dy[i];
                if (isValid(nr, nc) && !visited[nr][nc]) {
                    if (nr == endR && nc == endC) return dist + 1;
                    visited[nr][nc] = true;
                    queue.offer(new int[]{nr, nc, dist + 1});
                }

                // Ctrl 이동
                int[] ctrlPos = getCtrlMove(board, r, c, i);
                int cr = ctrlPos[0];
                int cc = ctrlPos[1];
                if (!visited[cr][cc]) {
                    if (cr == endR && cc == endC) return dist + 1;
                    visited[cr][cc] = true;
                    queue.offer(new int[]{cr, cc, dist + 1});
                }
            }
        }

        return Integer.MAX_VALUE;
    }

    // Ctrl 이동 시 도착 위치 계산
    private int[] getCtrlMove(int[][] board, int r, int c, int dir) {
        int nr = r;
        int nc = c;

        while (true) {
            int nextR = nr + dx[dir];
            int nextC = nc + dy[dir];

            if (!isValid(nextR, nextC)) break;

            nr = nextR;
            nc = nextC;

            if (board[nr][nc] != 0) break;
        }

        return new int[]{nr, nc};
    }

    private boolean isValid(int r, int c) {
        return r >= 0 && r < 4 && c >= 0 && c < 4;
    }

    private int[][] copyBoard(int[][] board) {
        int[][] copy = new int[4][4];
        for (int i = 0; i < 4; i++) {
            copy[i] = board[i].clone();
        }
        return copy;
    }

    // 순열만들기
    private void getPerms(List<Integer> cardList, List<Integer> current) {
        if (current.size() == cardList.size()) {
            perms.add(new ArrayList<>(current));
            return;
        }
        for (int card : cardList) {
            if (!current.contains(card)) {
                current.add(card);
                getPerms(cardList, current);
                current.remove(current.size() - 1);
            }
        }
    }
}