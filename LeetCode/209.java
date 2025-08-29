
/**
 * title : Minimum Size Subarray Sum
 * date : 2025-08-27
 */
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        // subarray의 합이 target보다 크거나 같은 subarray의 최소 길이 구하기
        // 2 3 1 2 4 3, 7
        // 2 3 1 2 -> 3 1 2 -> 3 1 2 4 -> 1 2 4 -> 2 4 3 -> 4 3 -> 3 7
        int start = 0;
        int end = 0;

        int sum = nums[0];
        int minLength = Integer.MAX_VALUE;

        // 2 3 1 2 4 3
        // 2 3 1 2
        while (true) {
            if (sum >= target) {
                minLength = Math.min(minLength, end - start + 1);
                sum -= nums[start];
                start++;
            } else {
                if (end + 1 < nums.length) {
                    end++;
                    sum += nums[end];
                } else {
                    break;
                }
            }
        }

        return (minLength == Integer.MAX_VALUE) ? 0 : minLength;
    }
}