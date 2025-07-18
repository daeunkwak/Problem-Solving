import java.util.*;

/**
 * title : 프로그래머스 - 가장 먼 노드
 * date : 2025-04-16
 */
class Solution {
    int cnt = 0;
    int depth = 0;
    public int solution(int n, int[][] edge) {
        int answer = 0;

        // 인접리스트
        List[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            List<Integer> list = new ArrayList<>();
            graph[i] = list;
        }

        for (int i = 0; i < edge.length; i++) {
            int from = edge[i][0] - 1;
            int to = edge[i][1] - 1;
            graph[from].add(to);
            graph[to].add(from);
        }

        // bfs
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();

        // *** 1번 노드 넣기 ***
        // for (int i = 0; i < graph[0].size(); i++) {
        //     List<Integer> firstList = graph[0];
        //     queue.add(firstList.get(i));
        // }
        queue.add(0);
        visited[0] = true;

        int curNodeCnt = 0;
        while (!queue.isEmpty()) {
            curNodeCnt = queue.size();
            if (curNodeCnt != 0) {
                cnt = curNodeCnt;
            }
            for (int k = 0; k < curNodeCnt; k++) {
                int curNode = queue.poll();
                List<Integer> curList = graph[curNode];

                for (int i = 0; i < graph[curNode].size(); i++) {
                    if (!visited[curList.get(i)]) {
                        queue.add(curList.get(i));
                        visited[curList.get(i)] = true;
                    }
                }

            }
        }
        if (curNodeCnt != 0) {
            cnt = curNodeCnt;
        }

        return cnt;
    }

}