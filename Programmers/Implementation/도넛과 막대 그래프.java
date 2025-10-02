import java.util.*;

/**
 * title : 도넛과 막대 그래프
 * date : 2025-10-01
 */
class Solution {
    public int[] solution(int[][] edges) {

        // 정점 개수를 알 수 없으니 map으로
        int maxV = 0;

        for (int i = 0; i < edges.length; i++) {
            int out = edges[i][0];
            int in = edges[i][1];
            maxV = Math.max(maxV, out);
            maxV = Math.max(maxV, in);
        }

        Map<Integer, List<Integer>> inMap = new HashMap<>();    // 들어오는 간선
        Map<Integer, List<Integer>> outMap = new HashMap<>();      // 나오는 간선
        for (int i = 0; i <= maxV; i++) {
            inMap.put(i, new ArrayList<>());
            outMap.put(i, new ArrayList<>());
            // *** 초기화해야 containsKey() 이런거 하기 편함
        }

        for (int i = 0; i < edges.length; i++) {
            int out = edges[i][0];
            int in = edges[i][1];
            inMap.get(in).add(out);
            outMap.get(out).add(in);
        }

        // 나가는 간선만 있는 정점 찾기
        int randomV = 0;
        for (int i = 1; i <= maxV; i++) {
            if (outMap.get(i).size() >= 2 && inMap.get(i).size() == 0) randomV = i;
        }

        // 각 그래프 판단하기
        // 도넛 > 임의 정점에서 탐색해서 임의 정점으로 다시 돌아오기
        // 막대 > 임의 정점에서 끝까지 탐색 > 나가는 간선만 or 들어오는 간선만 있는 노드가 존재
        // 8자 > 들2 나2 정점 찾기

        // 도넛, 막대, 8
        int[] result = new int[4];
        result[0] = randomV;
        for (int startV : outMap.get(randomV)) {
            // 무난하게 BFS로 탐색해그냥?
            boolean[] visited = new boolean[maxV + 1];
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(startV);
            visited[startV] = true;

            if (outMap.get(startV).size() == 2 && inMap.get(startV).size() == 3) {
                result[3]++;
                continue;
            }

            int cur = 0;
            boolean isEight = false;
            while (!queue.isEmpty()) {
                cur = queue.poll();

                // 8자 그래프 조건
                if (inMap.get(cur).size() == 2 && outMap.get(cur).size() == 2) {
                    isEight = true;
                    break;
                }

                for (int v : outMap.get(cur)) {
                    if (!visited[v]) {
                        queue.offer(v);
                        visited[v] = true;
                    }
                }
            }

            // 8자 그래프 판별
            if (isEight) {
                result[3]++;
                continue;
            }

            // 도넛 그래프 판별
            if (outMap.get(cur).size() == 1) {
                int v = outMap.get(cur).get(0);
                if (v == startV) {
                    result[1]++;
                    continue;
                }
            }

            // 막대 그래프 판별
            if (outMap.get(cur).size() == 0) {
                result[2]++;
                continue;
            }

        }

        return result;
    }
}