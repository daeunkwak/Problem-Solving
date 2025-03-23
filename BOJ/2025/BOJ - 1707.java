import java.io.IOException;
import java.util.*;

/**
 * title : 이분 그래프
 * date : 2025-03-17
 */
public class BOJ_1707 {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        int k = scanner.nextInt();

        // k번 반복
        for (int i = 0; i < k; i++) {
            int v = scanner.nextInt();  // 정점 개수
            int e = scanner.nextInt();  // 간선 정보 개수

            ArrayList[] graph = new ArrayList[v + 1];

            // 초기화 !!!!!!!!
            for (int j = 1; j <= v; j++) {
                graph[j] = new ArrayList<>();
            }

            for (int j = 1; j < e + 1; j++) {
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                graph[a].add(b);
                graph[b].add(a);
            }

            if (bfs(graph, v)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

    }
    private static boolean bfs(ArrayList<Integer>[] graph, int v) {

        boolean isBipartite = true;

        // 0 : 방문안함, 1, -1 두 개의 색으로 칠함 (번갈아가면서 나와야함)
        int[] visited = new int[v + 1];
        visited[0] = 1;

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < v; i++) {
            if (visited[i] == 0) {
                queue.offer(i);
                visited[i] = 1; // 1로 시작하기
            }

            while (!queue.isEmpty()) {
                int current = queue.poll();
                for (int connected : graph[current]) {
                    // 이미 방문했는데 같은색이면 이분 그래프 불가
                    if (visited[connected] == visited[current]) {
                        isBipartite = false;
                        break;

                        // 방문 전이라면 다른 색으로 방문처리
                    } else if (visited[connected] == 0){
                        visited[connected] = -visited[current];
                        queue.offer(connected);
                    }
                }
            }
        }
        return isBipartite;
    }
}