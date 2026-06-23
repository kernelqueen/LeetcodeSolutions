// ===================================================
// Solution 1: Recursive Memoization
// Time: O(n * m^2), Space: O(n * m) where m = r - l + 1
// ===================================================
class Solution {
    int mod = 1_000_000_007;
    int l, r, n;
    long[][][] memo; // memo[ind][prev - l][direction]

    public int zigZagArrays(int n, int l, int r) {
        this.l = l; this.r = r; this.n = n;
        memo = new long[n][r - l + 1][2];
        for (long[][] a : memo) for (long[] b : a) Arrays.fill(b, -1);

        long count = 0;
        for (int i = l; i <= r; i++) {
            count = (count + helper(1, i, true))  % mod; // next must be larger
            count = (count + helper(1, i, false)) % mod; // next must be smaller
        }
        return (int) count;
    }

    long helper(int ind, int prev, boolean nextLarger) {
        // Base case: last element — count valid choices directly
        if (ind == n - 1) {
            return nextLarger ? (r - prev) : (prev - l);
        }

        int k = nextLarger ? 1 : 0;
        if (memo[ind][prev - l][k] != -1) return memo[ind][prev - l][k];

        long count = 0;
        if (nextLarger) {
            // Next element must be strictly greater than prev
            for (int i = prev + 1; i <= r; i++)
                count = (count + helper(ind + 1, i, false)) % mod;
        } else {
            // Next element must be strictly smaller than prev
            for (int i = l; i < prev; i++)
                count = (count + helper(ind + 1, i, true)) % mod;
        }

        return memo[ind][prev - l][k] = count;
    }
}


// ===================================================
// Solution 2: Bottom-Up DP with Prefix Sums (Optimal)
// Time: O(n * m), Space: O(m) where m = r - l + 1
// ===================================================
class Solution {
    public int zigZagArrays(int n, int l, int r) {
        final int MOD = 1_000_000_007;
        int m = r - l + 1; // Total distinct values; index j maps to value j + l

        // Base layer: every single element is both an "up" and "down" start
        long[] up   = new long[m]; // up[j]   = ways ending at value j, expecting next to go down
        long[] down = new long[m]; // down[j] = ways ending at value j, expecting next to go up
        Arrays.fill(up, 1);
        Arrays.fill(down, 1);

        for (int i = 2; i <= n; i++) {
            // Prefix sum of down (left to right): used to fill newUp
            long[] preDown = new long[m + 1]; // preDown[j] = down[0] + ... + down[j-1]
            for (int j = 0; j < m; j++)
                preDown[j + 1] = (preDown[j] + down[j]) % MOD;

            // Suffix sum of up (right to left): used to fill newDown
            long[] sufUp = new long[m + 1]; // sufUp[j] = up[j] + ... + up[m-1]
            for (int j = m - 1; j >= 0; j--)
                sufUp[j] = (sufUp[j + 1] + up[j]) % MOD;

            long[] newUp   = new long[m];
            long[] newDown = new long[m];
            for (int j = 0; j < m; j++) {
                // newUp[j]: came from a down-step, so previous value must be < j
                newUp[j]   = preDown[j];
                // newDown[j]: came from an up-step, so previous value must be > j
                newDown[j] = sufUp[j + 1];
            }

            up = newUp;
            down = newDown;
        }

        // Sum all valid zigzag arrays across all ending values and directions
        long ans = 0;
        for (int j = 0; j < m; j++)
            ans = (ans + up[j] + down[j]) % MOD;
        return (int) ans;
    }
}