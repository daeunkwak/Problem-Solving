import java.util.*;

/**
 * title : Find Minimum in Rotated Sorted Array
 * date : 2025-08-11
 */
class Solution {
    public int findMin(int[] nums) {
        // rotated 1 ~ n times 오른쪽으로 한 칸씩 이동
        // 정렬된 nums, 중복된 원소 없음 -> 배열의 최소값 (rotated 이전의 index 0 구하기)
        // O(logn) -> 이분탐색 활용

        // 4 5 6 7 0 1 2
        // if (nums[mid] > nums[end]) -> start = mid + 1
        // 7 0 1 2 4 5 6 7
        // if (nums[start] > nums[mid]) -> end = mid - 1
        // 0 1 2 4 5 6 7
        // if (정상) -> end = mid - 1;

        int start = 0;
        int end = nums.length - 1;
        while (start < end) {
            int mid = (start + end) / 2;
            if (nums[mid] < nums[end]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return nums[start];
    }
}