import java.util.*;

/**
 * title : Kth Largest Element in a Stream
 * date : 2025-08-10
 */
class KthLargest {

    // tracking real-time of kth highest score
    private int k;
    private PriorityQueue<Integer> pq;

    public KthLargest(int k, int[] nums) {
        // return kth highest score
        this.k = k;

        pq = new PriorityQueue<>(k);
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
    }

    public int add(int val) {
        // add and return kth
        pq.offer(val);
        if (pq.size() > k) {
            pq.poll();
        }

        return pq.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */