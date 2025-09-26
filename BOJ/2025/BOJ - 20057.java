package just.samsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * title : 마법사 상어와 토네이도
 * date : 2025-09-25
 */
public class boj20057 {
    // 좌 하 우 상
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = { 0, 1, 0,-1};
    static int[][] map;
    static int sand;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int distance = 1;
        int d = 0;
        sand = 0;
        int sY = N / 2;
        int sX = N / 2;
        int cY = 1, cX = 1;

        while (!(sY == 0 && sX == 0)) {
            for (int c = 0; c < 2; c++) {      // 각 크기마다 2번씩 이동
                for (int i = 0; i < distance; i++) {
                    // 이동할 위치
                    cY = sY + dy[d];
                    cX = sX + dx[d];

                    // *****
                    move(cY, cX, d, map[cY][cX]);
                    map[cY][cX] = 0;

                    sY = cY;
                    sX = cX;

                    if (sY == 0 && sX == 0) {
                        System.out.println(sand);
                        return;
                    }
                }
                d = (d + 1) % 4;
            }
            distance++;
        }
        System.out.println(sand);
    }

    private static void add(int y, int x, int v) {
        if (v == 0) return;
        if (0 <= y && y < N && 0 <= x && x < N) map[y][x] += v;
        else sand += v;
    }

    // 좌/우 vs 상/하 분기 패턴 (네 구조 유지, 좌표/체크만 교정)
    private static void move(int cY, int cX, int d, int curSand) {
        if (curSand == 0) return;
        int used = 0;

        if (d == 0 || d == 2) { // 좌/우 (가로 이동)
            int f = dx[d]; // 좌:-1, 우:+1

            int v1 = curSand / 100;               // 1%
            add(cY - 1, cX - f, v1); used += v1;
            add(cY + 1, cX - f, v1); used += v1;

            int v7 = curSand * 7 / 100;           // 7%
            add(cY - 1, cX, v7);   used += v7;
            add(cY + 1, cX, v7);   used += v7;

            int v2 = curSand * 2 / 100;           // 2%
            add(cY - 2, cX, v2);    used += v2;
            add(cY + 2, cX, v2);    used += v2;

            int v10 = curSand / 10;               // 10%
            add(cY - 1, cX + f, v10); used += v10;
            add(cY + 1, cX + f, v10); used += v10;

            int v5 = curSand * 5 / 100;           // 5%
            add(cY, cX + 2 * f, v5); used += v5;

            // alpha(나머지): 전방 1칸
            add(cY, cX + f, curSand - used);

        } else { // d == 1 || d == 3  // 하/상 (세로 이동)
            int f = dy[d]; // 하:+1, 상:-1

            int v1 = curSand / 100;               // 1%
            add(cY - f, cX - 1, v1); used += v1;
            add(cY - f, cX + 1, v1); used += v1;

            int v7 = curSand * 7 / 100;           // 7%
            add(cY, cX - 1, v7);   used += v7;
            add(cY, cX + 1, v7);   used += v7;

            int v2 = curSand * 2 / 100;           // 2%
            add(cY, cX - 2, v2);    used += v2;
            add(cY, cX + 2, v2);    used += v2;

            int v10 = curSand / 10;               // 10%
            add(cY + f, cX - 1, v10); used += v10;
            add(cY + f, cX + 1, v10); used += v10;

            int v5 = curSand * 5 / 100;           // 5%
            add(cY + 2 * f, cX, v5); used += v5;

            // alpha(나머지): 전방 1칸
            add(cY + f, cX, curSand - used);
        }
    }
}
