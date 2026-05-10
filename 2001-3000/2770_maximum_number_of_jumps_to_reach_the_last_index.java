/**
 * LeetCode 2770 - Maximum Number of Jumps to Reach the Last Index
 *
 * From index i, you can jump to j if |nums[j] - nums[i]| <= target.
 * Return the maximum number of jumps to reach the last index, or -1 if unreachable.
 *
 * Time:  O(n^2)  |  Space: O(n)
 */
class MaximumJumps {

    // -------------------------------------------------------------------------
    // Approach 1: Top-down DP (Memoized Recursion)
    // -------------------------------------------------------------------------

    Integer[] dp;

    public int maximumJumpsTopDown(int[] nums, int target) {
        dp = new Integer[nums.length];
        return helper(0, nums, target);
    }

    private int helper(int ind, int[] nums, int target) {
        if (ind == nums.length - 1) return 0;
        if (dp[ind] != null) return dp[ind];

        int jumps = -1;
        for (int i = ind + 1; i < nums.length; i++) {
            if (Math.abs(nums[i] - nums[ind]) <= target) {
                int next = helper(i, nums, target);
                if (next != -1)
                    jumps = Math.max(jumps, next + 1);
            }
        }

        return dp[ind] = jumps;
    }

    // -------------------------------------------------------------------------
    // Approach 2: Bottom-up DP (Iterative)
    // Iterate right to left; dp[i] = max jumps from i to last index (-1 if unreachable)
    // -------------------------------------------------------------------------

    public int maximumJumpsBottomUp(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        dp[n - 1] = 0;

        for (int ind = n - 2; ind >= 0; ind--) {
            for (int i = ind + 1; i < n; i++) {
                if (Math.abs(nums[i] - nums[ind]) <= target && dp[i] != -1)
                    dp[ind] = Math.max(dp[ind], 1 + dp[i]);
            }
        }

        return dp[0];
    }
}