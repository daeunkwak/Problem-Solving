
/**
 * title : Course Schedule II
 * date : 2025-08-18
 */
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // [after, before]
        // graph에는 i번째 과목을 수강하면 -> 들을 수 있는 과목들 저장
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // 들어야 하는 선행과목의 개수 저장
        int[] indegree = new int[numCourses];

        for (int i = 0; i < prerequisites.length; i++) {
            int after = prerequisites[i][0];
            int before = prerequisites[i][1];

            indegree[after]++;

            graph.get(before).add(after);
        }

        Queue<Integer> queue = new LinkedList<>();
        int[] result = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        int idx = 0;
        while (!queue.isEmpty()) {
            int now = queue.poll();
            result[idx] = now;
            idx++;

            for (int next : graph.get(now)) {
                indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        // idx로 사이클 감지
        return idx == numCourses ? result : new int[0];
    }
}