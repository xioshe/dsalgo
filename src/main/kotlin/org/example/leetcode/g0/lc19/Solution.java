package org.example.leetcode.g0.lc19;

import org.example.common.ListNode;

public class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        var dummy = new ListNode(-1, head);
        var left  = dummy;
        var right = head;
        for (int i = 0; i < n; i++) {
            right = right.next;
        }
        while (right != null) {
            right = right.next;
            left = left.next;
        }
        left.next = left.next.next;
        return dummy.next;
    }
}
