class Solution {
    int[][] grid;
    int k;
    int m, n;
    Integer dp[][][];
    int mod = 1_000_000_007;

    public int numberOfPaths(int[][] grid, int k) {
        this.grid = grid;
        this.k = k;
        m = grid.length;
        n = grid[0].length;
        dp = new Integer[m][n][k];
        return helper(0, 0, 0); // i, j, sum
        // O(m*n*k)
        // O(m*n*k)
    }

    private int helper(int i, int j, int sum) {
        if (i >= m || j >= n) return 0;

        sum = (sum + grid[i][j]) % k;
        // last cell
        if (i == m - 1 && j == n - 1) {
            return sum%k==0 ? 1 : 0; // one path is found
        }

        if(dp[i][j][sum%k]!=null) {
            return dp[i][j][sum%k];
        }

        int right = helper(i, j + 1, sum%k);
        int down = helper(i + 1, j, sum%k);
        return dp[i][j][sum%k] = (right + down)%mod;
         
    }
}
