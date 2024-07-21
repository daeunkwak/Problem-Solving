"""
author : https://github.com/daeunkwak
date : 2024-07-06
title : BOJ) 토마토
"""

public class 토마토 {

    static int[][] tomato;
    static int N, M;
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] arr = new int[m][n];
        int cnt = 0;
        Queue<int[]> queue = new LinkedList<>();

        // 입력 배열 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                tomato[i][j] = Integer.parseInt(st.nextToken());
                if(tomato[i][j] == 1){
                    queue.add(new int[] {i, j});  // 익은 토마토 위치 push
                }
                else if(tomato[i][j] == 0){
                    cnt++;   // 안익은 토마토 개수 세기 -> 0 될때까지(다 익을때까지) 반복
                }
            }
        }

        int days = 0;
        // 아직 안익은 토마토가 있음 && queue에 토마토 들어있음
        while (cnt > 0 && !queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                int[] now = queue.poll();

                for (int j = 0; j < 4; j++) {
                    int ny = now[0] + dy[j];
                    int nx = now[1] + dx[j];
                    // 상자 안에 있지 않거나, 익힐 필요가 없는 토마토인 경우 pass
                    if (ny < 0 || nx < 0 || ny >= N || nx >= M || tomato[ny][nx] != 0) {
                        continue;
                    }
                    // 안익은 토마토인 경우 cnt감소, 익음처리
                    cnt--;
                    tomato[ny][nx] = 1;
                    queue.add(new int[]{ny, nx});
                }
            }
            days++;
        }

        if(cnt == 0) {
            System.out.println(days);
        } else System.out.println(-1);

    }
}