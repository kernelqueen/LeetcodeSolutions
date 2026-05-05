class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        // Edge case: empty list or single node
        if (head == null || head.next == null) return head;

        // Step 1: Find list length and tail node
        int length = 0;
        ListNode tail = null;
        ListNode curr = head;

        while (curr != null) {
            tail = curr;
            curr = curr.next;
            length++;
        }

        // Step 2: Normalize k and convert right-rotation to left-rotation offset
        k = k % length;
        int steps = (length - k) % length;

        if (steps == 0) return head;

        // Step 3: Find the new tail (node just before the new head)
        ListNode newTail = head;
        for (int i = 0; i < steps - 1; i++) {
            newTail = newTail.next;
        }

        // Step 4: Rewire pointers
        ListNode newHead = newTail.next;
        newTail.next = null;  // Break the list
        tail.next = head;     // Connect old tail to old head (form the rotation)

        return newHead;
    }
}