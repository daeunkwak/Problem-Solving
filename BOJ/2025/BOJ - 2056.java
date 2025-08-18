package just.p_0818;

import java.util.*;

/**
 * title : 작업
 * date : 2025-08-18
 */
public class boj2056 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] times = new int[N + 1];
        int[] indegree = new int[N + 1];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        Queue<Integer> queue = new LinkedList<>();

        // ***** 결과배열에 시작 작업들의 완료시간으로 초기화
        int[] result = new int[N + 1];

        // 작업시간, 선행 작업의 개수, 작업들의 번호
        for (int i = 1; i <= N; i++) {
            times[i] = sc.nextInt();
            indegree[i] = sc.nextInt();
            for (int j = 0; j < indegree[i]; j++) {
                int pre = sc.nextInt();
                graph.get(pre).add(i); // **** 선행작업 -> i 이게 정방향
            }

            if (indegree[i] == 0) {
                queue.add(i);
                result[i] = times[i];
            }
        }

        // 위상정렬
        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int next : graph.get(current)) {
                indegree[next]--;

                // 선행과목을 합한 수강시간
                result[next] = Math.max(result[next], result[current] + times[next]);
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            maxTime = Math.max(maxTime, result[i]);
        }

        System.out.println(maxTime);
    }
}
