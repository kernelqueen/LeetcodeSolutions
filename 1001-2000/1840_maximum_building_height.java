class Solution {
    public int maxBuilding(int n, int[][] nums) {

        // Let m = number of restricted buildings
        // Time:  O(m log m) for sorting + O(m) for the passes
        // Space: O(m)

        Arrays.sort(nums, (a, b) -> Integer.compare(a[0], b[0]));

        int len = nums.length;
        if (len == 0) return n - 1; // No restrictions, max height bounded only by edges

        // Check if the last building already has a restriction
        boolean isLast = nums[len - 1][0] == n;

        // Total points to process: restrictions + building 1 + (building n if not already included)
        int m = len + 1 + (isLast ? 0 : 1);

        int[][] h = new int[m][2]; // h[i] = {position, max height at position}
        h[0][0] = 1;
        h[0][1] = 0; // Building 1 has height 0

        // Left-to-right pass: propagate max possible height forward, capped by restrictions
        for (int i = 0; i < len; i++) {
            int diff = nums[i][0] - h[i][0];
            int ht = h[i][1] + diff;
            h[i + 1][0] = nums[i][0];
            h[i + 1][1] = Math.min(ht, nums[i][1]);
        }

        // Add building n if it wasn't part of the restrictions
        if (!isLast) {
            int diff = n - h[len][0];
            int ht = h[len][1] + diff;
            h[len + 1][0] = n;
            h[len + 1][1] = Math.min(ht, n - 1);
        }

        // Right-to-left pass: propagate max possible height backward, capped similarly
        for (int i = m - 2; i >= 0; i--) {
            int diff = h[i + 1][0] - h[i][0];
            int ht = h[i + 1][1] + diff;
            h[i][1] = Math.min(h[i][1], ht);
        }

        // For each consecutive pair of capped points, compute the max peak height achievable between them
        int ans = 0;
        for (int i = 1; i < m; i++) {
            int left = h[i - 1][0];
            int right = h[i][0];
            int h1 = h[i - 1][1];
            int h2 = h[i][1];

            // Peak height formula: average the "slack" distance and add to the taller endpoint
            int max = (right - left - Math.abs(h1 - h2)) / 2 + Math.max(h1, h2);
            ans = Math.max(ans, max);
        }

        return ans;
    }
}