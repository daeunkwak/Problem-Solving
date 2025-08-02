
import java.util.*;

/**
 * title : 집에 빨리 가고 싶어!
 * date : 2025-08-02
 */
public class boj23033 {
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
            int costTime = sc.nextInt();
            int term = sc.nextInt();

            graph.get(start).add(new int[]{end, costTime, term});
            // graph.get(end).add(new int[]{start, costTime, term});
            // 단방향임..제발
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[]{1, 0, 0});

        int[] costs = new int[N + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);

        boolean[] visited = new boolean[N + 1];

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curNode = cur[0];
            int curTime = cur[1];
            // System.out.println("현재 node : " + curNode + " time : " + curTime);

//            if (visited[curNode]) {
//                continue;
//            }

            if (curTime > costs[curNode]) {
                continue;
            }

            for (int[] node : graph.get(curNode)) {
                int nextNode = node[0];
                int nextTime = node[1];
                int nextTerm = node[2];

                // *** 소요 시간 + 다음 버스를 기다리는 시간 계산 ***
                int nextBusTime = ((curTime + nextTerm - 1) / nextTerm) * nextTerm;
                int totalTime = nextBusTime + nextTime;

                if (costs[nextNode] > totalTime) {
                    // System.out.println("갱신 node : " + nextNode + " time : " + costTime);
                    pq.offer(new int[]{nextNode, totalTime, nextTerm});
                    costs[nextNode] = totalTime;
                }
            }
            // visited[curNode] = true;
        }

        System.out.println(costs[N]);
    }
}
