package just.p_0808;

import java.util.*;

/**
 * title : 여행 가자
 * date : 2025-08-08
 */
public class boj1976 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();   // 도시의 수
        int M = sc.nextInt();   // 계획에 포함된 도시의 수

        int[] parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int connected = sc.nextInt();
                if (connected == 1) {
                    union(i, j, parents);
                }
            }
        }

        int visitFirst= sc.nextInt();
        boolean flag = true;
        for (int i = 1; i < M; i++) {
            int visit = sc.nextInt();
            if (find(visitFirst, parents) != find(visit, parents)) {
                flag = false;
                break;
            }
        }

        if (flag) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static int find(int x, int[] parents) {
        if (parents[x] != x) {
            parents[x] = find(parents[x], parents);
        }
        return parents[x];
    }

    private static void union(int x, int y, int[] parents) {
        x = find(x, parents);
        y = find(y, parents);

        if (x != y) {
            parents[y] = x;
        }
    }
}
