
import java.util.*;

/**
 * title : 택배 배송
 * date : 2025-08-01
 */
public class boj5972 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int cost = sc.nextInt();

            graph.get(start).add(new int[]{end, cost});
            graph.get(end).add(new int[]{start, cost});
        }

        // 1부터 N번 헛간까지 최소 비용 계산
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{1, 0});

        int[] costs = new int[N + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[1] = 0;

        boolean[] visited = new boolean[N + 1];
        visited[0] = true;

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int curNode = current[0];
            int curCost = current[1];

            if (visited[curNode]) {
                continue;
            }

            for (int[] node : graph.get(curNode)) {
                int nextNode = node[0];
                int nextCost = node[1];

                // 방문 전 && 최소가 갱신된 노드라면 큐에 넣고 탐색
                if (!visited[nextNode] && curCost + nextCost < costs[nextNode]) {
                    costs[nextNode] = curCost + nextCost;

                    // *** 큐에 넣을 때, 갱신된 거리를 넣어야함
                    pq.add(new int[]{nextNode, costs[nextNode]});
                }
            }

            visited[curNode] = true;
        }

        System.out.println(costs[N]);
    }
}