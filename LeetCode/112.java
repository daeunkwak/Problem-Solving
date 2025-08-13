import java.util.*;

/**
 * title : Path Sum
 * date : 2025-08-10
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private static boolean flag;
    public boolean hasPathSum(TreeNode root, int targetSum) {
        flag = false;

        if (root == null) {
            return false;
        }

        dfs(root, root.val, targetSum);
        return flag;
    }

    private void dfs(TreeNode root, int curSum, int targetSum) {
        // 리프노드 조건
        if (curSum == targetSum && root.left == null && root.right == null) {
            flag = true;
            return;
        }

        if (root.left != null) {
            dfs(root.left, curSum + root.left.val, targetSum);
        }

        if (root.right != null) {
            dfs(root.right, curSum + root.right.val, targetSum);
        }
    }
}