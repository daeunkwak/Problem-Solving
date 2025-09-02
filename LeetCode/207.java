
/**
 * title : Course Schedule
 * date : 2025-09-01
 */
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // pre[after, before]
        // 모두 들을 수 있는지 return

        List<List<Integer>> pre = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            pre.add(new LinkedList<>());
        }
        int[] indegree = new int[numCourses];

        for (int i = 0; i < prerequisites.length; i++) {
            int before = prerequisites[i][1];
            int after = prerequisites[i][0];

            indegree[after]++;
            pre.get(before).add(after);
        }

        // 근데 여기서 pq일 필요가 있나?? 없음
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        int courseCnt = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            courseCnt++;

            for (int next : pre.get(course)) {
                indegree[next]--;
                if (indegree[next] == 0) queue.offer(next);
            }
        }

        return (courseCnt == numCourses);
    }
}