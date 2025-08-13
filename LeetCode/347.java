import java.util.*;

/**
 * title : Top K Frequent Elements
 * date : 2025-08-11
 */
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // 가장 많이 등장하는 k개의 수 return
        // 빈도수 세기

        // 정렬 + 빈도수 세기(pq) -> O(nlogn) + O(nlogk) = O(nlogn)
        // 1 1 1 2 2 3
        Arrays.sort(nums);
        // [개수, 값]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        int start = 0;
        int end = 1;
        int cnt = 1;
        int curNum = nums[0];
        while (end < nums.length) {
            if (nums[end] == curNum) {
                cnt++;
            } else {
                pq.offer(new int[]{cnt, curNum});
                if (pq.size() > k) {
                    pq.poll();
                }
                curNum = nums[end];
                cnt = 1;
            }
            end++;
        }

        // 남은것들까지 처리 !!!!!
        pq.offer(new int[]{cnt, curNum});
        if (pq.size() > k) {
            pq.poll();
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pq.poll()[1];
        }

        return result;
    }
}