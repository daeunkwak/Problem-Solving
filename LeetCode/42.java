
/**
 * title : Trapping Water
 * date : 2025-08-23
 */
class Solution {
    public int trap(int[] height) {
        // 완탐 O(N^2) = 4 * 10^8 -> 각 bar마다 왼쪽 최대 / 오른쪽 최대 구하기
        // 양쪽에서 최대를 찾아서 -> 더 낮은 bar를 기준으로 trap
        // 왼 -> 오, 오 -> 왼 각각 최대 갱신 -> merge

        // 0 1 0 2 1 0 1 3 2 1 2 1
        // 0 1 1 2 2 2 2 3 3 3 3 3
        // 3 3 3 3 3 3 3 3 2 2 2 1

        // 0 1 1 2 2 2 2 3 2 2 2 1
        // 0 0 1 0 1 2 1 0 0 1 0 0
        // 1 1 2 1 1 -> 6

        int n = height.length;

        int[] left = new int[n];
        int[] right = new int[n];

        int leftMax = height[0];
        int rightMax = height[n - 1];

        for (int i = 0; i < n; i++) {
            leftMax = Math.max(leftMax, height[i]);
            left[i] = leftMax;

            rightMax = Math.max(rightMax, height[n - i - 1]);
            right[n - i - 1] = rightMax;
        }

        // merge
        int[] merge = new int[n];
        int unitCnt = 0;
        for (int i = 0; i < n; i++) {
            merge[i] = Math.min(left[i], right[i]);
            unitCnt += merge[i] - height[i];
        }

        return unitCnt;
    }
}