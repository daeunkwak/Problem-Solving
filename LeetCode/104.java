import java.util.*;

/**
 * title : Maximum Depth of Binary Tree
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
    private static int maxDepth;
    public int maxDepth(TreeNode root) {
        maxDepth = 0;

        // *** 입력값의 범위 확인
        if (root == null) {
            return 0;
        }

        dfs(root, 1);
        return maxDepth;
    }

    private void dfs(TreeNode root, int depth) {
        if (root.left == null && root.right == null) {
            maxDepth = Math.max(maxDepth, depth);
            return;
        }

        if (root.left != null) {
            dfs(root.left, depth + 1);
        }

        if (root.right != null) {
            dfs(root.right, depth + 1);
        }
    }
}