class Solution {
    // Cost curve per pair (a = min, b = max of the pair):
    // c in [2,    a    ] → 2 ops (both elements out of range for target c)
    // c in [a+1,  a+b-1] → 1 op  (only the smaller needs changing)
    // c =        a+b    → 0 ops (pair already sums to c, no change needed)
    // c in [a+b+1,b+lim ] → 1 op  (only the larger needs changing)
    // c in [b+lim+1,2*lim]→ 2 ops (both elements out of range again)
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;

        // Size 2*limit+2: indices 0..2*limit+1.
        // The extra +2 is needed because the sentinel write cost[b+limit+1]
        // reaches index 2*limit+1 when b == limit (the maximum possible value).
        int[] cost = new int[2 * limit + 2];

        // Process each mirror pair (i, n-1-i). Every element is covered. O(n)
        for (int i = 0; i < n / 2; i++) {
            int a = Math.min(nums[i], nums[n - 1 - i]);
            int b = Math.max(nums[i], nums[n - 1 - i]);

            // Region [2, a]: cost is 2. Mark start; undo at a+1.
            cost[2]   += 2;
            cost[a+1] -= 2;

            // Region [a+1, a+b-1]: cost is 1. Mark start; undo at a+b.
            cost[a+1] += 1;
            cost[a+b] -= 1;

            // Region [a+b+1, b+limit]: cost is 1. Mark start; sentinel write at b+limit+1.
            // When b == limit, b+limit+1 == 2*limit+1 (outside sweep range — never read).
            cost[a+b+1]     += 1;
            cost[b+limit+1] -= 1;

            // Restore cost to 2 beyond b+limit (same sentinel index absorbs this).
            cost[b+limit+1] += 2;
        }

        // Prefix sum over valid target sums c in [2, 2*limit].
        // curr accumulates total ops needed across all pairs if every pair targets c.
        // Track the minimum. O(limit)
        int ans  = n; // upper bound: at most n/2 pairs × 2 ops each = n
        int curr = 0;
        for (int c = 2; c <= 2 * limit; c++) {
            curr += cost[c];
            ans   = Math.min(curr, ans);
        }

        return ans;
    }
}