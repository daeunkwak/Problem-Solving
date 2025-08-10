import java.util.*;

/**
 * title : 두 큐 합 같게 만들기
 * date : 2025-03-07
 */

class Solution {
    public int solution(int[] queue1, int[] queue2) {

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        long sum1 = 0;
        long sum2 = 0;

        for (int i = 0; i < queue1.length; i++) {
            q1.add(queue1[i]);
            sum1 += queue1[i];
        }
        for (int i = 0; i < queue2.length; i++) {
            q2.add(queue2[i]);
            sum2 += queue2[i];
        }

        // 합 맞추기
        int polled = 0;
        int answer = 0;
        boolean flag = false;
        while (answer < 100000000) {
            if (sum1 > sum2) {
                polled = q1.poll();
                q2.offer(polled);

                sum1 -= polled;
                sum2 += polled;
            } else if (sum1 < sum2) {
                polled = q2.poll();
                q1.offer(polled);

                sum1 += polled;
                sum2 -= polled;
            } else {
                flag = true;
                break;
            }

            answer += 1;
        }

        if (!flag) {
            return -1;
        }

        return answer;
    }
}