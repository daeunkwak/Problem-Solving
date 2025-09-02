package just.p_0901;

import java.util.*;

/**
 * title : 운동
 * date : 2025-09-01
 */
public class boj1956 {
    private static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) {
        // 1번 ~ V번 마을 사이클 찾기
        // 사이클 이루는 도로 길이 합 최소
        // 노드 각각 자기자신으로 돌아오는 최단거리 구하기 (노드 하나 왕복 포함)

        Scanner sc = new Scanner(System.in);
        int V = sc.nextInt();
        int E = sc.nextInt();

        int[][] dist = new int[V + 1][V + 1];
        for (int i = 0; i < V + 1; i++) Arrays.fill(dist[i], INF);

        for (int i = 0; i < E; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int distance = sc.nextInt();
            dist[a][b] = distance;  // 단방향
        }

        for (int k = 1; k <= V; k++) {   // 경유
            for (int i = 1; i <= V; i++) {
                for (int j= 1; j <= V; j++) {
                    // i -> j, k 거쳐서
                    if (dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        int minCycleDist = INF;
        for (int i = 0; i < V; i++) {
            if (dist[i][i] != INF) minCycleDist = Math.min(minCycleDist, dist[i][i]);
        }

        System.out.println((minCycleDist == INF) ? -1 : minCycleDist);
    }
}
