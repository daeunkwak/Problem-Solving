import java.util.*;

/**
 * title : Search Insert Position
 * date : 2025-08-11
 */
class Solution {
    public int searchInsert(int[] nums, int target) {
        // 정렬된 중복없는 배열 -> target을 찾으면 index return
        // target 없으면 들어갈 index return
        // O(logn) -> 이분탐색으로 인덱스 찾기

        int start = 0;
        int end = nums.length - 1;

        // 1 3 5 6 -> 5, 2, 7
        // start > end가 되면 종료 -> 그 때 start idx가 들어갈 위치가 된다
        while (start <= end) {
            int mid = start + (end - start) / 2;
            // System.out.println("mid : " + mid + " target : " + target);
            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                return mid;
            }
        }

        return start;
    }
}