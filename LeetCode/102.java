
/**
 * title : Binary Tree Level Order Traversal
 * date : 2025-08-12
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
    public List<List<Integer>> levelOrder(TreeNode root) {
        // root
        // root.left -> root.right
        // root.left.left -> root.left.right -> root.right.left- > root.right.right
        // root.left.left.left -> root.left.left.right

        // BFS -> 현재 레벨의 노드에서 갈 수 있는 자식노드들을 다음 레벨에서 순회

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            List<Integer> curList = new ArrayList<>();
            // System.out.println("------");

            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode curNode = queue.poll();
                curList.add(curNode.val);

                if (curNode.left != null) {
                    queue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    queue.offer(curNode.right);
                }
            }

             result.add(curList);
        }

        return result;
    }
}