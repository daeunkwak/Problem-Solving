
/**
 * title : Majority Element
 * date : 2025-08-17
 */
class Solution {
    public int majorityElement(int[] nums) {
        // n / 2번보다 많이 등장한 원소 반환하기

        // hashmap을 사용하면 시간은 O(n), 공간은 O(n)
        // 정렬해서 세면 시간은 O(nlogn), 공간은 O(1)
        // 시간 O(n) + 공간 O(1)이 되려면 ?????

        Arrays.sort(nums);
        int cnt = 1;
        int curNum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == curNum) {
                cnt++;
            } else {
                if (cnt > nums.length / 2) {
                    return curNum;
                }
                curNum = nums[i];
                cnt = 1;
            }
        }
        return curNum;
    }
}