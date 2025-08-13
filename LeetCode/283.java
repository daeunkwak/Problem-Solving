import java.util.*;

/**
 * title : Move Zeros
 * date : 2025-08-10
 */
class Solution {
    public void moveZeroes(int[] nums) {
        // 정렬을 유지하면서 0을 끝으로 옮겨라
        // 배열 copy 없이

        // O(N)
        int zeroCnt = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                zeroCnt++;
            } else {
                nums[i - zeroCnt] = nums[i];
            }
        }
        for (int k = 0; k < zeroCnt; k++) {
            nums[nums.length - 1 -k] = 0;
        }
    }
}