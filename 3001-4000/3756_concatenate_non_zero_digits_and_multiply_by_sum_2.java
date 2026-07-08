class Solution {
    int mod = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        // Time: O(n + q), Space: O(n + q)
        long[] presum = new long[n + 1]; // presum[i]  = prefix digit sum up to index i (exclusive)
        long[] conval = new long[n + 1]; // conval[i]  = concatenated numeric value of non-zero digits up to i
        int[]  count  = new int[n + 1];  // count[i]   = number of non-zero digits up to index i
        long[] pow10  = new long[n + 1]; // pow10[i]   = 10^i % mod

        for (int i = 1; i <= n; i++) {
            int num = s.charAt(i - 1) - '0';

            presum[i] = (presum[i - 1] + num) % mod;

            if (num == 0) {
                // Zero digits don't contribute to the concatenated value
                conval[i] = conval[i - 1];
                count[i]  = count[i - 1];
            } else {
                conval[i] = (conval[i - 1] * 10 + num) % mod;
                count[i]  = count[i - 1] + 1;
            }
        }

        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % mod;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0], right = queries[i][1];

            // Sum of digits in [left, right]
            long sum = (presum[right + 1] - presum[left] + mod) % mod;

            // Extract concatenated value of non-zero digits in [left, right]
            // by stripping the left prefix from the right prefix:
            // range = conval[right+1] - conval[left] * 10^(non-zero digit count in range)
            long leftpart = conval[left];
            long rightpart = conval[right + 1];
            int  diff      = count[right + 1] - count[left]; // non-zero digit count in range
            long power     = pow10[diff];
            long range     = (rightpart - (leftpart * power) % mod + mod) % mod;

            ans[i] = (int) (range * sum % mod);
        }

        return ans;
    }
}