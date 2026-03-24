class Solution {
    public int[][] constructProductMatrix(int[][] grid) {
        int mod = 12345;
        int n = grid.length;
        int m = grid[0].length;
        int ans[][] = new int[n][m];

        long left = 1, right = 1;
        // O(n*m)
        // O(n*m) - ans matrix
        // top to bottom
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                ans[i][j] = (int)left;
                left = (left*grid[i][j])%mod;
            }
        }

        // bottom to top traversal

        for(int i=n-1; i>=0; i--) {
            for(int j=m-1; j>=0; j--) {
                ans[i][j] = (int)(right*ans[i][j])%mod;
                right = (right*grid[i][j])%mod;
            }
        }

        return ans;
    }
}