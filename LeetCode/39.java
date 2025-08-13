import java.util.*;

/**
 * title : Combination Sum
 * date : 2025-08-11
 */
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();
        dfs(0, target, 0, new ArrayList<>(), candidates, result);
        return result;
    }

    private void dfs(int start, int target, int curSum, List<Integer> curList, int[] candidates, List<List<Integer>> result) {
        if (curSum == target) {
            result.add(new ArrayList<>(curList));
            return;
        }

        if (curSum > target) {
            return;
        }

        // startIdx를 설정해서 3, 3, 3은 가능하되 3, 3, 2 이렇게 중복된 조합이 또 나오지는 않게 해야함
        for (int i = start; i < candidates.length; i++) {
            curList.add(candidates[i]);
            dfs(i, target, curSum + candidates[i], curList, candidates, result);
            curList.remove(curList.size() - 1);  // 백트래킹
        }
    }
}