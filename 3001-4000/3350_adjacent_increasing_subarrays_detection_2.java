class Solution {
    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int max = 1; // Single element is always an increasing subarray of size 1

        int val[] = new int[n]; // val[i] = length of increasing subarray ending at index i
        val[0] = 1;

        int in = 1;

        // Build lengths of increasing subarrays
        while (in < n) {
            // Traverse as long as sequence is increasing
            while (in < n && nums.get(in) > nums.get(in - 1)) {
                val[in] = val[in - 1] + 1;
                in++;
            }

            // Reset count when sequence breaks
            if (in < n) {
                val[in] = 1;
            }

            in++;
        }

        // Calculate maximum valid increasing subarray length
        for (int i = 0; i < n; i++) {
            int prev = i - val[i]; // Index before the current increasing segment

            // If previous segment is at least as long, consider merging potential
            if (prev >= 0 && val[prev] >= val[i]) {
                max = Math.max(val[i], max);
            }

            // Also consider half-length overlap case
            max = Math.max(max, val[i] / 2);
        }

        return max;
    }
}
