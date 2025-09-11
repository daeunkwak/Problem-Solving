
 /**
 * title : Count Nodes Equal to Average of Subtree
 * date : 2025-09-03
 */
class Solution {
    private int cnt;
    public int averageOfSubtree(TreeNode root) {
        // 서브트리 값들의 평균 == root 경우의수 구하기
        cnt = 0;
        getAvg(root);
        return cnt;
    }

    private int[] getAvg(TreeNode node) {
        // 서브트리 노드 개수, 합 구하기
        if (node == null) return new int[]{0, 0};

        int[] leftSub = getAvg(node.left);
        int[] rightSub = getAvg(node.right);

        int nodeCnt = leftSub[0] + rightSub[0] + 1;
        int sum = leftSub[1] + rightSub[1] + node.val;
        if (sum / nodeCnt == node.val) cnt++;

        return new int[]{nodeCnt, sum};
    }
}