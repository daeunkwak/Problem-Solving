
/**
 * title : Binary Tree Zigzag Level Order Traversal
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        // 각 뎁스별로 left -> right, right -> left 번갈아가면서 출력
        // (l -> r) root
        // (r -> l) root.right -> root.left
        // (l -> r) root.left.left -> root.left.right -> root.right.left -> root.right.right

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> deque = new LinkedList<>();
        deque.offerFirst(root);
        boolean flag = true;    // true : l -> r, false : r -> l

        while (!deque.isEmpty()) {
            List<Integer> curList = new ArrayList<>();
            int curSize = deque.size();

            // offerFirst() -> 15, 7 -> 다음 뎁스에서는 왼쪽부터 읽음 -> pollFirst()
            // offerFirst() -> 7, 15 -> 다음 뎁스에서는 오른쪽부터 읽음 -> pollFirst()

            for (int i = 0; i < curSize; i++) {
                // TreeNode curNode = deque.pollFirst();
                // System.out.println("val : " + curNode.val);
                // curList.add(curNode.val);

                if (flag) {  // l -> r
                    TreeNode curNode = deque.pollFirst();
                    curList.add(curNode.val);

                    if (curNode.left != null) {
                        deque.offerLast(curNode.left);
                    }
                    if (curNode.right != null) {
                        deque.offerLast(curNode.right);
                    }
                } else {
                    // 20 9
                    TreeNode curNode = deque.pollLast();
                    curList.add(curNode.val);

                    if (curNode.right != null) {
                        deque.offerFirst(curNode.right);
                    }
                    if (curNode.left != null) {
                        deque.offerFirst(curNode.left);
                    }
                }
            }
            result.add(curList);
            if (flag) {
                flag = false;
            } else {
                flag = true;
            }
        }
        return result;
    }
}