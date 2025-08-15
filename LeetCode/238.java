
/**
 * title : Product of Array Except Self
 * date : 2025-08-15
 */
class Solution {
    public int[] productExceptSelf(int[] nums) {
        // answer[i] : nums[i]를 제외한 모든 원소들의 곱
        // 나누기 연산 없이 O(n)에 구현

        // 1 2 3 4
        // -->   <-

        int[] leftProducts = new int[nums.length];
        int products = 1;
        for (int i = 0; i < nums.length; i++) {
            products *= nums[i];
            leftProducts[i] = products;
        }

        products = 1;
        int[] rightProducts = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            products *= nums[i];
            rightProducts[i] = products;
        }

        int[] answer = new int[nums.length];

        // ***** 길이 확인
        answer[0] = (nums.length > 1) ? rightProducts[1] : 1;
        answer[nums.length  - 1] = (nums.length  > 1) ? leftProducts[nums.length  - 2] : 1;

        for (int i = 1; i < nums.length - 1; i++) {
            answer[i] = leftProducts[i - 1] * rightProducts[i + 1];
        }

        return answer;
    }
}