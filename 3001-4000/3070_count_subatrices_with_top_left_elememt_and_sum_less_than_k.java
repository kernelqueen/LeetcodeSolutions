class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int presum[][] = new int[m+1][n+1]; // O(m*n)
        int ans = 0;
        // O(m*n)
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                presum[i][j] = grid[i-1][j-1] +
                presum[i][j-1] + presum[i-1][j] -
                presum[i-1][j-1];
                if(presum[i][j] <=k)
                    ans++;
                else break;
            }
        }
        return ans;
    }
}