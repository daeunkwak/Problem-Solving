
/**
 * title : Product of Array Except Self
 * date : 2025-08-15
 */
class Solution {
    public int[] productExceptSelf(int[] nums) {

        // nums[i] 제외한 모든 원소 곱 구하기 -> int[]
        // 곱해야하는 방향은, 0 ~ i-1 // i+1 ~ n 이렇게 두 덩어리
        // 따라서 배열 2개 만들고 왼 -> 오, 오 -> 왼 곱 저장

        // 1 2 3 4
        // -->   <-

        int size = nums.length;
        int[] leftProduct = new int[size];
        int[] rightProduct = new int[size];

        int left = 1;
        int right = 1;
        for (int i = 0; i < size; i++) {
            leftProduct[i] = left;
            rightProduct[size - 1 - i] = right;

            if (i < size - 1) {
                left *= nums[i];
                right *= nums[size - 1 - i];
            }
        }

        int[] answer = new int[size];
        answer[0] = rightProduct[size - 1];
        answer[size - 1] = leftProduct[size - 1];

        for (int i = 0; i < size; i++) {
            answer[i] = leftProduct[i] * rightProduct[i];
        }

        return answer;
    }
}