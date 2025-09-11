import java.util.*;

/**
 * title : 무지의 먹방 라이브
 * date : 2025-09-11
 */
class Solution {
    public int solution(int[] food_times, long k) {
        int answer = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        long total = 0;
        for (int i = 0; i < food_times.length; i++) {
            pq.offer(new int[]{food_times[i], i + 1});
            total += food_times[i];
        }

        if (k >= total) return -1;

        long timeUsed = 0;  // 지금까지 사용된 시간
        long prevTime = 0;  // 이전에 제거된 음식의 시간

        while (!pq.isEmpty()) {
            int[] cur = pq.peek();
            int curTime = cur[0];

            // 추가로 몇초가 더 걸리는지
            long cycleTime = (curTime - prevTime) * pq.size();

            if (timeUsed + cycleTime <= k) {
                timeUsed += cycleTime;
                prevTime = curTime;
                pq.poll();
            } else {  // 시간 내에 못먹는 경우
                break;
            }
        }

        // 남은 음식들 -> 무조건 먹을 수 있음
        List<int[]> remainFoods = new ArrayList<>(pq);
        remainFoods.sort((a, b) -> a[1] - b[1]);

        // 남은 시간으로 몇 번째 음식인지 계산
        long remainTime = k - timeUsed;
        int targetIdx = (int)(remainTime % remainFoods.size());

        return remainFoods.get(targetIdx)[1];
    }
}