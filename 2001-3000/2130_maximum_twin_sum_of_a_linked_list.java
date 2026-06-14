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
    public int pairSum(ListNode head) {
        ListNode slow = head, fast = head;

        while(fast!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // slow will have start of second half
        ListNode revhead = reverse(slow);
        ListNode temp1 = head, temp2 = revhead;

        int ans = 0;
        while(temp2!=null) {
            ans = Math.max(temp1.val + temp2.val, ans);
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        return ans;
        
    }

    ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode next = null;
        ListNode curr = head;
        while(curr!=null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}