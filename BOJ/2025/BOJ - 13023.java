package just.p_0909;

import java.util.*;

/**
 * title : ABCDE
 * date : 2025-09-15
 */
public class boj13023 {
    static boolean found = false;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();  // 사람 수
        int M = sc.nextInt();  // 관계 수

        // 이어진 친구관계 5명
        // DFS > 5명찾기
        List<Integer>[] graph = new ArrayList[N];
        for (int i = 0; i < N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            graph[a].add(b);
            graph[b].add(a);
        }

        boolean[] visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (!found) {
                visited[i] = true;
                dfs(i, 1, graph, visited);
                visited[i] = false;
            }
        }

        System.out.println(found ? 1 : 0);
    }

    static void dfs(int cur, int depth, List<Integer>[] graph, boolean[] visited) {
        if (depth == 5) {
            found = true;
            return;
        }

        if (found) return;

        for (int next : graph[cur]) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next, depth + 1, graph, visited);
                visited[next] = false;
            }
        }
    }
}
