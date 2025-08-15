import java.util.*;

/**
 * title : Two Sum
 * date : 2025-08-15
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 2 7 11 15 -> 9
        // hashSet -> (target - currentNum) 에 해당하는 수가 있는지 확인
        // *** 근데 인덱스를 같이 저장해야 하므로 hashmap이 나을듯

        Map<Integer, Integer> map = new HashMap<>();
        int[] answer = new int[2];

        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if (map.containsKey(target - cur)) {
                answer[0] = i;
                answer[1] = map.get(target - cur);
                return answer;
            } else {
                map.put(nums[i], i);
            }
        }
        return answer;
    }
}