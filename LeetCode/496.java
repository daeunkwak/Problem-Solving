
/**
 * title : Next Greater Element I
 * date : 2025-08-17
 */
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // nums1의 원소 각각 -> nums2에서, 오른쪽에 더 큰 값이 존재하는지 확인 -> 있으면 값 반환, 없으면 -1 반환
        // [4, 1, 2] [1, 3, 4, 2] -> [-1, 3, -1]

        // stack으로 nums2의 next greater element 배열 채우기
        // nums2 순회하면서 배열 값 확인 -> O(n) + O(n x m)
        // O(n x m)을 어떻게 줄이지 -> hashmap 활용

        Map<Integer, Integer> map = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= nums2[i]) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                map.put(nums2[i], stack.peek());
            } else {
                map.put(nums2[i], -1);
            }
            stack.push(nums2[i]);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = map.get(nums1[i]);
        }

        return result;
    }
}