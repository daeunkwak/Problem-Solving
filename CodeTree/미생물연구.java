
import java.util.*;
import java.io.*;

/**
 * title : 미생물연구
 * date : 2025-09-27
 */
public class 미생물연구 {

    // 좌표계 변환을 위한 유틸 클래스
    static class CoordinateUtils {

        static int[] coordToArray(int x, int y, int N) {
            int row = N - y - 1;  // y축 뒤집기
            int col = x;          // x는 그대로
            return new int[]{row, col};
        }

        static int[] arrayToCoord(int row, int col, int N) {
            int x = col;          // 열이 x
            int y = N - row - 1;  // 행에서 y 복원
            return new int[]{x, y};
        }

        static boolean isValidArray(int row, int col, int N) {
            return row >= 0 && row < N && col >= 0 && col < N;
        }

        static boolean isValidCoord(int x, int y, int N) {
            return x >= 0 && x < N && y >= 0 && y < N;
        }
    }

    static class Creature {
        int r;
        int c;  // 아무거나 좌표 하나 (이제 배열 인덱스로 사용)
        int area;
        int idx;

        Creature(int x, int y, int area, int idx) {
            int[] arrayPos = CoordinateUtils.coordToArray(x, y, N);
            this.r = arrayPos[0];  // row
            this.c = arrayPos[1];  // col
            this.area = area;
            this.idx = idx;
        }

        // r, c가 지금도 유효한지 수시로 체크하고 업데이트 해야할듯
    }

    static void updatePresentCoordinate(int q) {
        List<Creature> removeList = new ArrayList<>();
        for (Creature creature : creatures) {
            if (creature.idx == q) {
                // 면적 1 감소
                creature.area -= 1;

                // 현재 좌표가 여전히 유효한지 확인
                if (map[creature.r][creature.c] == q) {
                    // 여전히 유효하면 그대로 유지
                    continue;
                }

                // BFS로 새로운 유효한 좌표 찾기
                Queue<int[]> queue = new LinkedList<>();
                boolean[][] visited = new boolean[N][N];
                boolean found = false;

                // 전체 맵에서 해당 미생물의 영역 찾기
                for (int i = 0; i < N && !found; i++) {
                    for (int j = 0; j < N && !found; j++) {
                        if (map[i][j] == q) {
                            creature.r = i;
                            creature.c = j;
                            found = true;
                        }
                    }
                }

                // 해당 미생물이 완전히 사라졌다면
                if (!found) {
                    // System.out.println("creature " + creature.idx + " 삭제됨");
                    removeList.add(creature);
                }
            }
        }
        for (Creature cre : removeList) {
            creatures.remove(cre);
        }
    }

    static boolean isBound(int r, int c) {
        return CoordinateUtils.isValidArray(r, c, N);
    }

    // (x, y)라고 간주하고 풀어야함ㅋㅋ..
    static void putCreature(int x1, int y1, int x2, int y2, int q) {
        for (int y = y1; y < y2; y++) {
            for (int x = x1; x < x2; x++) {
                int[] arrayPos = CoordinateUtils.coordToArray(x, y, N);
                int row = arrayPos[0];
                int col = arrayPos[1];

                // 대표 좌표 업데이트 해놓고
                if (map[row][col] != 0) {
                    updatePresentCoordinate(map[row][col]);
                }
                // 덮어씌우기
                map[row][col] = q;
            }
        }
    }

    // 반으로 나뉜 미생물 제거하기
    static void removeDivided() {
        List<Creature> removeList = new ArrayList<>();
        for (Creature creature : creatures) {
            // 올바른 시작점 찾기
            int startR = -1, startC = -1;
            boolean found = false;

            for (int i = 0; i < N && !found; i++) {
                for (int j = 0; j < N && !found; j++) {
                    if (map[i][j] == creature.idx) {
                        startR = i;
                        startC = j;
                        found = true;
                    }
                }
            }

            if (!found) {
                // 미생물이 완전히 사라진 경우
                removeList.add(creature);
                continue;
            }

            // BFS로 연결된 영역 크기 계산
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visited = new boolean[N][N];
            queue.offer(new int[]{startR, startC});
            visited[startR][startC] = true;

            int cnt = 0;
            int idx = creature.idx;
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                cnt++;
                int cR = cur[0];
                int cC = cur[1];

                for (int d = 0; d < 4; d++) {
                    int nR = cR + dr[d];
                    int nC = cC + dc[d];

                    if (isBound(nR, nC) && !visited[nR][nC] && map[nR][nC] == idx) {
                        queue.offer(new int[]{nR, nC});
                        visited[nR][nC] = true;
                    }
                }
            }

            if (cnt != creature.area) {
                removeList.add(creature);
            } else {
                // 좌표 업데이트
                creature.r = startR;
                creature.c = startC;
            }
        }

        for (Creature creature : removeList) {
            creatures.remove(creature);
        }
    }

    // 배양 용기 이동 위치 정하기
    static boolean canMove(Creature creature, int targetX, int targetY) {
        int[] targetPos = CoordinateUtils.coordToArray(targetX, targetY, N);
        int targetRow = targetPos[0];
        int targetCol = targetPos[1];

        int diffR = targetRow - creature.r;  // row 차이
        int diffC = targetCol - creature.c;  // col 차이

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        // 시작 가능?
        int startNewRow = creature.r + diffR;
        int startNewCol = creature.c + diffC;
        if (CoordinateUtils.isValidArray(startNewRow, startNewCol, N) && newMap[startNewRow][startNewCol] == 0) {
            queue.offer(new int[]{creature.r, creature.c});
            visited[creature.r][creature.c] = true;   // 방문체크는 기존 Map 기준으로
        } else {
            return false;
        }

        // 진행
        int idx = creature.idx;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cR = cur[0];
            int cC = cur[1];

            // 범위 밖이거나 이미 차있는경우 불가능
            int newRow = cR + diffR;
            int newCol = cC + diffC;
            if (!CoordinateUtils.isValidArray(newRow, newCol, N) || newMap[newRow][newCol] != 0) {
                return false;
            }

            for (int d = 0; d < 4; d++) {
                int nR = cR + dr[d];
                int nC = cC + dc[d];

                if (isBound(nR, nC) && !visited[nR][nC] && map[nR][nC] == idx) {
                    queue.offer(new int[]{nR, nC});
                    visited[nR][nC] = true;
                }
            }
        }

        return true;
    }

    // 배양 용기에 이동하기
    static void move(Creature creature, int targetX, int targetY) {
        int[] targetPos = CoordinateUtils.coordToArray(targetX, targetY, N);
        int targetRow = targetPos[0];
        int targetCol = targetPos[1];

        int diffR = targetRow - creature.r;  // row 차이
        int diffC = targetCol - creature.c;  // col 차이

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        int idx = creature.idx;
        queue.offer(new int[]{creature.r, creature.c});
        visited[creature.r][creature.c] = true;   // 방문체크는 기존 Map 기준으로

        // 진행
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cR = cur[0];
            int cC = cur[1];

            int newRow = cR + diffR;
            int newCol = cC + diffC;

            // if (newMap[newRow][newCol] != 0) {
            //     System.out.println("!!!!!!!!!!!! 오류 : " + newMap[newRow][newCol]);
            // }

            newMap[newRow][newCol] = idx;

            for (int d = 0; d < 4; d++) {
                int nR = cR + dr[d];
                int nC = cC + dc[d];

                if (isBound(nR, nC) && !visited[nR][nC] && map[nR][nC] == idx) {
                    queue.offer(new int[]{nR, nC});
                    visited[nR][nC] = true;
                }
            }
        }

        // creature의 새로운 위치 업데이트
        creature.r = targetRow;
        creature.c = targetCol;
    }

    static void printMap() {
        for (int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j]);
                sb.append(" ");
            }
            System.out.println(sb.toString());
        }
        System.out.println("--------");
    }

    static void printNewMap() {
        for (int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < N; j++) {
                sb.append(newMap[i][j]);
                sb.append(" ");
            }
            System.out.println(sb.toString());
        }
        System.out.println("--------");
    }

    static int N;
    static int Q;
    static List<Creature> creatures;
    static int[][] map;
    static int[][] newMap;
    // 상 하 좌 우
    static int[] dr = {-1, 1, 0, 0};  // 배열에서는 위가 -1, 아래가 +1
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   // 좌표 크기
        Q = Integer.parseInt(st.nextToken());   // 실험 횟수

        map = new int[N][N];    // 0부터 N-1까지 인덱스
        creatures = new ArrayList<>();

        // Q번 실험
        for (int q = 1; q <= Q; q++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int area = (x2 - x1) * (y2 - y1);
            // System.out.println("new creature, x : " + x1 + " y : " + y1);
            Creature creature = new Creature(x1, y1, area, q);

            // [1] 미생물 투입 ---------------
            putCreature(x1, y1, x2, y2, q);
            removeDivided();    // 갈라진거 소멸시키기 > BFS돌리고 면적 비교

            creatures.add(creature);    // 미생물 리스트에 추가
            // System.out.println("map ---");
            // printMap();

            // [2] 용기 이동 ---------------
            creatures.sort((a, b) -> {
                if (a.area == b.area) return a.idx - b.idx;
                else return b.area - a.area;
            });     // 면적, 순서 순으로 정렬

            // 새로운 용기에 하나씩 옮기기
            // x좌표 작은순 -> y좌표 작은순으로 한칸씩 확인하면서 BFS
            newMap = new int[N][N];
            List<Creature> removeList = new ArrayList<>();
            // System.out.println("미생물 개수 : " + creatures.size());
            for (Creature cre : creatures) {
                // System.out.println("미생물 " + cre.idx);
                boolean flag = false;

                // x좌표 작은순 -> y좌표 작은순으로 탐색
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < N; y++) {
                        // System.out.println("trying x : " + x + " y : " + y + " " + canMove(cre, x, y));
                        if (canMove(cre, x, y)) {
                            move(cre, x, y);
                            flag = true;
                            break;
                        }
                    }
                    if (flag) break;
                }

                if (!flag) removeList.add(cre);
            }
            // System.out.println("newMap ---");
            // printNewMap();

            // 옮길 자리 없으면 삭제
            // System.out.println("삭제된건가? : " + removeList.size());
            for (Creature cre : removeList) creatures.remove(cre);

            // 각 실험 후 모든 미생물의 실제 면적 재계산
            for (Creature cre : creatures) {
                int actualArea = 0;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if (map[i][j] == cre.idx) {
                            actualArea++;
                        }
                    }
                }
                cre.area = actualArea;
            }

            map = newMap;

            // [3] 실험 결과 기록 ---------------
            int result = 0;
            for (int i = 0; i < creatures.size() - 1; i++) {
                for (int j = i + 1; j < creatures.size(); j++) {
                    // 인접 판단....썅
                    if (isAdjacent(creatures.get(i), creatures.get(j))) {
                        result += creatures.get(i).area * creatures.get(j).area;
                    }
                }
            }
            // System.out.println("result >>>>>>>>>>>>>>>>>>>>>>>>>>> " + result);
            System.out.println(result);
        }
    }

    static boolean isAdjacent(Creature c1, Creature c2) {
        int target = c2.idx;
        int idx = c1.idx;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        queue.offer(new int[]{c1.r, c1.c});
        visited[c1.r][c1.c] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cR = cur[0];
            int cC = cur[1];

            // if (map[cR][cC] == target) return true;

            for (int d = 0; d < 4; d++) {
                int nR = cR + dr[d];
                int nC = cC + dc[d];

                if (isBound(nR, nC)) {
                    if (map[nR][nC] == target) {
                        return true;
                    }
                }

                if (isBound(nR, nC) && !visited[nR][nC] && map[nR][nC] == idx) {
                    queue.offer(new int[]{nR, nC});
                    visited[nR][nC] = true;
                }
            }
        }
        return false;
    }
}