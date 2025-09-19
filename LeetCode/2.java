/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

/**
 * title : Add Two Numbers
 * date : 2025-09-19
 */

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // dfs로 숫자만들기 > 더하기 > ListNode만들기
        String n1 = getNum(l1);
        String n2 = getNum(l2);
        String sum = addStrings(n1, n2);

        //  807 > 7부터 root
        ListNode node = makeListNode(sum);
        return node;
    }

    private ListNode makeListNode(String sum) {
        ListNode root = new ListNode(sum.charAt(sum.length() - 1) - '0');
        ListNode cur = root;

        for (int i = 1; i < sum.length(); i++) {
            ListNode nextNode = new ListNode(sum.charAt(sum.length() - i - 1) - '0');
            cur.next = nextNode;
            cur = nextNode;
        }

        return root;
    }

    private String getNum(ListNode node) {
        String num = "";
        if (node.next != null) {
            num = getNum(node.next);
        }

        return num + Integer.toString(node.val);
    }

    private String addStrings(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        int carry = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;

        while (i >= 0 || j >= 0 || carry > 0) {
            int digit1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int digit2 = j >= 0 ? num2.charAt(j) - '0' : 0;

            int sum = digit1 + digit2 + carry;
            result.append(sum % 10);
            carry = sum / 10;

            i--;
            j--;
        }

        return result.reverse().toString();
    }
}