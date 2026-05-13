class Solution {
    // Cost curve per pair (a = min, b = max):
    // [2, a] → 2 ops | [a+1, a+b-1] → 1 op | [a+b] → 0 ops | [a+b+1, b+lim] → 1 op | [b+lim+1, 2*lim] → 2 ops
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        // +2 because sentinel write cost[b+limit+1] reaches 2*limit+1 when b == limit
        int[] cost = new int[2 * limit + 2];

        for (int i = 0; i < n / 2; i++) {
            int a = Math.min(nums[i], nums[n - 1 - i]);
            int b = Math.max(nums[i], nums[n - 1 - i]);
            // Difference array: mark boundaries of the five cost regions
            cost[2]         += 2;  cost[a+1]       -= 2;
            cost[a+1]       += 1;  cost[a+b]       -= 1;
            cost[a+b+1]     += 1;  cost[b+limit+1] -= 1;
            cost[b+limit+1] += 2;
        }

        // Prefix sum to recover total ops per target sum c; track minimum
        int ans = n, curr = 0;
        for (int c = 2; c <= 2 * limit; c++) {
            curr += cost[c];
            ans   = Math.min(curr, ans);
        }
        return ans;
    }
}