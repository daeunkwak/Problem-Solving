import java.util.*;
import java.io.*;

/**
 * title : 마법의 숲 탐색
 * date : 2025-09-26
 */
public class Main {

    static class Golem {
        int r;
        int c;
        int d;
        int idx;

        Golem( int c, int d, int idx) {
            r = -1;     // ** 이러면 된다 !
            this.c = c;
            this.d = d;
            this.idx = idx;
        }

        boolean canMoveSouth(int r, int c, int idx) {
            if (r + 2 <= R) {
                boolean f = (map[r + 2][c] == 0 || map[r + 2][c] == idx || map[r + 2][c] == idx * 1001);
                boolean s = (map[r + 1][c - 1] == 0 || map[r + 1][c - 1] == idx || map[r + 1][c - 1] == idx * 1001);
                boolean t = (map[r + 1][c + 1] == 0 || map[r + 1][c + 1] == idx || map[r + 1][c + 1] == idx * 1001);
                // System.out.println("아니왜.. " + f + " " + s  + " " + t);
                return (f && s && t);
            } else {
                return false;
            }
        }

        boolean canMoveWest() {
            // System.out.println("west 입장");
            if (c - 2 > 0) {
                if (isBound(r - 1, c - 1)) {
                    // System.out.println("1");
                    if (map[r - 1][c - 1] != 0) return false;
                }
                if (isBound(r + 1, c - 1)) {
                    // System.out.println("2");
                    if (map[r + 1][c - 1] != 0) return false;
                }
                // System.out.println("3");
                // ****** 인덱스 런타임에러 조심
                if (isBound(r, c - 2)) {
                    if (map[r][c - 2] != 0) return false;
                }

                // System.out.println("canMoveSouth : " + canMoveSouth(r, c - 1, idx));
                return canMoveSouth(r, c - 1, idx);
            }
            return false;
        }

        // *** 이런식으로 짜야하는듯?
        boolean canMoveEast() {
            // System.out.println("입장");
            if (c + 2 <= C) {
                if (isBound(r - 1, c + 1)) {
                    if (map[r - 1][c + 1] != 0) return false;
                }
                if (isBound(r + 1, c + 1)) {
                    if (map[r + 1][c + 1] != 0) return false;
                }
                if (isBound(r, c + 2)) {
                    if (map[r][c + 2] != 0) return false;
                }

                // System.out.println("canMoveSoutn : " + canMoveSouth(r, c + 1, idx));
                return canMoveSouth(r, c + 1, idx);
            }
            return false;
        }

        void moveSouth() {
            this.r += 1;
        }

        void moveWest() {
            this.c -= 1;
            this.d = (this.d + 3) % 4;
        }

        void moveEast() {
            this.c += 1;
            this.d = (this.d + 1) % 4;
        }

        // 넘쳤는지 확인
        boolean isOver() {
            if (!isBound(r, c)) return true;
            if (!isBound(r, c + 1)) return true;
            if (!isBound(r, c - 1)) return true;
            if (!isBound(r + 1, c)) return true;
            if (!isBound(r - 1, c)) return true;
            return false;
        }
    }

    static void moveSouthAndUpdateMap(Golem golem) {
        // map 0으로 바꾸기
        cleanGolemMap(golem);
        // 골렘 이동하기
        golem.moveSouth();
        // map 업데이트
        updateGolemMap(golem);
    }

    static void moveWestAndUpdateMap(Golem golem) {
        cleanGolemMap(golem);
        golem.moveWest();
        golem.moveSouth();
        updateGolemMap(golem);
    }

    static void moveEastAndUpdateMap(Golem golem) {
        cleanGolemMap(golem);
        golem.moveEast();
        golem.moveSouth();
        updateGolemMap(golem);
    }

    static void updateGolemMap(Golem golem) {
        int r = golem.r;
        int c = golem.c;
        int d = golem.d;
        int idx = golem.idx;

        // 바운더리에 해당하는 골렘만 update
        if (isBound(r, c)) map[r][c] = idx;
        if (isBound(r, c + 1)) map[r][c + 1] = idx;
        if (isBound(r, c - 1)) map[r][c - 1] = idx;
        if (isBound(r + 1, c)) map[r + 1][c] = idx;
        if (isBound(r - 1, c)) map[r - 1][c] = idx;

        // *** 출구는 idx * 1001로 표시
        if (isBound(r + dr[d], c + dc[d])) {
            map[r + dr[d]][c + dc[d]] = idx * 1001;
        }
    }

    static void cleanGolemMap(Golem golem) {
        int r = golem.r;
        int c = golem.c;

        if (isBound(r, c)) map[r][c] = 0;
        if (isBound(r, c + 1)) map[r][c + 1] = 0;
        if (isBound(r, c - 1)) map[r][c - 1] = 0;
        if (isBound(r + 1, c)) map[r + 1][c] = 0;
        if (isBound(r - 1, c)) map[r - 1][c] = 0;
    }

    static boolean isBound(int r, int c) {
        return (r > 0 && r <= R && c > 0 && c <= C);
    }

    static int bfs(Golem golem) {
        Queue<Golem> queue = new LinkedList<>();
        boolean[] visited = new boolean[K + 1];
        queue.offer(golem);
        visited[golem.idx] = true;

        int maxRow = golem.r + 1;
        // 출구에서 다른 골렘과 인접한 경우 이동 가능
        // 인접하지 않은 경우 답은 r + 1
        while (!queue.isEmpty()) {
            // 골렘 출구찾기
            Golem curGolem = queue.poll();
            maxRow = Math.max(maxRow, curGolem.r + 1);
            int exitR = curGolem.r + dr[curGolem.d];
            int exitC = curGolem.c + dc[curGolem.d];
            // System.out.println("exitR : " + exitR + " exitC :  " + exitC);

            for (int d = 0; d < 4; d++) {
                int nR = exitR + dr[d];
                int nC = exitC + dc[d];

                if (nR > 0 && nR <= R && nC > 0 && nC <= C) {
                    int seq = map[nR][nC];
                    if (seq >= 1001) {
                        seq = seq / 1001;
                    }
                    // System.out.println("nR : " + nR + " nC : " + nC + " seq : " + seq);
                    if (seq != 0 && seq != curGolem.idx && !visited[seq]) {
                        // System.out.println("그럼이조건이잖아");
                        queue.offer(golems[seq - 1]);
                        visited[seq] = true;
                    }
                }
            }
        }
        return maxRow;
    }

    // **** 귀찮아도  디버깅을 꼭!!!! 해야함
    static void printMap() {
        for (int i = 1; i <= R; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j <= C; j++) {
                sb.append(map[i][j]);
                sb.append(" ");
            }
            System.out.println(sb.toString());
        }
    }

    static int R;
    static int C;
    static int K;
    // 북 동 남 서
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static Golem[] golems;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());   // 숲의 크기
        K = Integer.parseInt(st.nextToken());   // 정령의 수

        map = new int[R + 1][C + 1];
        golems = new Golem[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int gr = Integer.parseInt(st.nextToken()); // 출발 열
            int gd = Integer.parseInt(st.nextToken()); // 출구 방향
            golems[i] = new Golem(gr, gd, i + 1);
        }


        // 1 ~ R행
        // 테트리스마냥 골렘 하나씩 남쪽으로 이동함
        // i번째 골렘은 중앙이 ci열이 되도록 내려옴

        // 골렘 하나씩 테트리스하기
        int result = 0;
        for (int i = 0; i < K; i++) {
            // 골렘이 하나씩, 한칸씩 순서대로 이동함
            // System.out.println("-- 골렘 " + i);
            Golem golem = golems[i];
            boolean done = false;
            while (!done) {
                // printMap();
                // System.out.println("--");
                // System.out.println("현재 골렘 : " + golem.r + " " + golem.c + " " + golem.d);
                if (golem.canMoveSouth(golem.r, golem.c, golem.idx)) {  // [1] 남 이동
                    // System.out.println("남쪽 이동 가능");
                    moveSouthAndUpdateMap(golem);
                } else if (golem.canMoveWest()) {    // [2] 서 > 남 이동
                    moveWestAndUpdateMap(golem);
                } else if (golem.canMoveEast()) {   // [3] 동 > 남 이동
                    // System.out.println("동쪽으로 이동 가능");
                    moveEastAndUpdateMap(golem);
                    // System.out.println("이동 후 : " + golem.r + " " + golem.c + " " + golem.d);
                } else {
                    // [4] 이동 못함 > 정령 BFS, done = true
                    // 골렘이 map 안에 들어오면 BFS > 점수 더하기
                    // 최대한 내려왔을 때 BFS (막히면 max행 갱신하기)
                    // 골렘이 넘치면 map 초기화, 점수 없음
                    // System.out.println("---------");
                    if (golem.isOver()) {
                        // printMap();
                        map = new int[R + 1][C + 1];
                    } else {
                        // printMap();
                        int score = bfs(golem);
                        // System.out.println("bfs결과 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + score);
                        result += score;
                    }
                    done = true;
                }
            }
        }

        System.out.println(result);
    }
}