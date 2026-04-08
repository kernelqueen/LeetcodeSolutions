class Solution {
    int mod = 1000_000_007;

    public int xorAfterQueries(int[] nums, int[][] queries) {
        int xor = 0;

        for (int[] q : queries) {
            int ind = q[0];   // starting index
            int r = q[1];     // ending index (inclusive)
            int k = q[2];     // step size (jump)
            long v = q[3];    // multiplier

            while (ind <= r) {
                // taking long value to keep it in range
                long curr = nums[ind];

                curr = (curr * v) % mod;

                nums[ind] = (int) curr;

                ind += k;
            }
        }

        // Compute XOR of all elements after processing queries
        for (int num : nums) {
            xor = xor ^ num;
        }

        return xor;
    }
}