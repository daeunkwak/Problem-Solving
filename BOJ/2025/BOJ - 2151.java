
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * title : 거울 설치
 * date : 2025-07-29
 */
public class boj2151 {

    // up, right, down, left 각각 0, 1, 2, 3
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private static class Info {
        int x;
        int y;
        int direct;
        int cnt;

        public Info(int x, int y, int direct, int cnt) {
            this.x = x;
            this.y = y;
            this.direct = direct;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        char[][] map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int[] start = new int[2];
        int[] end = new int[2];
        boolean flag = false;

        // 문 저장
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '#') {
                    if (!flag) {
                        start[0] = i;
                        start[1] = j;
                        flag = true;  // flag 업데이트 추가
                    } else {
                        end[0] = i;
                        end[1] = j;
                    }
                }
            }
        }

        // 거울 개수 저장 배열 (최소값 보장)
        int[][][] visited = new int[N][N][4];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }

        // 우선순위 큐 사용 (거울 개수 기준 정렬)
        PriorityQueue<Info> queue = new PriorityQueue<>((a, b) -> a.cnt - b.cnt);
        for (int i = 0; i < 4; i++) {
            queue.add(new Info(start[0], start[1], i, 0));  // 행, 열 순서로 수정
            visited[start[0]][start[1]][i] = 0;  // 시작점 거울 개수 0
        }

        int minCnt = Integer.MAX_VALUE;
        while(!queue.isEmpty()) {
            Info info = queue.poll();

            int cX = info.x + dx[info.direct];
            int cY = info.y + dy[info.direct];

            // 범위 내에 속할 때
            if (cX >= 0 && cX < N && cY >= 0 && cY < N) {

                // 이미 더 적은 거울로 도달한 경우 스킵
                if (info.cnt >= visited[cX][cY][info.direct]) {
                    continue;
                }

                if (map[cX][cY] == '!') {     // 거울 > 90도 회전한 방향 큐에 넣어주기
                    // 거울 설치하는 경우 - 올바른 방향 전환
                    int dir1 = (info.direct + 1) % 4;  // 시계방향 90도
                    int dir2 = (info.direct + 3) % 4;  // 반시계방향 90도

                    if (info.cnt + 1 < visited[cX][cY][dir1]) {
                        queue.offer(new Info(cX, cY, dir1, info.cnt + 1));
                        visited[cX][cY][dir1] = info.cnt + 1;
                    }
                    if (info.cnt + 1 < visited[cX][cY][dir2]) {
                        queue.offer(new Info(cX, cY, dir2, info.cnt + 1));
                        visited[cX][cY][dir2] = info.cnt + 1;
                    }

                    // 거울 설치하지 않는 경우
                    if (info.cnt < visited[cX][cY][info.direct]) {
                        queue.offer(new Info(cX, cY, info.direct, info.cnt));
                        visited[cX][cY][info.direct] = info.cnt;
                    }
                } else if (map[cX][cY] == '.') {    // 빈공간 > 직진
                    if (info.cnt < visited[cX][cY][info.direct]) {
                        queue.offer(new Info(cX, cY, info.direct, info.cnt));
                        visited[cX][cY][info.direct] = info.cnt;
                    }
                } else if (map[cX][cY] == '#') {    // 문 > 탐색 완료
                    minCnt = Math.min(minCnt, info.cnt);
                }
            }
        }
        System.out.println(minCnt);
    }
}