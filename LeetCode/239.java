
/**
 * title : Sliding Window Maximum
 * date : 2025-09-11
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];

        // pq > 최대힙 > 윈도우 벗어나면 계속 자르기
        // 1 3 -1 -3 5 3 6 7
        // 1 3 -1 > 3 1 -1 (3) > 3 1 -1 -3 (3) > 5 ...
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        for (int i = 0; i < k; i++) pq.offer(new int[]{nums[i], i});

        int minIdx = 0;
        int resIdx = 0;
        int windowMax = 0;
        int[] cur;
        for (int i = k; i <= nums.length; i++) {
            while (true) {
                cur = pq.peek();
                if (cur[1] >= minIdx) {
                    windowMax = cur[0];
                    break;
                } else {
                    pq.poll();
                }
            }
            result[resIdx] = windowMax;
            resIdx++;

            if (i < nums.length) {
                pq.offer(new int[]{nums[i], i});
                minIdx++;
            }
        }

        return result;
    }
}