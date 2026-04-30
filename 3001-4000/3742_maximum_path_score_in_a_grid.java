class Solution {
    int m, n;
    Integer dp[][][];
    // O(m*n*k)
    public int maxPathScore(int[][] grid, int k) {
        m = grid.length;
        n = grid[0].length;
        dp = new Integer[m][n][k+1];
        return helper(0, 0, grid, k);
    }

    int helper(int r, int c, int[][] grid, int k) {
        if(r>=m || c>=n || k<0)
            return -1; // invalid path
        int val = grid[r][c];
        boolean anyCost = val > 0;

        if(anyCost && k<=0)
            return -1; // invalid path
        
        // last cell
        if(r==m-1 && c==n-1)
            return val;
        if(dp[r][c][k]!=null)
            return dp[r][c][k];
        int nextK = k;
        if(val > 0)
            nextK = k-1;
        int right = helper(r, c+1, grid, nextK);
        int down = helper(r+1, c, grid, nextK);
        if(right==-1 && down==-1)
            return dp[r][c][k] = -1;
        return dp[r][c][k] = val + Math.max(right, down);
    }
}