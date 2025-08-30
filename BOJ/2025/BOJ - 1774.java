package just.p_0826;

import java.util.*;

/**
 * title : 우주선과의 교감
 * date : 2025-08-30
 */
public class boj1774 {
    private static int[] parents;

    static class Edge {
        int from, to;
        double dist;

        Edge(int from, int to, double dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
    }

    public static void main(String[] args) {
        // 위대한 우주신을 거쳐 황선자와 교감 가능
        // 황선자씨 or 정상적 통로로 교감 가능
        // 황선자는 긴 통로를 싫어함
        // 새로 만들어야 할 통로 길이들의 합이 최소가 되는 통로를 만들기

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   // 우주신 개수
        int M = sc.nextInt();   // 통로 개수

        int[][] aliens = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            aliens[i][0] = sc.nextInt();  // x좌표
            aliens[i][1] = sc.nextInt();  // y좌표
        }

        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        // 이미 연결된 우주신 union
        for (int i = 0; i < M; i++) {
            int a1 = sc.nextInt();
            int a2 = sc.nextInt();
            union(a1, a2);
        }

        // 우주신 거리정보 (가중치) 계산
        List<Edge> edges = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                double distance = Math.sqrt(Math.pow(aliens[i][0] - aliens[j][0], 2) + Math.pow(aliens[i][1] - aliens[j][1], 2));
                edges.add(new Edge(i, j, distance));
            }
        }

        // 가중치 오름차순 정렬
        edges.sort((a, b) -> Double.compare(a.dist, b.dist));

        // 크루스칼 알고리즘
        double totalCost = 0.0;
        for (Edge edge : edges) {
            int a1 = edge.from;
            int a2 = edge.to;
            if (find(a1) != find(a2)) {  // 다른 집합이면 연결
                union(a1, a2);
                totalCost += edge.dist;
            }
        }

        System.out.printf("%.2f\n", totalCost);
        sc.close();
    }

    private static void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        if (px != py) {
            parents[py] = px;
        }
    }

    private static int find(int x) {
        if (parents[x] != x) {
            parents[x] = find(parents[x]);
        }
        return parents[x];
    }
}