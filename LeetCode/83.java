import java.util.*;

/**
 * title : Remove Duplicates from Sorted List
 * date : 2025-08-10
 */

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
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        // search before listNode.next == null
        // if (nextNode.val == val ) -> nextNode.nextNode. ... before different or end
        if (head == null) return head;

        ListNode current = head;

        // *** current null 체크를 하고 반복문에 들어가야함 (current.next != null XXX)
        while (current != null) {
            if (current.next != null && current.val == current.next.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return head;
    }
}