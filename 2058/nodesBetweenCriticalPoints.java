class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        // We need at least 3 nodes to have a critical point
        if (head == null || head.next == null || head.next.next == null) {
            return new int[] { -1, -1 };
        }

        ListNode prev = head;
        ListNode curr = head.next;

        int index = 1;

        // Position of first critical point
        int firstCritical = -1;

        // Position of previous critical point
        int prevCritical = -1;

        int minDistance = Integer.MAX_VALUE;

        while (curr.next != null) {

            ListNode next = curr.next;

            // Check whether curr is a local maximum or minimum
            boolean isCritical = (curr.val > prev.val && curr.val > next.val) ||
                    (curr.val < prev.val && curr.val < next.val);

            if (isCritical) {

                // First critical point
                if (firstCritical == -1) {
                    firstCritical = index;
                } else {
                    // Distance from previous critical point
                    minDistance = Math.min(
                            minDistance,
                            index - prevCritical);
                }

                // Current critical point becomes previous
                prevCritical = index;
            }

            prev = curr;
            curr = next;
            index++;
        }

        // Fewer than two critical points
        if (firstCritical == prevCritical) {
            return new int[] { -1, -1 };
        }

        // Maximum distance = last critical - first critical
        int maxDistance = prevCritical - firstCritical;

        return new int[] { minDistance, maxDistance };
    }
}