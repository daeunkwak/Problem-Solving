import java.io.IOException;
import java.util.*;

/**
 * title : 구슬 찾기
 * date : 2025-03-17
 */
public class BOJ_2617 {

    static int answer = 0;

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();  // 구슬 개수
        int m = scanner.nextInt();  // m쌍의 구슬

        ArrayList[] heavy = new ArrayList[n + 1];   // ~보다 무겁다
        ArrayList[] light = new ArrayList[n + 1];   // ~보다 가볍다
        for (int i = 0; i < n + 1; i++) {
            heavy[i] = new ArrayList<Integer>();
            light[i] = new ArrayList<Integer>();
        }

        for (int i = 0; i < m; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            heavy[a].add(b);
            light[b].add(a);
        }

        bfs(heavy, n);
        bfs(light, n);
        System.out.println(answer);

    }

    private static void bfs(ArrayList<Integer>[] graph, int n) {

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i < n + 1; i++) {
            boolean[] visited = new boolean[n + 1];
            int cnt = 0;
            visited[0] = true;
            visited[i] = true;
            queue.offer(i);

            while (!queue.isEmpty()) {
                int current = queue.poll();

                for (int ball : graph[current]) {
                    if (!visited[ball]) {
                        queue.offer(ball);
                        visited[ball] = true;
                        cnt++;  // ******** 여기에 있어야함
                    }
                }
            }

            if (cnt > n / 2) {
                answer += 1;
            }
        }

    }
}