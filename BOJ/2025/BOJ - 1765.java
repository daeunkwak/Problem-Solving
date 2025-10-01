package just.p_0922;

import java.util.*;

/**
 * title : 닭싸움 팀 정하기
 * date : 2025-10-01
 */
public class boj1765 {
    static int[] parent;
    static int[] enemy;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        parent = new int[n + 1];
        enemy = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            String type = sc.next();
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (type.equals("F")) {
                union(a, b);    // 친구
            } else {
                // 원수의 원수랑 union
                if (enemy[a] != 0) {
                    union(enemy[a], b);
                } else {
                    enemy[a] = b;
                }

                if (enemy[b] != 0) {
                    union(enemy[b], a);
                } else {
                    enemy[b] = a;
                }
            }
        }

        Set<Integer> teams = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            teams.add(find(i));
        }

        System.out.println(teams.size());
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a != b) {
            parent[b] = a;
        }
    }
}
