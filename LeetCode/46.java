import java.util.*;

/**
 * title : Permutations
 * date : 2025-08-11
 */
class Solution {
    private static List<List<Integer>> result;
    public List<List<Integer>> permute(int[] nums) {
        // 모든 순열을 return
        // [1, 2, 3] -> List<List<Integer>> -> List<Integer>.size() == nums.length
        // 1 -> 2, 3
        // 2 -> 1, 3
        // 3 -> 1, 2

        result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            boolean[] visited = new boolean[nums.length];
            visited[i] = true;
            List<Integer> list = new ArrayList<>();
            list.add(nums[i]);

            dfs(nums, visited, list, 1);
        }
        return result;
    }

    private void dfs(int[] nums, boolean[] visited, List<Integer> curList, int depth) {
        if (depth == nums.length) {
            result.add(curList);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                List<Integer> newList = new ArrayList<>(curList);
                newList.add(nums[i]);

                dfs(nums, visited, newList, depth + 1);
                visited[i] = false;  // 백트래킹
            }
        }
    }
}