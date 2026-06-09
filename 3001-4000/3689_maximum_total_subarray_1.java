class Solution {
    public long maxTotalValue(int[] nums, int k) {
        long min = Long.MAX_VALUE, max = Long.MIN_VALUE ;

        for(int num : nums) {
            // O(n)
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        // (max-min)
        return k*(max-min);
    }
}